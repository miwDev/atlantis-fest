package com.msd.atlantis_fest.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Staff extends User {

    @Builder.Default
    @OneToMany(mappedBy = "staff")
    private List<Shift> shifts = new ArrayList<>();
}