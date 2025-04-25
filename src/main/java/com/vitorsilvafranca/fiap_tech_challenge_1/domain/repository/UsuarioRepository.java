package com.vitorsilvafranca.fiap_tech_challenge_1.domain.repository;

import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.Usuario;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> findById(Long id);
    boolean existsByEmail(String email);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
}
