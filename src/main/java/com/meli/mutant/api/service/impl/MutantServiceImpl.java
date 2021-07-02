package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.exception.MatrixValidationException;
import com.meli.mutant.api.model.DnaStatus;
import com.meli.mutant.api.service.Dna;
import com.meli.mutant.api.service.MutantService;
import com.meli.mutant.api.service.ResultMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    private List<Dna> dnaConfig;

    @Autowired
    private ResultMutantService resultMutantService;

    private Pattern pat = Pattern.compile("^[atcgATCG]+");

    /**
     * @see MutantService#isMutant(String[])
     */
    @Override
    public Boolean isMutant(String[] dna) throws MatrixValidationException {
        String [][] dnaSeparateInRow = separateInRows(dna);
        List<String> resultDnaList = dnaConfig.stream().map(dnaMode -> dnaMode.inspectDna(dnaSeparateInRow).name()).collect(Collectors.toList());
        Boolean isMutant = resultDnaList.contains(DnaStatus.MUTANT.name());
        saveResult(isMutant);
        return isMutant;
    }

    /**
     * Transforma la cadena horizontal de String em arreglo y evalua que los tamaños de cada fila sean iguales
     * @param dna arreglo de String con la información de dna
     * @return Arreglo bidimencional a partir de parametro dna
     * @throws MatrixValidationException Cuando la matriz no cumple con el tamaño correcto
     */
    private String[][] separateInRows(String[] dna) throws MatrixValidationException {
        int rouCounter = 0;
        int horizontalLength = dna[0].length();
        String [][] m = new String[dna.length][horizontalLength];
        for(String row: dna){
            if(horizontalLength != row.length()) {
                throw new MatrixValidationException("The rows or columns are not the same length");
            }
            validateCharacters(row);
            m[rouCounter] = row.toUpperCase().split("");
            rouCounter++;
        }
        return m;
    }

    /**
     * Valida que la matriz tenga los caracteres validos (A,T,C,G)
     * @param rowDna fila del arreglo
     * @throws MatrixValidationException Cuando la fila no contiene los caracteres adecuados
     */
    private void validateCharacters(String rowDna) throws MatrixValidationException {
        if (!pat.matcher(rowDna).matches()) {
            throw new MatrixValidationException("The characters String are not valid");
        }
    }

    /**
     * Guarda el resultado del análisis de la matriz.
     * @param isMutant Trues si es mutante, de lo contrario false.
     */
    @Async
    private void saveResult(Boolean isMutant) {
        if (Boolean.TRUE.equals(isMutant)){
            resultMutantService.storeResult(DnaStatus.MUTANT);
        } else {
            resultMutantService.storeResult(DnaStatus.HUMAN);
        }
    }

}
