package com.meli.mutant.api.service;

import com.meli.mutant.api.exception.MatrixValidationException;

public interface MutantService {

    /**
     * Determina si el arreglo dna tiene la secuencia mutante
     * @param dna arreglo de String con la información de dna
     * @return True si el dna es mutante, de lo contrario False
     * @throws MatrixValidationException Cuando la matriz no cumple con el tamaño correcto
     */
    Boolean isMutant(String [] dna) throws MatrixValidationException;
}
