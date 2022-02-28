package com.boasaude.autenticacao.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.boasaude.autenticacao.request.LoginRequest;
import com.boasaude.autenticacao.response.LoginResponse;
import com.boasaude.autenticacao.service.AutenticacaoService;

import reactor.core.publisher.Mono;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/login")
@RequiredArgsConstructor
@RestController
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autenticação realizada com sucesso", content = @Content(schema = @Schema(
                    implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Autenticação não realizada para este usuário")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        return autenticacaoService.verificarLogin(loginRequest);
    }
}
