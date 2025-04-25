package com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.usuario;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.RoleUsuario;

public class UsuarioResponse {

    private Long id;

    private String nome;

    private String email;

    private RoleUsuario role;

    public UsuarioResponse(Long id, String nome, String email, RoleUsuario role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public RoleUsuario getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
}
