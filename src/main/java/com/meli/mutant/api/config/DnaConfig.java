package com.meli.mutant.api.config;

import com.meli.mutant.api.service.Dna;
import com.meli.mutant.api.service.impl.HorizontalDna;
import com.meli.mutant.api.service.impl.ObliqueDna;
import com.meli.mutant.api.service.impl.VerticalDna;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DnaConfig {

    @Bean
    public List<Dna> getDnaConfiguration() {
        List<Dna> dna = new ArrayList<>();
        dna.add(new ObliqueDna());
        dna.add(new HorizontalDna());
        dna.add(new VerticalDna());
        return dna;
    }
}
