package com.meli.mutant.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServletInitializerTest {

    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    @Test
    void testIt() {
        ServletInitializer servletInitializer = new ServletInitializer();
        when(springApplicationBuilder.sources(MutantApplication.class)).thenReturn(springApplicationBuilder);

        SpringApplicationBuilder result = servletInitializer.configure(springApplicationBuilder);

        verify(springApplicationBuilder).sources(MutantApplication.class);
        Assertions.assertEquals(springApplicationBuilder, result);
    }

}
