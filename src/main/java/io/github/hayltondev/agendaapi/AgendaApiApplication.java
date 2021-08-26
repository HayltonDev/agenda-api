package io.github.hayltondev.agendaapi;

import io.github.hayltondev.agendaapi.model.entity.Contato;
import io.github.hayltondev.agendaapi.model.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgendaApiApplication {


	@Bean //com @Bean o commandLineRunner vai executar sempre que a app subir.
	public CommandLineRunner commandLineRunner(@Autowired ContatoRepository repository){
		return args -> {
			Contato contato = new Contato();
			contato.setNome("Haylton2");
			contato.setEmail("haylton-email@gmail.com");
			contato.setFavorito(false);
			repository.save(contato);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AgendaApiApplication.class, args);
	}

}
