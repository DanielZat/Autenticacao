package com.boasaude.autenticacao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtService {

    private final KongService kongService;

    public String gerarToken(String login) {

        var teste = kongService.criarJwtConsumidor(login);

        return StringUtils.EMPTY;
    }
}
