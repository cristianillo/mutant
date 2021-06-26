package com.meli.mutant.api.controller;

import com.meli.mutant.api.dto.MutantRequest;
import com.meli.mutant.api.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping(value = "/mutant/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> isMutant(@RequestBody MutantRequest mutantRequest) {
        if(Boolean.TRUE.equals(mutantService.isMutant(mutantRequest.getDna()))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
