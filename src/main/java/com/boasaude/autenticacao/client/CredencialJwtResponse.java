package com.boasaude.autenticacao.client;

import java.time.LocalDateTime;

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

    private LocalDateTime created_at;

    private Consumer consumer;

    private String id;

    private String algorithm;

    private String secret;

    private String key;

    // {
    //
    // "rsa_public_key":null,
    //
    // "created_at":1645658407,
    //
    // "consumer":{
    //
    // "id":"ccff220d-bb04-4ca4-b4f7-267f87324b04"
    //
    // },
    //
    // "id":"465acf04-404a-4a49-ac80-f14126c4c4fe",
    //
    // "algorithm":"HS256",
    //
    // "secret":"g9FI7DtaF6taeyfW4qELxRebo7dhhiEU",
    //
    // "key":"p4vjhhR1v3kw6MAOChOyxfSBfgWpLbpc"
    //
    // }
}
