package ait.cohort34.person.service;

import ait.cohort34.person.dto.*;
import ait.cohort34.person.model.Person;

import java.util.List;

public interface PersonService {
    Boolean addPerson(PersonDto personDto);
    PersonDto findPerson(int id);
    PersonDto[] findByCity(String city);
    PersonDto[] findByAges(int min_age, int max_age);
    PersonDto updateName(int id, String name);
    PersonDto[] findByName(String name);
    Iterable<CityDto> getCityPopulation();
    PersonDto updateAddress(int id, AddressDto addressDto);
    PersonDto deletePerson(int id);


    Boolean addChild(ChildDto childDto);

    Boolean addEmployee(EmployeeDto employeeDto);

    List<ChildDto> getAllChildren();

    List<EmployeeDto> getEmployeesBySalaryRange(int minSalary, int maxSalary);
}
