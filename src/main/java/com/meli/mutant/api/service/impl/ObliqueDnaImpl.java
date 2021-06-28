package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.dto.DnaStatus;
import com.meli.mutant.api.service.Dna;

public class ObliqueDnaImpl implements Dna {
    @Override
    public DnaStatus inspectDna(String[][] dnaMatrix) {
        return DnaStatus.MUTANT;
    }

    @Override
    public DnaStatus getInspectDnaResult() {
        return null;
    }
}
