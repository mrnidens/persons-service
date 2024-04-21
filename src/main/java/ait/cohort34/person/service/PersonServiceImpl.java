package ait.cohort34.person.service;

import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {
        if(personRepository.existsById(personDto.getId())) {
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

    @Override
    public List<PersonDto> findByCity(String city) {
        return List.of();
    }

    @Override
    public List<PersonDto> findByAges(int min_age, int max_age) {
        return List.of();
    }

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


    @Override
    public List<PersonDto> findByName(String name) {
        return List.of();
    }

    @Override
    public List<CityDto> getCityPopulation(String name) {
        return List.of();
    }

    @Override
    public PersonDto updateAddress(int id, AddressDto addressDto) {
        return null;
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
}
