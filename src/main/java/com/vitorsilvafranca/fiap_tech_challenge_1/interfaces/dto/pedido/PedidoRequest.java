package com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.StatusPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PedidoRequest {

    @NotBlank(message = "Descrição não pode ser vazia e deve possuir ao menos 3 caracteres")
    @Size(min=3)
    private String descricao;

    @NotNull(message = "Status do pedido deve ser 'PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDO' ou 'CANCELADO'")
    private StatusPedido status;

    @NotNull(message = "Id do usuário não pode ser vazio")
    private Long usuarioId;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
