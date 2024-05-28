package com.entreprise.project;

import com.entreprise.project.entities.Utilisateur;
import com.entreprise.project.enums.Role;
import com.entreprise.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitialDataLoader implements CommandLineRunner {
    public final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public InitialDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByUserName("admin").isPresent()){
            System.out.println("L'admin existe déjà, aucune action nécessaire.");
        }else{
            Utilisateur admin = Utilisateur.builder().build();
            admin.setUserName("admin");
            admin.setPhoneNumber("23232323");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setLastName("admin");
            admin.setFirstName("admin");
            admin.setRole(Role.ADMIN);
            admin.setHiringDate(LocalDate.now());
            admin.setEmail("admin@gmail.com");
            userRepository.save(admin);
            System.out.println("Admin ajouté avec succès !");
        }
    }
}
