package com.meli.mutant.api.service;

import com.meli.mutant.api.model.DnaStatus;

public interface Dna {

    DnaStatus inspectDna(String [][] dnaMatrix);
}
