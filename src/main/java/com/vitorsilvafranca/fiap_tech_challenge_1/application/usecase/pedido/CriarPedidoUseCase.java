package com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.pedido;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.Pedido;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.StatusPedido;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.PedidoRepository;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.ApplicationException;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CriarPedidoUseCase {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public CriarPedidoUseCase(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Pedido criar(Pedido pedido) {
        Long usuarioId = pedido.getUsuario().getId();

        if (usuarioId == null) {
            throw new ApplicationException("ID do usuário é obrigatório.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioId));

        Pedido novoPedido = new Pedido(
                pedido.getDescricao(),
                pedido.getStatus() != null ? pedido.getStatus() : StatusPedido.PENDENTE,
                usuario
        );

        return pedidoRepository.save(novoPedido);
    }
}
