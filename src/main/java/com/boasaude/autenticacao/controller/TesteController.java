package com.boasaude.autenticacao.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RequestMapping("/teste")
@RequiredArgsConstructor
@RestController
public class TesteController {

    @GetMapping()
    public Mono<String> teste() {
        return Mono.just("Chegou aqui");
    }
}
