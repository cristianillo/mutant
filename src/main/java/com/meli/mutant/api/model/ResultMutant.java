package com.meli.mutant.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResultMutant {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(STRING)
    private DnaStatus status;
}
