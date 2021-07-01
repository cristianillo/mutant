package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.model.DnaStatus;
import com.meli.mutant.api.service.Dna;
import com.meli.mutant.api.service.MutantService;
import com.meli.mutant.api.service.ResultMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    private List<Dna> dnaConfig;

    @Autowired
    private ResultMutantService resultMutantService;

    @Override
    public Boolean isMutant(String[] dna) {
        String [][] dnaSeparateInRow = separateInRows(dna);
        List<String> resultDnaList = dnaConfig.stream().map(dnaMode -> dnaMode.inspectDna(dnaSeparateInRow).name()).collect(Collectors.toList());
        Boolean isMutant = resultDnaList.contains(DnaStatus.MUTANT.name());
        saveResult(isMutant);
        return isMutant;
    }

    private String[][] separateInRows(String[] dna) {
        int rouCounter = 0;
        String [][] m = new String[dna.length][dna[0].length()];
        for(String row: dna){
            m[rouCounter] = row.toUpperCase().split("");
            rouCounter++;
        }
        return m;
    }

    @Async
    private void saveResult(Boolean isMutant) {
        if (Boolean.TRUE.equals(isMutant)){
            resultMutantService.storeResult(DnaStatus.MUTANT);
        } else {
            resultMutantService.storeResult(DnaStatus.HUMAN);
        }
    }

}
