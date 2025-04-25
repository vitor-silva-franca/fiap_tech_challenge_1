package com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    Optional<Pedido> findById(Long id);
    List<Pedido> findByUsuarioId(Long usuarioId);
    Pedido save(Pedido pedido);
    void deleteById(Long id);
}
