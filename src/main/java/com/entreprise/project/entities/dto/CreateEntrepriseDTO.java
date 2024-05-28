package com.entreprise.project.entities.dto;

import com.entreprise.project.entities.Utilisateur;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateEntrepriseDTO {
    String userName;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String password;
}
