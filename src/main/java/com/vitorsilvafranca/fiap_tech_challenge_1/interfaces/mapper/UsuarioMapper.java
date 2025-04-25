package com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.mapper;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.usuario.UsuarioRequest;
import com.vitorsilvafranca.fiap_tech_challenge_1.interfaces.dto.usuario.UsuarioResponse;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.DomainException;

public class UsuarioMapper {

    public static Usuario toModel(UsuarioRequest request) {
        if (request.getRole() == null) {
            throw new DomainException("Role do usuário é obrigatória");
        }
        Usuario usuario = new Usuario(
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getRole());
        return usuario;
    }

    public static UsuarioResponse fromModel(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
