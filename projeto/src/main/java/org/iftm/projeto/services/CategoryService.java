package org.iftm.projeto.services;

import java.util.List;
import java.util.Optional;

import org.iftm.projeto.entities.Category;
import org.iftm.projeto.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findByNome(String nome) {
        return categoryRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Category save(Category category) {
        validateCategory(category);
        return categoryRepository.save(category);
    }

    @Transactional
    public List<Category> saveAll(List<Category> categorias) {
        for (Category c : categorias) {
            validateCategory(c);
        }
        return categoryRepository.saveAll(categorias);
    }

    @Transactional
    public Category update(Long id, Category updatedCategory) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoria com ID " + id + " não encontrada.");
        }
        validateCategory(updatedCategory);
        updatedCategory.setId(id);
        return categoryRepository.save(updatedCategory);
    }

    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoria com ID " + id + " não encontrada.");
        }
        categoryRepository.deleteById(id);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    private void validateCategory(Category category) {
        if (category.getNome() == null || category.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        }
    }
}
