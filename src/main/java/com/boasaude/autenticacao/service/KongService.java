package com.boasaude.autenticacao.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.boasaude.autenticacao.client.CredencialJwtResponse;

import reactor.core.publisher.Mono;

@Component
@Slf4j
public class KongService {

    private final WebClient webClient;

    public KongService(@Value("${webclient.kong-service}") String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public Mono<CredencialJwtResponse> criarJwtConsumidor(String login) {

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/consumers/{consumer}/jwt").build(login))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(CredencialJwtResponse.class);
    }
}
