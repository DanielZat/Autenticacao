package com.boasaude.autenticacao.helper;

import com.boasaude.autenticacao.entity.LoginEntity;

public class LoginHelper {

    public static LoginEntity criarLogin() {

        return LoginEntity.builder()
                .id("1")
                .login("user_associado")
                .senha("associado")
                .build();
    }
}
