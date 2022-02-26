package com.boasaude.autenticacao.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ExternalResourceException extends ResponseStatusException {

    public ExternalResourceException(String reason) {
        super(HttpStatus.GATEWAY_TIMEOUT, reason);
    }
}
