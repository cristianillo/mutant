package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.dto.DnaStatus;
import com.meli.mutant.api.service.Dna;
import org.springframework.stereotype.Service;

@Service
public class HorizontalDnaImpl implements Dna {

    @Override
    public DnaStatus inspectDna(String[][] dnaMatrix) {
        int width =  dnaMatrix[0].length;
        String sameCharDna;
        int sameCharDnaCounter;
        for (String[] matrix : dnaMatrix) {
            sameCharDna = "";
            sameCharDnaCounter = 1;
            for (int w = 0; w < width; w++) {
                String charDnaExtracted = matrix[w];
                if (!charDnaExtracted.equals(sameCharDna)) {
                    sameCharDna = charDnaExtracted;
                    sameCharDnaCounter = 1;
                } else {
                    sameCharDnaCounter++;
                    if(sameCharDnaCounter >= 4) {
                        return DnaStatus.MUTANT;
                    }
                }
            }
        }
        return DnaStatus.HUMAN;
    }

    @Override
    public DnaStatus getInspectDnaResult() {
        return null;
    }
}
