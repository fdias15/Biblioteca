package org.iftm.projeto.repositories;

import java.util.List;

import org.iftm.projeto.entities.Livro;
import org.iftm.projeto.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Livro, Long> {
    // Encontra todos os livros emprestados por um usuário específico
    List<Livro> findByUsuario(Usuario usuario);

    // Encontra livros emprestados e disponíveis
    List<Livro> findByUsuarioAndDisponivelTrue(Usuario usuario);
}