package com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.pedido;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.Pedido;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.PedidoRepository;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.ApplicationException;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.PedidoNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuscarPedidoUseCase {
    
    private final PedidoRepository pedidoRepository;

    public BuscarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscarPorId(Long id) {
        if (id == null) {
            throw new ApplicationException("ID do pedido é obrigatório.");
        }
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));
    }
}
