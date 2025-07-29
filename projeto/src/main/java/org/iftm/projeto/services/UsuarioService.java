package org.iftm.projeto.services;

import java.util.List;
import java.util.Optional;

import org.iftm.projeto.entities.Usuario;
import org.iftm.projeto.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        validateUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public List<Usuario> saveAll(List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            validateUsuario(usuario);
        }
        return usuarioRepository.saveAll(usuarios);
    }

    @Transactional
    public Usuario update(Long id, Usuario updatedUsuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário com ID " + id + " não encontrado.");
        }
        validateUsuario(updatedUsuario);
        updatedUsuario.setId(id);
        return usuarioRepository.save(updatedUsuario);
    }

    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário com ID " + id + " não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

    private void validateUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário é obrigatório.");
        }
    }
}
