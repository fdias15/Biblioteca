package org.iftm.client_api_rest.service;

import java.util.List;
import java.util.Optional;

import org.iftm.client_api_rest.entities.Livro;
import org.iftm.client_api_rest.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    // Buscar todos os livros
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    // Buscar livro por ID
    public Optional<Livro> findById(Long id) {
        return livroRepository.findById(id);
    }

    // Buscar livros por título (ignorando maiúsculas/minúsculas)
    public List<Livro> findByTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Buscar livros por autor
    public List<Livro> findByAutor(String autor) {
        return livroRepository.findByAutor(autor);
    }

    // Buscar livros publicados após determinado ano
    public List<Livro> findByAnoPublicacaoGreaterThan(int ano) {
        return livroRepository.findByAnoPublicacaoGreaterThan(ano);
    }

    // Buscar apenas livros disponíveis
    public List<Livro> findDisponiveis() {
        return livroRepository.findByDisponivel(true);
    }

    // Inserir um novo livro
    @Transactional
    public Livro save(Livro livro) {
        validateLivro(livro);
        return livroRepository.save(livro);
    }

    // Inserir vários livros
    @Transactional
    public List<Livro> saveAll(List<Livro> livros) {
        for (Livro livro : livros) {
            validateLivro(livro);
        }
        return livroRepository.saveAll(livros);
    }

    // Atualizar um livro existente
    @Transactional
    public Livro update(Long id, Livro updatedLivro) {
        if (!livroRepository.existsById(id)) {
            throw new IllegalArgumentException("Livro com ID " + id + " não encontrado.");
        }
        validateLivro(updatedLivro);
        updatedLivro.setId(id); // Mantém o ID original
        return livroRepository.save(updatedLivro);
    }

    // Deletar um livro por ID
    public void deleteById(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new IllegalArgumentException("Livro com ID " + id + " não encontrado.");
        }
        livroRepository.deleteById(id);
    }

    // Deletar todos os livros
    public void deleteAll() {
        livroRepository.deleteAll();
    }

    // Validações de negócio
    private void validateLivro(Livro livro) {
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O título do livro é obrigatório.");
        }
        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("O autor do livro é obrigatório.");
        }
        if (livro.getAnoPublicacao() <= 0) {
            throw new IllegalArgumentException("O ano de publicação deve ser maior que zero.");
        }
    }
}