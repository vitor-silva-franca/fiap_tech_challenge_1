package com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(Long id) {
        super("Pedido com ID " + id + " não encontrado.");
    }
}
