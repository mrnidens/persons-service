package ait.cohort34.person.model;

import ait.cohort34.person.dto.AddressDto;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
public class Person {
    @Id
    Integer id;
    @Setter
    String name;
    LocalDate birthDate;
    @Setter
    @Embedded
    AddressDto address;
}
