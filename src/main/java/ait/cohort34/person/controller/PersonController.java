package ait.cohort34.person.controller;

import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityDto;
import ait.cohort34.person.model.Person;
import ait.cohort34.person.service.PersonService;
import ait.cohort34.person.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    final PersonService personService;


    @PostMapping
    public Boolean addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }
    @GetMapping("/{id}")
    public PersonDto findPerson(@PathVariable int id) {
        return personService.findPerson(id);
    }
    @GetMapping("/city/{name}")
    public List<PersonDto> findByCity(@PathVariable String name) {
        return personService.findByCity(name);
    }
    @GetMapping("/ages/{min_age}/{max_age}")
    public List<PersonDto> findByAges(@PathVariable int min_age, @PathVariable int max_age) {
        return personService.findByAges(min_age, max_age);
    }
    @PutMapping("/{id}/name/{name}")
    public PersonDto updateName(@PathVariable int id, @PathVariable String name) {
        return personService.updateName(id, name);
    }
    @GetMapping("/name/{name}")
    public List<PersonDto> findByName(@PathVariable String name) {
        return personService.findByName(name);
    }
    @GetMapping("/population/{city}")
    public List<CityDto> findByPopulation(@PathVariable String city) {
        return personService.getCityPopulation(city);
    }
    @PutMapping("/{id}/address")
    public PersonDto updateAddress(@PathVariable int id,@RequestBody AddressDto address) {
        return personService.updateAddress(id, address);
    }
    @DeleteMapping("/{id}")
    public PersonDto deletePerson(@PathVariable int id) {
        return personService.deletePerson(id);
    }
}
