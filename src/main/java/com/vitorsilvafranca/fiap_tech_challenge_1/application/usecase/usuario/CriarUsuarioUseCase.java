package com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.usuario;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.ApplicationException;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.UsuarioJaExisteException;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioUseCase {

    private final UsuarioRepository repo;

    public CriarUsuarioUseCase(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario criar(Usuario usuario) {
        if (usuario.getEmail() == null) {
            throw new ApplicationException("Email é obrigatório.");
        }
        if (repo.existsByEmail(usuario.getEmail())) {
            throw new UsuarioJaExisteException(usuario.getEmail());
        }
        return repo.save(usuario);
    }
}
