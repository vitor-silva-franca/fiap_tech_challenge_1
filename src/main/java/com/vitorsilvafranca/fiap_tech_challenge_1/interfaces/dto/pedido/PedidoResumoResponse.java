package com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.StatusPedido;

public class PedidoResumoResponse {

    private Long id;

    private String descricao;

    private StatusPedido status;

    public PedidoResumoResponse(Long id, String descricao, StatusPedido status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
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
}
