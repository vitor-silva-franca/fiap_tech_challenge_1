package com.vitorsilvafranca.fiap_tech_challenge_1.infrastructure.repository;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido.Pedido;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.PedidoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepositoryJpa extends PedidoRepository, JpaRepository<Pedido, Long> {
    public List<Pedido> findByUsuarioId(Long usuarioId);
}
