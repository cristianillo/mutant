package com.meli.mutant.api.service.impl;

import com.meli.mutant.api.dto.StatisticResponse;
import com.meli.mutant.api.model.DnaStatus;
import com.meli.mutant.api.model.ResultMutant;
import com.meli.mutant.api.repository.ResultMutantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResultMutantServiceImplTest {

    @InjectMocks
    ResultMutantServiceImpl resultMutantService;

    @Mock
    ResultMutantRepository resultMutantRepository;

    @Test
    void storeResultTest(){
        ResultMutant resultMutant = Mockito.mock(ResultMutant.class);
        when(resultMutantRepository.save(any())).thenReturn(resultMutant);
        resultMutantService.storeResult(DnaStatus.MUTANT);
        verify(resultMutantRepository, times(1)).save(any());
    }

    @Test
    void getStatisticsTest() {
        when(resultMutantRepository.countByStatus(DnaStatus.MUTANT)).thenReturn(40L);
        when(resultMutantRepository.countByStatus(DnaStatus.HUMAN)).thenReturn(100L);
        StatisticResponse response = resultMutantService.getStatistics();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(100L, response.getCount_human_dna());
        Assertions.assertEquals(40L, response.getCount_mutant_dna());
        Assertions.assertEquals(0.4F, response.getCount_mutant_dna() / (response.getCount_human_dna() * 1F));
    }

}
