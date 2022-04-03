package com.boasaude.autenticacao.helper;

import com.boasaude.autenticacao.client.CredencialJwtResponse;

public class CredencialJwtResponseHelper {

    public static CredencialJwtResponse criarCredencialJwt() {

        return CredencialJwtResponse
                .builder()
                .rsa_public_key(null)
                .created_at(1648843579L)
                .id("id=9bcc9fac-5d5f-4003-8e88-a69284fab9bf")
                .key("XAzEya74IBaOP95oP5sMBd5ldjB85elm")
                .secret("SHmYBj8Y9npcWL9joesbPgJgA9fw4lPU")
                .algorithm("HS256")
                .build();
    }
}
