package com.entreprise.project.entities;

import com.entreprise.project.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String password;
    //?
    @ManyToOne
    @JoinColumn(name = "Entreprise")
    private Utilisateur entreprise;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate HiringDate = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "createur",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tache> tachesCrees;

    @OneToMany(mappedBy = "assigne",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tache> tachesAssignees;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    List<Prospect> prospects;
}
