package com.appsdev.PaymentSystem.security;

import com.appsdev.PaymentSystem.security.domain.User;
import com.appsdev.PaymentSystem.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final static String USER = "user";
    private final static String ADMIN = "admin";
    @Autowired
    UserRepository users;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (!users.findByUsername(USER).isPresent()) {
            this.users.save(User.builder()
                    .username(USER)
                    .password(this.passwordEncoder.encode("password"))
                    .roles(Arrays.asList("ROLE_USER"))
                    .build()
            );
        }
        if (!users.findByUsername(ADMIN).isPresent()) {
            this.users.save(User.builder()
                    .username(ADMIN)
                    .password(this.passwordEncoder.encode("password"))
                    .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                    .build()
            );
        }
        log.debug("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}