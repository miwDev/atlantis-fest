package com.msd.atlantis_fest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User {

    @Column(unique = true, nullable = false)
    @NotEmpty
    //@ValidDNI #TODO
    private String dniNumber;

    @Column(nullable = false)
    @NotEmpty
    private String firstName;

    @Column(nullable = false)
    @NotEmpty
    private String lastName;

    @Column(nullable = false)
    @Past
    private LocalDate birthDate;


}
