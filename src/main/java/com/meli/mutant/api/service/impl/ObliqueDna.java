package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.model.DnaStatus;
import com.meli.mutant.api.service.Dna;

public class ObliqueDna implements Dna {
    @Override
    public DnaStatus inspectDna(String[][] dnaMatrix) {
        int width = dnaMatrix[0].length;
        int high = dnaMatrix.length;
        String sameCharDna;
        int sameCharDnaCounter;
        for (int diag = 1 - width; diag <= high - 1; diag ++) {
            int v = Math.max(0, diag);
            int h = -Math.min(0, diag);
            sameCharDna = "";
            sameCharDnaCounter = 1;
            for (int verti = v, hori = h; verti < high && hori < width; verti++, hori++) {
                String charDnaExtracted = dnaMatrix[verti][hori];
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
