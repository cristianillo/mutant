package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.exception.MatrixValidationException;
import com.meli.mutant.api.service.Dna;
import com.meli.mutant.api.service.ResultMutantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class MutantServiceImplTest {

    @InjectMocks
    MutantServiceImpl mutantServiceImpl;

    List<Dna> dnaConfig;

    @Mock
    ResultMutantService resultMutantService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        dnaConfig = new ArrayList<>();
        dnaConfig.add(new ObliqueDna());
        dnaConfig.add(new HorizontalDna());
        dnaConfig.add(new VerticalDna());
        ReflectionTestUtils.setField(mutantServiceImpl, "dnaConfig", dnaConfig);
    }

    @Test
    void isMutantTrueResponseTest() throws MatrixValidationException {
        doNothing().when(resultMutantService).storeResult(any());
        Boolean response = mutantServiceImpl.isMutant(getDnaFullMutantFormat());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Boolean.TRUE, response);
    }

    @Test
    void isMutantFalseResponseTest() throws MatrixValidationException {
        doNothing().when(resultMutantService).storeResult(any());
        Boolean response = mutantServiceImpl.isMutant(getDnaFullHumanFormat());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(Boolean.FALSE, response);
    }

    @Test
    void isMutantWrongFormatTest() {
        Assertions.assertThrows(MatrixValidationException.class, () -> {
            mutantServiceImpl.isMutant(getDnaWrongFormat());
        });
    }

    @Test
    void isMutantWrongCharactersFormatTest() {
        Assertions.assertThrows(MatrixValidationException.class, () -> {
            mutantServiceImpl.isMutant(getDnaWrongCharactersFormat());
        });
    }

    private String[] getDnaFullMutantFormat() {
        return new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    }

    private String[] getDnaFullHumanFormat() {
        return new String[]{"TTGCGA","CAGTGC","TTATGT","AGAGAG","CACCTA","TCACTG"};
    }

    private String[] getDnaWrongFormat() {
        return new String[]{"TTGCGA","CAGC","TTATGT","AGAABG","CACCTA","TCACTG"};
    }

    private String[] getDnaWrongCharactersFormat() {
        return new String[]{"TTGCGA","PQERIU","TTATGT","AGAABG","CACCTA","TCACTG"};
    }
}
