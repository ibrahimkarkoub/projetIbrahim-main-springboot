package com.entreprise.project.entities;

import com.entreprise.project.enums.Role;
import com.entreprise.project.enums.TacheStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    private TacheStatus status = TacheStatus.OUVERT;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "createur_id")
    private Utilisateur createur;
    private Boolean active = true;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigne_id")
    private Utilisateur assigne;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAffectation = LocalDate.now();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDeadline;
}
