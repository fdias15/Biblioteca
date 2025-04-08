package org.iftm.projeto;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.Instantiator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//anotação que indica ao JPA que essa classe 
@Entity

public class Client implements Serializable { private long id; 
	private String name; 
	private String cpf; 
	private double income; 
	private Instant birthDate; 
	private Int children; 
}   

	//atributos

	//anotação da chave primaria da entidade no BD
	@Id
	//Anotação que define a estrategia de geração 
	@GeneratedValue(strategy = GenerationType.IDENTITY)


	public static void main(String[] args) {
		SpringApplication.run(Client.class, args);
	}

}
