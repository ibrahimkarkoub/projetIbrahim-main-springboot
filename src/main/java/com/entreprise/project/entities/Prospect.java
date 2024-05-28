package com.entreprise.project.entities;

import com.entreprise.project.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prospect implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    private String numeroTelephone;
    private String nom;
    private String prenom;
    private String adresse;
    private String job;
    private Boolean active = true;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender genre;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provenance_id", referencedColumnName = "id")
    private Provenance provenance;

    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur utilisateur;
}
