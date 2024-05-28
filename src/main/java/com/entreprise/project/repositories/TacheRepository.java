package com.entreprise.project.repositories;

import com.entreprise.project.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheRepository extends JpaRepository<Tache,Long> {
    List<Tache> findByAssigne_Id(Long id);
    List<Tache> findByCreateur_Id(Long id);
}
