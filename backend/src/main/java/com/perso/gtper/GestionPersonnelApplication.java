package com.perso.gtper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionPersonnelApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionPersonnelApplication.class, args);
	}

	// @Bean
	// CommandLineRunner start(UserService userService, UserRepository userRepository) {
	// 	return args -> {
	// 		Role admin = new Role(ERole.ADMIN);
	// 		Role rh = new Role(ERole.RH);
	// 		Role bum = new Role(ERole.BUM);

	// 		userService.addNewRole(admin);
	// 		userService.addNewRole(rh);
	// 		userService.addNewRole(bum);
	// 	};
	// }

}
