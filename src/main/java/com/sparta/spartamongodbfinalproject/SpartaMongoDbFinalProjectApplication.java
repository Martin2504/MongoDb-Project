package com.sparta.spartamongodbfinalproject;


import com.sparta.spartamongodbfinalproject.model.entities.User;
import com.sparta.spartamongodbfinalproject.model.entities.roles.ERole;
import com.sparta.spartamongodbfinalproject.model.entities.roles.Role;
import com.sparta.spartamongodbfinalproject.model.repositories.CommentRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.RoleRepository;
import com.sparta.spartamongodbfinalproject.model.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class SpartaMongoDbFinalProjectApplication {

    public static final Logger logger = LoggerFactory.getLogger(SpartaMongoDbFinalProjectApplication.class);
   
    public static void main(String[] args) {
        SpringApplication.run(SpartaMongoDbFinalProjectApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner runner(CommentsRepository commentsRepository){
//        return args -> logger.info(commentsRepository.findCommentsByNameEquals("John Bishop").toString());
//    }

//    @Bean
//	public CommandLineRunner runner(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//		 return args -> {
//			 User user = new User();
//			 Role roleAdmin = new Role();
//			 roleAdmin.setName(ERole.ROLE_ADMIN);
//			 roleAdmin.setId("1");
//			 Role roleBasic = new Role();
//			 roleBasic.setName(ERole.ROLE_BASIC);
//			 roleBasic.setId("2");
//			 roleRepository.save(roleAdmin);
//			 roleRepository.save(roleBasic);
//             user.setEmail("admin@tech203.com");
//             user.setPassword(passwordEncoder.encode("admin"));
//             user.setName("John Smith");
//             user.setRoles(Set.of(roleAdmin, roleBasic));
//			 userRepository.save(user);
//		 };
//	}

//	@Bean
//	public CommandLineRunner runnerCheckUserExists(UserRepository userRepository) {
//		return args -> {
//
//			User user = userRepository.findByEmail("admin@tech203.com").get();
//			System.out.println(user);
//			Set<Role> roles = user.getRoles();
//			for (Iterator<Role> it = roles.iterator(); it.hasNext(); ) {
//				System.out.println(it.next().getName().name());
//			}
//		};
//	}
}
