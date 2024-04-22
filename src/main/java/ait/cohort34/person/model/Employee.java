package ait.cohort34.person.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {
    String company;
    int salary;

    public Employee(Integer id, String name, LocalDate birthDate, Address address, String company, int salary, String type) {
        super(id, name, birthDate, address, type);
        this.company = company;
        this.salary = salary;
    }
}
