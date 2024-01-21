package br.com.companhia.aeroporto.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    DatabaseInstanceInitializer databaseInstanceInitializer;

    @PostConstruct
    public void init() {
        this.databaseInstanceInitializer.instanciaDadosNaBase();
    }

    @Bean
    public DatabaseInstanceInitializer getDatabaseInstanceInitializer() {
        return new DatabaseInstanceInitializer();
    }
}
