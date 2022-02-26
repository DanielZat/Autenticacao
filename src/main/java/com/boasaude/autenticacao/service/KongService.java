package com.boasaude.autenticacao.service;

import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.boasaude.autenticacao.client.CredencialJwtResponse;
import com.boasaude.autenticacao.exception.ExternalResourceException;
import com.boasaude.autenticacao.exception.NotFoundException;

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
                .onStatus(HttpStatus.NOT_FOUND::equals, logarErroDispararExcecao404(login))
                .onStatus(HttpStatus::isError, logarErroDispararExcecaoExterna())
                .bodyToMono(CredencialJwtResponse.class);
    }

    private Function<ClientResponse, Mono<? extends Throwable>> logarErroDispararExcecao404(String login) {
        return response -> {
            log.error("Usuário com o login {} não foi encontrado.", login);
            return Mono.error(new NotFoundException("Não foi possível encontrar o usuário."));
        };
    }

    private Function<ClientResponse, Mono<? extends Throwable>> logarErroDispararExcecaoExterna() {
        return response -> {
            log.error("Erro ao tentar conectar com serviço do api gateway. Status: {}", response.rawStatusCode());
            return Mono.error(new ExternalResourceException("Não foi possível estabelecer contato com um serviço do gateway."));
        };
    }
}
