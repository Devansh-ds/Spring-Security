package com.devansh.jwt;

import com.devansh.jwt.User.Role;
import com.devansh.jwt.auth.AuthenticationService;
import com.devansh.jwt.auth.RegisterRequest;
import com.devansh.jwt.config.JwtService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service) {
        return args -> {
            var admin = new RegisterRequest(
                    "Admin",
                    "Admin",
                    "admin@gmail.com",
                    "admin",
                    Role.ADMIN
            );
            System.out.println("Admin token: " + service.register(admin).getAcessToken());

            var manager = new RegisterRequest(
                    "Manager",
                    "Manager",
                    "manager@gmail.com",
                    "manager",
                    Role.MANAGER
            );
            System.out.println("Manager token: " + service.register(manager).getAcessToken());
        };
    }

}
