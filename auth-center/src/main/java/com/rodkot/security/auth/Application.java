package com.rodkot.security.auth;

import com.rodkot.security.auth.entity.User;
import com.rodkot.security.auth.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run (UserService userService){
        Role role = new Role(null,"USER");
        Role role2 = new Role(null,"ADMIN");

        User user = new User(null,"admin","admin", List.of(role2),"email","rodion");

        return args -> {

           // userService.saveRole(role2);
           //userService.saveUser(user);
        };
    }
}
