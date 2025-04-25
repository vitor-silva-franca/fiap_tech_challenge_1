package com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.pedido;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.Pedido;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.PedidoRepository;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.ApplicationException;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.PedidoNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AtualizarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public AtualizarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido atualizar(Long id, Pedido novosDados) {
        if (id == null) {
            throw new ApplicationException("ID do pedido é obrigatório.");
        }

        Pedido existente = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));

        existente.atualizarDados(novosDados.getDescricao(), novosDados.getStatus());

        return pedidoRepository.save(existente);
    }
}
