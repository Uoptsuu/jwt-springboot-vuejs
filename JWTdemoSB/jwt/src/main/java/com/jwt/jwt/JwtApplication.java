package com.jwt.jwt;

import com.jwt.jwt.service.RoleService;
import com.jwt.jwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService, RoleService roleService){
//		return args -> {
//			roleService.saveRole(new Role(null,"ROLE_USER"));
//			roleService.saveRole(new Role(null,"ROLE_ADMIN"));

//			userService.saveUser(new User(null,"user1","1","Bac ninh"));
//			userService.saveUser(new User(null,"user2","2","Bac giang"));
//			userService.saveUser(new User(null,"user3","3","Hung yen"));
//			userService.saveUser(new User(null,"user4","4","Ninh binh"));
//			userService.saveUser(new User(null,"admin2","2","Tuyen quang"));
//			userService.saveUser(new User(null,"admin10","10","Vinh phuc"));
//			userService.setRole("admin10","ROLE_ADMIN");
//			userService.setRole("user2","ROLE_USER");
//			userService.setRole("user3","ROLE_USER");
//			userService.setRole("user4","ROLE_USER");
//			userService.setRole("admin2","ROLE_ADMIN");
//			userService.setRole("admin3","ROLE_ADMIN");
//		};
//	}
}
