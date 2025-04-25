package com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.usuario;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.ApplicationException;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public BuscarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(Long id) {
        if (id == null) {
            throw new ApplicationException("ID do usuário é obrigatório.");
        }
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }
}
