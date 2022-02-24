package com.boasaude.autenticacao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.boasaude.autenticacao.exception.BadRequestException;
import com.boasaude.autenticacao.repository.LoginRepository;
import com.boasaude.autenticacao.request.LoginRequest;
import com.boasaude.autenticacao.response.LoginResponse;

import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class AutenticacaoService {

    private final LoginRepository loginRepository;

    private final JwtService jwtService;

    public Mono<LoginResponse> verificarLogin(LoginRequest loginRequest) {

        return loginRepository.verificarLogin(loginRequest)
                .flatMap(loginEntity -> jwtService.gerarToken(loginEntity.getLogin()))
                .map(token -> LoginResponse.builder().token(token).build())
                .switchIfEmpty(Mono.error(new BadRequestException("Autenticação não realizada para este usuário")));
    }
}
