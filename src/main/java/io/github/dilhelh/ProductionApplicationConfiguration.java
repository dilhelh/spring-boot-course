package io.github.dilhelh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Production
public class ProductionApplicationConfiguration {

    @Bean
    public CommandLineRunner exec (){
        return args -> {
            System.out.println("Executando no ambiente de produção");
        };
    }
}
