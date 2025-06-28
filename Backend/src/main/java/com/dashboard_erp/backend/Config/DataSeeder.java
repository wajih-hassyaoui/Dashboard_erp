package com.dashboard_erp.backend.Config;

import com.dashboard_erp.backend.Entity.User;
import com.dashboard_erp.backend.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.CharBuffer;

import static com.dashboard_erp.backend.Entity.Role.admin;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(User.builder()
                        .firstName("Wajih")
                        .lastName("Hassyaoui")
                        .email("wajih@gmail.com")
                        .password(passwordEncoder.encode(CharBuffer.wrap("123456")))
                        .phone("24548648")
                        .address("ben arous")
                        .role(admin)// You should encode it if using Spring Security
                        .build());
            }
        };
    }
}
