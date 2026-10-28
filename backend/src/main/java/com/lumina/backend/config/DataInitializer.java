package com.lumina.backend.config;

import com.lumina.backend.model.ERole;
import com.lumina.backend.model.User;
import com.lumina.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedUser("superadmin", "superadmin@lumina.com", "Super Admin", "superadmin123", Set.of(ERole.ROLE_SUPER_ADMIN.name()));
        seedUser("admin", "admin@lumina.com", "System Administrator", "admin123", Set.of(ERole.ROLE_ADMIN.name()));
        seedUser("technician", "tech@lumina.com", "Senior Technician", "tech123", Set.of(ERole.ROLE_TECHNICIAN.name()));
        seedUser("client", "client@lumina.com", "VIP Client", "client123", Set.of(ERole.ROLE_CLIENT.name()));
    }

    private void seedUser(String username, String email, String fullName, String rawPassword, Set<String> roles) {
        if (!userRepository.existsByUsername(username)) {
            User user = User.builder()
                    .username(username)
                    .email(email)
                    .fullName(fullName)
                    .password(passwordEncoder.encode(rawPassword))
                    .roles(roles)
                    .enabled(true)
                    .build();
            userRepository.save(user);
            log.info("Seeded initial user: {} with roles {}", username, roles);
        }
    }
}
