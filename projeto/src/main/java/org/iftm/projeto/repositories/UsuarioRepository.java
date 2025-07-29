package org.iftm.projeto.repositories;

import java.util.List;

import org.iftm.projeto.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por nome exato
    List<Usuario> findByNome(String nome);

    // Buscar por parte do nome (ignorando maiúsculas/minúsculas)
    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    // Buscar usuários que pegaram livros (relacionamento 1:N com Livro)
    // Isso depende de ter o relacionamento no model com: List<Livro>
    // livrosEmprestados
}