package com.meli.mutant.api.controller;

import com.meli.mutant.api.dto.MutantRequest;
import com.meli.mutant.api.dto.StatisticResponse;
import com.meli.mutant.api.exception.MatrixValidationException;
import com.meli.mutant.api.service.MutantService;
import com.meli.mutant.api.service.ResultMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @Autowired
    private ResultMutantService resultMutantService;

    /**
     * Método post que recibe el arreglo de dna para determinar si es mutante o humano
     * @param mutantRequest Objeto de tipo MutantRequest con el arreglo de dna
     * @return True si el dna es mutante, de lo contrario False
     * @throws MatrixValidationException Cuando la matriz no cumple con el tamaño correcto
     */
    @PostMapping(value = "/mutant/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> isMutant(@RequestBody MutantRequest mutantRequest) throws MatrixValidationException {
        String[] dnaList = mutantRequest.getDna();
        if(Boolean.TRUE.equals(mutantService.isMutant(dnaList))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    /**
     * Método get que devuelve la estadistica de las verificaciones de ADN
     * @return ResponseEntity de tipo StatisticResponse con los paremetros count_mutant_dna, count_human_dna y ratio
     */
    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<StatisticResponse> stats() {
        return new ResponseEntity<>(resultMutantService.getStatistics(), HttpStatus.OK);
    }
}
