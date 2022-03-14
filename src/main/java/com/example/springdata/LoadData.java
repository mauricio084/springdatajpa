package com.example.springdata;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.example.springdata.service.UserService;
import com.example.springdata.springjpa.model.User;

@Configuration
class LoadData {

	@Bean
	CommandLineRunner initDatabase(UserService userService) {
		return args -> {
			System.out.println("Probando la capa de servicios");
			
			User user = new User();
			user.setFirstname("Juanito");
			user.setLastname("Ramirez");
			user.setAge(31);
			user.setActive(true);
			userService.save(user);
			
			user = new User();
			user.setFirstname("Pepito");
			user.setLastname("Perez");
			user.setAge(5);
			user.setActive(false);
			userService.save(user);
			
			// Busca por id
			user = userService.findById(1L).get();
			
			// Actualiza datos del usuario
			user.setActive(false);
			user.setStartDate(new Date());
			userService.save(user);
			
			// Accediendo a la segunda pagina de User para un tamaño de pagina de 10,
			// ordenando por firstname de manera ascendente
			Page<User> page = userService.getUsers(0, 10, Sort.by(Direction.ASC, "firstname"));
			if (page.hasContent()) {
				System.out.println("Total de elementos:"+ page.getTotalElements());
				System.out.println("Total de paginas:"+ page.getTotalPages());
				imprimir("Usuarios de la pagina: "+ page.getNumber(),  page.getContent());
			}
			
		};
	}

	private void imprimir(String message, List<User> users) {
		System.out.println(message);
		
		System.out.println("Usuarios encontrados: "+users.size());
		
		for (User u : users) {
			System.out.println(u);
		}
	}
}
