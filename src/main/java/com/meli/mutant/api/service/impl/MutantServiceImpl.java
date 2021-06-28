package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.dto.DnaStatus;
import com.meli.mutant.api.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    private HorizontalDnaImpl horizontalDna;

    @Autowired
    private VerticalDnaImpl verticalDna;

    @Override
    public Boolean isMutant(String[] dna) {
        String [][] dnaSeparateInRow = separateInRows(dna);
        List<String> resultDnaList = new ArrayList<>();
        resultDnaList.add(horizontalDna.inspectDna(dnaSeparateInRow).name());
        resultDnaList.add(verticalDna.inspectDna(dnaSeparateInRow).name());
        return resultDnaList.contains(DnaStatus.MUTANT.name());
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

}
