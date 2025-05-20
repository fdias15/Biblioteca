package org.iftm.projeto;

import java.util.List;

import org.iftm.projeto.entities.Livro;
import org.iftm.projeto.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivroApplication implements CommandLineRunner {

    @Autowired
    private LivroRepository repositorio;

    public static void main(String[] args) {
        SpringApplication.run(LivroApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Cadastrar livros
        Livro livro = new Livro();
        livro.setTitulo("O Pequeno Principe");
        livro.setAutor("Antoine de Saint-Exupéry");
        livro.setGenero("infanto-juvenil");
        livro.setAnoPublicacao(1943);
        livro.setDisponivel(true);
        repositorio.save(livro);

        Livro livro2 = new Livro(null, "A Culpa é das Estrelas", "John Green", "Romance", 2012, true);
        repositorio.save(livro2);

        livro2.setTitulo("Cidade de Papel");
        repositorio.save(livro2);

        // Buscar todos os livros
        List<Livro> livros = repositorio.findAll();
        System.out.println("Relatório geral:");
        for (Livro l : livros) {
            System.out.println(l.getTitulo());
        }

        // Buscar por ID
        Livro busca = repositorio.findById(2L).get();
        System.out.println("Busca individual:");
        System.out.println(busca.getTitulo());

        // Buscar por autor
        List<Livro> listalivros = repositorio.findByAutor("John Green");
        System.out.println("-------- Saída por Autor ----------");
        System.out.println("Quantidade: " + listalivros.size());
        for (Livro l : listalivros) {
            System.out.println("Título: " + l.getTitulo() + " | Autor: " + l.getAutor());
        }

        // Buscar por título exato
        List<Livro> listaLivros = repositorio.findByTitulo("A Culpa é das Estrelas");
        System.out.println("-------- Saída por Título ----------");
        System.out.println("Quantidade: " + listaLivros.size());
        for (Livro l : listaLivros) {
            System.out.println("Título: " + l.getTitulo() + " | Autor: " + l.getAutor());
        }

        // Buscar livros com ano de publicação entre 1940 e 1947
        List<Livro> listaLivrosEntre = repositorio.findByAnoPublicacaoBetween(1940, 1947);
        System.out.println("-------- Saída Ano entre 1940 e 1947 ----------");
        System.out.println(listaLivrosEntre.size());
        System.out.println("Relatório:");
        for (Livro l : listaLivrosEntre) {
            System.out.println("Título: " + l.getTitulo() + " - Ano: " + l.getAnoPublicacao());
        }
        System.out.println("-------- Saída ----------");

        // Buscar por título contendo 'Cidade', ordenado por ano desc
        List<Livro> listaLivrosCidade = repositorio.findByTituloContainingIgnoreCaseOrderByAnoPublicacaoDesc("Cidade");
        System.out.println("-------- Livros com título contendo 'Cidade' --------");
        System.out.println("Quantidade: " + listaLivrosCidade.size());
        for (Livro l : listaLivrosCidade) {
            System.out.println("Título: " + l.getTitulo() + " - Ano: " + l.getAnoPublicacao());
        }
        System.out.println("------------------------------------------------------");
    }
}