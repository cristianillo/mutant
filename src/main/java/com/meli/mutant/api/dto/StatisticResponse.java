package com.meli.mutant.api.dto;

import lombok.Data;

@Data
public class StatisticResponse {
    private long count_mutant_dna;
    private long count_human_dna;
    private float ratio;
}
