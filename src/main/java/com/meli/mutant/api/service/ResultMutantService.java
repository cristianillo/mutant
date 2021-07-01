package com.meli.mutant.api.service;

import com.meli.mutant.api.dto.StatisticResponse;
import com.meli.mutant.api.model.DnaStatus;

public interface ResultMutantService {
    public void storeResult(DnaStatus status);

    public StatisticResponse getStatistics();
}
