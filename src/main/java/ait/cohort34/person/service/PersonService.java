package ait.cohort34.person.service;

import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.model.Person;

import java.util.List;

public interface PersonService {
    Boolean addPerson(PersonDto personDto);
    PersonDto findPerson(int id);
    List<PersonDto> findByCity(String city);
    List<PersonDto> findByAges(int min_age, int max_age);
    PersonDto updateName(int id, String name);
    List<PersonDto> findByName(String name);
    List<CityDto> getCityPopulation(String name);
    PersonDto updateAddress(int id, AddressDto addressDto);
    PersonDto deletePerson(int id);

}
