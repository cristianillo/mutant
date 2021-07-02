package com.meli.mutant.api.service;

import com.meli.mutant.api.dto.StatisticResponse;
import com.meli.mutant.api.model.DnaStatus;

public interface ResultMutantService {
    /**
     * Guarda el resultado del análisis de la matriz
     * @param status Enum de tipo DnaStatus
     */
    public void storeResult(DnaStatus status);

    /**
     * Consulta y determina la estadística del análisis de los dna.
     * @return Objeto de tipo StatisticResponse
     */
    public StatisticResponse getStatistics();
}
