package io.github.dilhelh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public String applicationName(){
        return "Sistema de Vendas";
    }
}
