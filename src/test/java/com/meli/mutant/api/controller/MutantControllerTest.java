package com.meli.mutant.api.controller;

import com.meli.mutant.api.dto.MutantRequest;
import com.meli.mutant.api.dto.StatisticResponse;
import com.meli.mutant.api.exception.MatrixValidationException;
import com.meli.mutant.api.service.MutantService;
import com.meli.mutant.api.service.ResultMutantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MutantControllerTest {

    @InjectMocks
    MutantController mutantController;

    @Mock
    MutantService mutantService;

    @Mock
    ResultMutantService resultMutantService;

    @Mock
    MutantRequest request;

    @Test
    void mutantResult200Test() throws MatrixValidationException {
        when(request.getDna()).thenReturn(new String[]{"ATGC","CAGT","TTAT","AAAA"});
        when(mutantService.isMutant(any())).thenReturn(Boolean.TRUE);
        ResponseEntity<Object> response = mutantController.isMutant(request);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void mutantResult403Test() throws MatrixValidationException {
        when(request.getDna()).thenReturn(new String[]{"ATGC","CBGT","TTAT","AABA"});
        when(mutantService.isMutant(any())).thenReturn(Boolean.FALSE);
        ResponseEntity<Object> response = mutantController.isMutant(request);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(403, response.getStatusCode().value());
    }

    @Test
    void mutantStatsTest() throws MatrixValidationException {
        StatisticResponse response = Mockito.mock(StatisticResponse.class);
        when(response.getCount_mutant_dna()).thenReturn(40L);
        when(response.getCount_human_dna()).thenReturn(100L);
        when(response.getRatio()).thenReturn(0.4F);
        when(resultMutantService.getStatistics()).thenReturn(response);
        ResponseEntity<StatisticResponse> responseStats = mutantController.stats();
        Assertions.assertNotNull(responseStats);
        Assertions.assertEquals(200, responseStats.getStatusCode().value());
        Assertions.assertNotNull(responseStats.getBody());
        Assertions.assertEquals(40L, responseStats.getBody().getCount_mutant_dna());
        Assertions.assertEquals(100L, responseStats.getBody().getCount_human_dna());
        Assertions.assertEquals(0.4F, responseStats.getBody().getRatio());
    }
}
