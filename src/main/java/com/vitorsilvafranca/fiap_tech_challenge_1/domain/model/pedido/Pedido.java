package com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.pedido;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.DomainException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.JoinColumn;

import java.util.EnumSet;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Descrição do pedido é obrigatória")
    @Size(min=3, message = "Descrição do pedido deve ter ao menos 3 caracteres")
    private String descricao;

    @NotNull(message = "Status do pedido deve ser 'PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDO' ou 'CANCELADO'")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @NotNull(message = "Usuário associado ao pedido é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Pedido(String descricao, StatusPedido status, Usuario usuario) {
        validate(descricao, status, usuario);
        this.descricao = descricao;
        this.status = status;
        this.usuario = usuario;
    }

    @Deprecated
    protected Pedido() {
        // construtor padrão para uso do JPA
    }

    public void atualizarDados(String novaDescricao, StatusPedido novoStatus) {
        validate(novaDescricao, novoStatus, this.usuario);
        this.descricao = novaDescricao;
        this.status = novoStatus;
    }

    private void validate(String descricao, StatusPedido status, Usuario usuario) {
        if (descricao == null || descricao.trim().isEmpty() || descricao.length() < 3) {
            throw new DomainException("Descrição do pedido é obrigatória e deve ter ao menos 3 caracteres.");
        }

        if (status == null) {
            throw new DomainException("Status do pedido é obrigatório.");
        }

        if (!EnumSet.allOf(StatusPedido.class).contains(status)) {
            throw new DomainException("Status do pedido deve ser 'PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDO' ou 'CANCELADO'");
        }

        if (usuario == null || usuario.getId() == null) {
            throw new DomainException("Usuário associado ao pedido é obrigatório.");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
