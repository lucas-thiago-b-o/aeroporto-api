package br.com.companhia.aeroporto;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.*;

class AeroportoApplicationTest {

    @Test
    void testMain() {
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
            mocked.when(() -> {
                SpringApplication.run(AeroportoApplication.class, "args");
            }).thenReturn(mock(ConfigurableApplicationContext.class));

            AeroportoApplication.main(new String[] { "args" });

            mocked.verify(() -> { SpringApplication.run(AeroportoApplication.class, "args"); });
        }
    }
}