package ait.cohort34.person.controller;

import ait.cohort34.person.dto.*;
import ait.cohort34.person.model.Person;
import ait.cohort34.person.service.PersonService;
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

    @PostMapping
    public Boolean addChild(@RequestBody ChildDto childDto) {
        return personService.addChild(childDto);
    }

    @PostMapping
    public Boolean addEmployee(@RequestBody EmployeeDto employeeDto) {
        return personService.addEmployee(employeeDto);

    }
    @GetMapping("/{id}")
    public PersonDto findPerson(@PathVariable int id) {
        return personService.findPerson(id);
    }
    @GetMapping("/city/{name}")
    public PersonDto[] findByCity(@PathVariable String name) {
        return personService.findByCity(name);
    }
    @GetMapping("/ages/{min_age}/{max_age}")
    public PersonDto[] findByAges(@PathVariable int min_age, @PathVariable int max_age) {
        return personService.findByAges(min_age, max_age);
    }
    @PutMapping("/{id}/name/{name}")
    public PersonDto updateName(@PathVariable int id, @PathVariable String name) {
        return personService.updateName(id, name);
    }
    @GetMapping("/name/{name}")
    public PersonDto[] findByName(@PathVariable String name) {
        return personService.findByName(name);
    }
    @GetMapping("/population/city")
    public Iterable<CityDto> getCityPopulation() {
        return personService.getCityPopulation();
    }
    @PutMapping("/{id}/address")
    public PersonDto updateAddress(@PathVariable int id,@RequestBody AddressDto address) {
        return personService.updateAddress(id, address);
    }
    @DeleteMapping("/{id}")
    public PersonDto deletePerson(@PathVariable int id) {
        return personService.deletePerson(id);
    }
    @GetMapping("/children")
    public List<ChildDto> getAllChildren() {
        return personService.getAllChildren();
    }

    @GetMapping("/employees")
    public List<EmployeeDto> getEmployeesBySalaryRange(@RequestParam int minSalary, @RequestParam int maxSalary) {
        return personService.getEmployeesBySalaryRange(minSalary, maxSalary);
    }


}
