package com.boasaude.autenticacao.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.boasaude.autenticacao.helper.CredencialJwtResponseHelper;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.main.webApplicationType=reactive"})
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @MockBean
    private KongService kongService;

    @Test
    public void deveObterTokenComSucesso() {

        var login = "user_associado";

        var credencialJwtResponse = CredencialJwtResponseHelper.criarCredencialJwt();

        Mockito.when(kongService.criarJwtConsumidor(login)).thenReturn(Mono.just(credencialJwtResponse));

        var token = jwtService.gerarToken(login).block();

        Assertions.assertNotNull(token);
    }
}
