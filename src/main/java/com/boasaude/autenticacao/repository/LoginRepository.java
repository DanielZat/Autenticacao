package com.boasaude.autenticacao.repository;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import com.boasaude.autenticacao.entity.LoginEntity;
import com.boasaude.autenticacao.exception.BadRequestException;
import com.boasaude.autenticacao.request.LoginRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class LoginRepository {

    public Mono<LoginEntity> verificarLogin(LoginRequest loginRequest) {

        return criarListaLogins()
                .filter(associadoEntity -> loginRequest.getLogin().equals(associadoEntity.getLogin()) && loginRequest.getSenha()
                        .equals(associadoEntity.getSenha()))
                .switchIfEmpty(Mono.error(new BadRequestException("Autenticação não realizada para este usuário")))
                .single();
    }

    private Flux<LoginEntity> criarListaLogins() {

        return Flux.fromIterable(Arrays.asList(LoginEntity.builder()
                .id("1")
                .login("user_associado")
                .senha("associado")
                .build(), LoginEntity
                        .builder()
                        .id("2")
                        .login("user_conveniado")
                        .senha("conveniado")
                        .build()));
    }
}
