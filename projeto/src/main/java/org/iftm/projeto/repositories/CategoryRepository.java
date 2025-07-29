package org.iftm.projeto.repositories;

import java.util.List;

import org.iftm.projeto.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Buscar por nome exato
    List<Category> findByNome(String nome);

    // Buscar categorias que contenham parte do nome (case insensitive)
    List<Category> findByNomeContainingIgnoreCase(String nome);
}