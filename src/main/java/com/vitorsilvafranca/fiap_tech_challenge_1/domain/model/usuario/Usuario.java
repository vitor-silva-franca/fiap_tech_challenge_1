package com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario;

import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.DomainException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import java.util.EnumSet;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100)
    private String nome;

    @Email
    @NotBlank(message = "Email é obrigatório")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, max = 100)
    private String senha;

    @NotNull(message = "Role é obrigatória e deve ser 'ADMIN' ou 'CLIENTE'")
    @Enumerated(EnumType.STRING)
    private RoleUsuario role;

    public Usuario(String nome, String email, String senha, RoleUsuario role) {
        validate(nome, email, senha, role);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Usuario(Long id) {
        this.id = id;
    }

    @Deprecated
    protected Usuario() {
        // usado apenas pelo JPA
    }

    private void validate(String nome, String email, String senha, RoleUsuario role) {
        if (nome == null || nome.trim().isEmpty() || nome.length() < 2) {
            throw new DomainException("Nome é obrigatório e deve ter ao menos 2 caracteres");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new DomainException("Email é obrigatório");
        }

        if (senha == null || senha.length() < 6) {
            throw new DomainException("Senha deve conter ao menos 8 caracteres");
        }

        if (role == null) {
            throw new DomainException("Role do usuário é obrigatória");
        }
        if (!EnumSet.of(RoleUsuario.ADMIN, RoleUsuario.CLIENTE).contains(role)) {
            throw new DomainException("Role deve ser ADMIN ou CLIENTE");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public RoleUsuario getRole() {
        return role;
    }

    public void setRole(RoleUsuario role) {
        this.role = role;
    }

}

