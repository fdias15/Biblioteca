package org.iftm.projeto.repositories;

import java.util.List;

import org.iftm.projeto.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    // Métodos adicionais de consulta podem ser adicionados aqui, se necessário

    List<Livro> findByAnoPublicacaoGreaterThan(int ano);

    List<Livro> findByDisponivel(boolean b);

    List<Livro> findByAutor(String autor);
}