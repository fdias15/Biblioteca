package org.iftm.projeto;

import java.util.List;

import org.iftm.projeto.entities.Livro;
import org.iftm.projeto.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*  modifiquei a classe incluindo a implementação da interface
 commandLineRunner que permite inserir código a ser executado na
 inicialização do servidor.
*/
@SpringBootApplication
public class LivroApplication implements CommandLineRunner {
    // injeção de dependencia da classe ClientRepository.
    // permite ao SpringBoot instanciar objetos dessa classe.
    @Autowired
    private LivroRepository repositorio;

    public static void main(String[] args) {
        SpringApplication.run(LivroApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Instanciar um objeto da classe Client
        Livro livro = new Livro();
        livro.setTitulo("O Pequeno Principe");
        livro.setAutor("Antoine de Saint-Exupéry");
        livro.setGenero("infanto-juvenil");
        livro.setAnoPublicacao(1943);
        livro.setDisponivel(true);
        repositorio.save(livro);

        Livro livro2 = new Livro(null, "A Culpa é das Estrelas", "John Green", "Romance", 2012, true);
        repositorio.save(livro2);

        livro2.setTitulo("Mar de Estrelas");
        repositorio.save(livro2);

        // repositorio.deleteAll();
        // repositorio.deleteById(2L);
        List<Livro> livros = repositorio.findAll();
        System.out.println("Relatório:::::");
        for (int i = 0; i < livros.size(); i++) {
            System.out.println(livros.get(i).getTitulo());
        }

        // utilizando o método de busca por id, que é a chave primaria, ou seja,
        // retorna apenas um elemento
        // ele retorna um objeto Optional, o metodo get() retorna o Client
        Livro busca = repositorio.findById(2L).get();
        System.out.println("Busca individual:");
        System.out.println(busca.getTitulo());
    }

}