package com.boasaude.autenticacao.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boasaude.autenticacao.request.LoginRequest;
import com.boasaude.autenticacao.response.LoginResponse;
import com.boasaude.autenticacao.service.AutenticacaoService;

import reactor.core.publisher.Mono;

@RequestMapping("/acesso")
@RequiredArgsConstructor
@RestController
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping()
    public Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        return autenticacaoService.verificarLogin(loginRequest);
    }
}
