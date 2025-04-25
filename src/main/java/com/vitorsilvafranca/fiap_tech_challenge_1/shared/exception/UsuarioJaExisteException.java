package com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception;

public class UsuarioJaExisteException extends RuntimeException {
    public UsuarioJaExisteException(String email) {
        super("Já existe um usuário com o e-mail " + email);
    }
}
