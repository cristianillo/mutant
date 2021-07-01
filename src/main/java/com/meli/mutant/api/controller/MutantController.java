package com.meli.mutant.api.controller;

import com.meli.mutant.api.dto.MutantRequest;
import com.meli.mutant.api.dto.StatisticResponse;
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

    @PostMapping(value = "/mutant/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> isMutant(@RequestBody MutantRequest mutantRequest) {
        if(Boolean.TRUE.equals(mutantService.isMutant(mutantRequest.getDna()))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<StatisticResponse> stats() {
        return new ResponseEntity<>(resultMutantService.getStatistics(), HttpStatus.OK);
    }
}
