package com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido;

public enum StatusPedido {
    PENDENTE, EM_ANDAMENTO, CONCLUIDO, CANCELADO;

    public static boolean isValido(String status) {
        for (StatusPedido s : values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}
