package com.boasaude.autenticacao.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CredencialJwtResponse {

    private String rsa_public_key;

    private Long created_at;

    private Consumer consumer;

    private String id;

    private String algorithm;

    private String secret;

    private String key;
}
