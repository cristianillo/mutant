package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.dto.StatisticResponse;
import com.meli.mutant.api.model.DnaStatus;
import com.meli.mutant.api.model.ResultMutant;
import com.meli.mutant.api.repository.ResultMutantRepository;
import com.meli.mutant.api.service.ResultMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultMutantServiceImpl implements ResultMutantService {

    @Autowired
    private ResultMutantRepository resultMutantRepository;

    /**
     * @see ResultMutantService#storeResult(DnaStatus)
     */
    @Override
    public void storeResult(DnaStatus status){
        ResultMutant resultOfOperation = new ResultMutant();
        resultOfOperation.setStatus(status);
        resultMutantRepository.save(resultOfOperation);
    }

    /**
     * @see ResultMutantService#getStatistics()
     */
    @Override
    public StatisticResponse getStatistics() {
        long mutantCount = resultMutantRepository.countByStatus(DnaStatus.MUTANT);
        long humanCount = resultMutantRepository.countByStatus(DnaStatus.HUMAN);
        StatisticResponse response = new StatisticResponse();
        response.setCount_mutant_dna(mutantCount);
        response.setCount_human_dna(humanCount);
        float ratio = mutantCount / (humanCount * 1f);
        response.setRatio(ratio);
        return response;
    }
}
