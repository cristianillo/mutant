package com.meli.mutant.api.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
public class ResultMutant {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(STRING)
    private DnaStatus status;
}
