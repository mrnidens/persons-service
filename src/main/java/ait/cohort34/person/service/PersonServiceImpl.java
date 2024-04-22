package ait.cohort34.person.service;

import ait.cohort34.person.configuration.PersonNotFoundException;
import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.*;
import ait.cohort34.person.model.Address;
import ait.cohort34.person.model.Child;
import ait.cohort34.person.model.Employee;
import ait.cohort34.person.model.Person;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPerson(int id) {
        Optional<Person> personOptional = personRepository.findById(id);
        return personOptional.map(person -> modelMapper.map(person, PersonDto.class)).orElse(null);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public PersonDto[] findByCity(String city) {
        return personRepository.findByAddressCityIgnoreCase(city)
                .map(c -> modelMapper.map(c, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Transactional
    @Override
    public PersonDto[] findByAges(int min_age, int max_age) {
        LocalDate from = LocalDate.now().minusYears(min_age);
        LocalDate to = LocalDate.now().minusYears(max_age);
        return personRepository.findByBirthDateBetween(from, to)
                .map(a -> modelMapper.map(a, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Transactional
    @Override
    public PersonDto updateName(int id, String name) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setName(name);
            personRepository.save(person);
            return modelMapper.map(person, PersonDto.class);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public PersonDto[] findByName(String name) {
        return personRepository.findByNameIgnoreCase(name)
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Override
    public Iterable<CityDto> getCityPopulation() {
        return personRepository.getCitiesPopulation();
    }

    @Transactional
    @Override
    public PersonDto updateAddress(int id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setAddress(modelMapper.map(addressDto, Address.class));
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto deletePerson(int id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            personRepository.delete(person);
            return modelMapper.map(person, PersonDto.class);
        } else {
            return null;
        }
    }

    @Override
    public Boolean addChild(ChildDto childDto) {
        return null;
    }

    @Override
    public Boolean addEmployee(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public List<ChildDto> getAllChildren() {
        return List.of();
    }

    @Override
    public List<EmployeeDto> getEmployeesBySalaryRange(int minSalary, int maxSalary) {
        return List.of();
    }


    @Transactional
    public void run(String... args) throws Exception {
        if (personRepository.count() == 0) {
            Person person = new Person(1000, "John", LocalDate.of(1985, 3, 11),
                    new Address("Berlin", "Purim", 18), "Person");
            Child child = new Child(2000, "Carl", LocalDate.of(2018, 3, 11),
                    new Address("Hamburg", "Hauptstrasse", 1), "Sunny", "Child");
            Employee employee = new Employee(3000, "Papi", LocalDate.of(1977, 5, 12),
                    new Address("Siegen", "Bismarckplatz", 1), "Motorola", 4500, "Employee");
            personRepository.save(person);
            personRepository.save(child);
            personRepository.save(employee);

        }
    }
}
