package com.meli.mutant.api.service;

import com.meli.mutant.api.dto.DnaStatus;

public interface Dna {

    DnaStatus inspectDna(String [][] dnaMatrix);

    DnaStatus getInspectDnaResult();
}
