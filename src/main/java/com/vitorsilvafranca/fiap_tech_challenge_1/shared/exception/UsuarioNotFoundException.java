package com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("Usuário com ID " + id + " não encontrado.");
    }
}
