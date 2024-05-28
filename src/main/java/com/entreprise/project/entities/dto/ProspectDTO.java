package com.entreprise.project.entities.dto;

import com.entreprise.project.enums.Gender;
import com.entreprise.project.enums.ReseauxSociaux;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProspectDTO {
    @NotNull
    private String numeroTelephone;
    private String nom;
    private String prenom;
    private String adresse;
    private String job;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender genre;
    @Enumerated(EnumType.STRING)
    private ReseauxSociaux reseauxSociaux;
    private String Description;
}
