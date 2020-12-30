package io.github.dilhelh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class DevelopmentApplicationConfiguration {

    @Bean
    public CommandLineRunner exec (){
        return args -> {
            System.out.println("Executando no ambiente de desenvolvimento");
        };
    }
}
