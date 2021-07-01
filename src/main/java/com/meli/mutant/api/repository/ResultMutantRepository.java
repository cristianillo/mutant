package com.meli.mutant.api.repository;

import com.meli.mutant.api.model.DnaStatus;
import com.meli.mutant.api.model.ResultMutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultMutantRepository extends JpaRepository<ResultMutant, Integer> {
    long countByStatus(DnaStatus dnaStatus);
}
