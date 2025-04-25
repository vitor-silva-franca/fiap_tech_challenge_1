package com.vitorsilvafranca.fiap_tech_challenge_1.application.usecase.usuario;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.Usuario;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository.UsuarioRepository;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.ApplicationException;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.UsuarioJaExisteException;
import com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public AtualizarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario atualizar(Long id, Usuario novosDados) {
        if (id == null) {
            throw new ApplicationException("ID do usuário é obrigatório.");
        }
        if (novosDados == null) {
            throw new ApplicationException("Dados do usuário são obrigatórios.");
        }

        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        boolean emailAlterado = !existente.getEmail().equals(novosDados.getEmail());

        if (emailAlterado && usuarioRepository.existsByEmail(novosDados.getEmail())) {
            throw new UsuarioJaExisteException(novosDados.getEmail());
        }

        existente.setNome(novosDados.getNome());
        existente.setEmail(novosDados.getEmail());
        existente.setRole(novosDados.getRole());
        //existente.setSenha(novosDados.getSenha());

        return usuarioRepository.save(existente);
    }
}
