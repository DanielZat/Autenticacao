package com.boasaude.autenticacao.service;

import java.util.Base64;
import java.util.Date;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtService {

    private final KongService kongService;

    @Value("${token.validade}")
    private Long validadeEmMilisegundos;

    public Mono<String> gerarToken(String login) {

        return kongService.criarJwtConsumidor(login)
                .map(credencialJwtResponse -> {

                    Claims claims = Jwts.claims().setSubject(login);
                    claims.put("iss", credencialJwtResponse.getKey());

                    return Jwts.builder()
                            .setClaims(claims)
                            .setIssuedAt(new Date())
                            .setExpiration(new Date(new Date().getTime() + validadeEmMilisegundos))
                            .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(credencialJwtResponse.getSecret().getBytes()))
                            .compact();

                });
    }
}
