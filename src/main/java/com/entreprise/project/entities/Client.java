package com.entreprise.project.entities;

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
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private Boolean active = true;
    @OneToOne
    private PreContrat preContrat;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate DateAjout = LocalDate.now();
}
