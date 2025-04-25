package com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class ErrorResponse {

    @Schema(description = "Data e hora do erro", example = "2025-04-22T14:15:22.123")
    private LocalDateTime timestamp;

    @Schema(description = "Código HTTP", example = "400")
    private int status;

    @Schema(description = "Mensagem de erro", example = "Já existe um usuário com o e-mail teste@email.com")
    private String mensagem;

    public ErrorResponse(LocalDateTime timestamp, int status, String mensagem) {
        this.timestamp = timestamp;
        this.status = status;
        this.mensagem = mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

}
