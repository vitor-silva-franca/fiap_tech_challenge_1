package com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.pedido;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.PedidoRepository;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.pedido.PedidoResumoResponse;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.mapper.PedidoMapper;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.ApplicationException;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.UsuarioNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarPedidosPorUsuarioUseCase {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public BuscarPedidosPorUsuarioUseCase(PedidoRepository pedidoRepository,
                                          UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PedidoResumoResponse> buscarPedidosPorUsuarioId(Long usuarioId) {
        if (usuarioId == null) {
            throw new ApplicationException("ID do usuário é obrigatório para listar pedidos.");
        }

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioId));

        return pedidoRepository.findByUsuarioId(usuarioId).stream()
                .map(PedidoMapper::toResumo)
                .collect(Collectors.toList());
    }
}
