package com.boasaude.autenticacao.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.boasaude.autenticacao.exception.BadRequestException;
import com.boasaude.autenticacao.exception.NotFoundException;
import com.boasaude.autenticacao.helper.LoginHelper;
import com.boasaude.autenticacao.repository.LoginRepository;
import com.boasaude.autenticacao.request.LoginRequest;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.main.webApplicationType=reactive"})
public class AutenticacaoServiceTest {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @MockBean
    private LoginRepository loginRepository;

    @MockBean
    private JwtService jwtService;

    @Test
    public void naoDeveAutenticarUsuarioNaoEncontrado() {

        var loginRequest = LoginRequest.builder().login("teste").senha("teste").build();

        Mockito.when(loginRepository.verificarLogin(loginRequest)).thenReturn(Mono.empty());

        StepVerifier.create(autenticacaoService.verificarLogin(loginRequest))
                .expectErrorMatches(ex -> ex instanceof NotFoundException
                        && ((NotFoundException) ex).getReason().equals("Usuário não encontrado."))
                .verify();
    }

    @Test
    public void naoDeveAutenticarUsuarioProblemaObtencaoToken() {

        var loginRequest = LoginRequest.builder().login("user_associado").senha("associado").build();

        var loginEntity = LoginHelper.criarLogin();

        Mockito.when(loginRepository.verificarLogin(loginRequest)).thenReturn(Mono.just(loginEntity));

        Mockito.when(jwtService.gerarToken(loginRequest.getLogin())).thenReturn(Mono.empty());

        StepVerifier.create(autenticacaoService.verificarLogin(loginRequest))
                .expectErrorMatches(ex -> ex instanceof BadRequestException
                        && ((BadRequestException) ex).getReason().equals("Ocorreu um problema ao tentar gerar token para este usuário."))
                .verify();
    }

    @Test
    public void deveAutenticarUsuarioComSucesso() {

        var loginRequest = LoginRequest.builder().login("user_associado").senha("associado").build();

        var loginEntity = LoginHelper.criarLogin();

        var token =
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyX2Fzc29jaWFkbyIsImlzcyI6IkFkbUtWVGJSS3lycDZIVGxzQVhJeHZXQ2djU1JzQjFLIiwiaWF0IjoxNjQ2MDgwMzI1LCJleHAiOjExNjQ2MDgwMzI1fQ.9BRC2X0GTZjAXy1AN3AtAHzn3oUTb-yf4RR77X7gF18";

        Mockito.when(loginRepository.verificarLogin(loginRequest)).thenReturn(Mono.just(loginEntity));

        Mockito.when(jwtService.gerarToken(loginRequest.getLogin())).thenReturn(Mono.just(token));

        var loginResponse = autenticacaoService.verificarLogin(loginRequest).block();

        Assertions.assertNotNull(loginResponse);
        Assertions.assertEquals(token, loginResponse.getToken());
    }
}
