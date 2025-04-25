package com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String status) {
        super("Status inválido: " + status);
    }
}
