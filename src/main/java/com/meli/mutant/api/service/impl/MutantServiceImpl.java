package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.service.MutantService;
import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantService {

    @Override
    public Boolean isMutant(String[] dna) {
        return Boolean.TRUE;
    }
}
