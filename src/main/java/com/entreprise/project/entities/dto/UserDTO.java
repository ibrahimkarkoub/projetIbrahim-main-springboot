package com.entreprise.project.entities.dto;

import com.entreprise.project.entities.Tache;
import com.entreprise.project.entities.Utilisateur;
import com.entreprise.project.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    Long id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String password;
    private Utilisateur entreprise;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate HiringDate = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Role role;
    List<Utilisateur> users;
    List<Tache> tachescreated;
    List<Tache> tachesassined;

}
