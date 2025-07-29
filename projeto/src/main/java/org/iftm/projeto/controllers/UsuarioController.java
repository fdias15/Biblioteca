package org.iftm.projeto.controllers;

import java.util.List;
import java.util.Optional;

import org.iftm.projeto.entities.Usuario;
import org.iftm.projeto.entities.Livro;
import org.iftm.projeto.services.UsuarioService;
import org.iftm.projeto.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    // GET /usuarios
    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    // GET /usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /usuarios
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    // PUT /usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(usuarioService.update(id, usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /usuarios/{id}/livros
    @GetMapping("/{id}/livros")
    public ResponseEntity<List<Livro>> getLivrosEmprestados(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return ResponseEntity.ok(usuario.getLivrosEmprestados());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /usuarios/{id}/livros/{livroId}
    @PostMapping("/{id}/livros/{livroId}")
    public ResponseEntity<String> emprestarLivro(@PathVariable Long id, @PathVariable Long livroId) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id);
        Optional<Livro> livroOpt = livroService.findById(livroId);

        if (usuarioOpt.isEmpty() || livroOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Livro livro = livroOpt.get();
        if (!livro.isDisponivel()) {
            return ResponseEntity.badRequest().body("Livro não está disponível.");
        }

        livro.setDisponivel(false);
        livro.setUsuario(usuarioOpt.get());
        livroService.save(livro);
        return ResponseEntity.ok("Livro emprestado com sucesso.");
    }
}