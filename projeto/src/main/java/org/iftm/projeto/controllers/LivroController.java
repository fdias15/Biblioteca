package org.iftm.projeto.controllers;

import java.util.List;
import java.util.Optional;

import org.iftm.projeto.entities.Livro;
import org.iftm.projeto.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Anotação que define a criação de uma API REST
@RestController
// Define o endereço (URI) no qual a API irá responder
// Exemplo: localhost:8080/livros
@RequestMapping("/livros")
// Dá permissão a qualquer URI acessar a API
@CrossOrigin(origins = "*")
public class LivroController {

    // Injeção de dependência da camada Service
    @Autowired
    private LivroService service;

    // Endpoint para buscar todos os livros
    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> livros = service.findAll();
        if (!livros.isEmpty()) {
            return ResponseEntity.ok(livros);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para buscar livros por título (parcial, case insensitive)
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Livro>> findByTitulo(@PathVariable String titulo) {
        List<Livro> livros = service.findByTitulo(titulo);
        if (!livros.isEmpty()) {
            return ResponseEntity.ok(livros);
        } else {
            System.out.println(titulo);
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para buscar livros por autor
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<Livro>> findByAutor(@PathVariable String autor) {
        List<Livro> livros = service.findByAutor(autor);
        if (!livros.isEmpty()) {
            return ResponseEntity.ok(livros);
        } else {
            System.out.println(autor);
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para buscar livro por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id) {
        Optional<Livro> livro = service.findById(id);
        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para criar um novo livro
    @PostMapping
    public ResponseEntity<Livro> create(@RequestBody Livro livro) {
        try {
            Livro livroSalvo = service.save(livro);
            return ResponseEntity.ok(livroSalvo);
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint para atualizar um livro
    @PutMapping
    public ResponseEntity<Livro> update(@RequestBody Livro livro) {
        try {
            Livro livroAtualizado = service.update(livro.getId(), livro);
            return ResponseEntity.ok(livroAtualizado);
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint para deletar um livro por ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return "Livro deletado com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}