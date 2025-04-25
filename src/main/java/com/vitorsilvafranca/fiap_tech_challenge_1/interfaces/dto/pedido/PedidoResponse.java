package com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.StatusPedido;

public class PedidoResponse {

    private Long id;

    private String descricao;

    private StatusPedido status;

    private Long usuarioId;

    public PedidoResponse(Long id, String descricao, StatusPedido status, Long usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}
