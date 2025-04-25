package com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.mapper;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.Pedido;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido.PedidoRequest;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido.PedidoResponse;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido.PedidoResumoResponse;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.DomainException;

public class PedidoMapper {

    public static Pedido toResumo(PedidoRequest request) {
        if (request.getUsuarioId() == null) {
            throw new DomainException("ID do usuário é obrigatório");
        }
        Usuario usuario = new Usuario(request.getUsuarioId());

        return new Pedido(request.getDescricao(), request.getStatus(), usuario);
    }

    public static PedidoResponse fromModel(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getDescricao(),
                pedido.getStatus(),
                pedido.getUsuario().getId()
        );
    }

    public static PedidoResumoResponse toResumo(Pedido pedido) {
        return new PedidoResumoResponse(
                pedido.getId(),
                pedido.getDescricao(),
                pedido.getStatus()
        );
    }
}
