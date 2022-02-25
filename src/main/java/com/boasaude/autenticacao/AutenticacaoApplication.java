package com.boasaude.autenticacao;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Boa Saúde - Autenticação", version = "1.0", description = "Documentação do microsserviço de autenticação"))
public class AutenticacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutenticacaoApplication.class, args);
    }

}
