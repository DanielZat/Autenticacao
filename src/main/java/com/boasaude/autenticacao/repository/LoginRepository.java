package com.boasaude.autenticacao.repository;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import com.boasaude.autenticacao.entity.LoginEntity;
import com.boasaude.autenticacao.request.LoginRequest;

import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class LoginRepository {

    public Mono<LoginEntity> verificarLogin(LoginRequest loginRequest) {

        return Mono.just(criarListaLogins()
                .stream()
                .filter(associadoEntity -> loginRequest.getLogin().equals(associadoEntity.getLogin()) && loginRequest.getSenha()
                        .equals(associadoEntity.getSenha()))
                .findAny()
                .get());
    }

    private List<LoginEntity> criarListaLogins() {

        return Arrays.asList(LoginEntity.builder()
                .id("1")
                .login("user_associado")
                .senha("associado")
                .build(), LoginEntity
                        .builder()
                        .id("2")
                        .login("user_conveniado")
                        .senha("conveniado")
                        .build());
    }

}
