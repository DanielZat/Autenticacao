package com.boasaude.autenticacao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class LoginEntity {

    private String id;

    private String login;

    private String senha;
}
