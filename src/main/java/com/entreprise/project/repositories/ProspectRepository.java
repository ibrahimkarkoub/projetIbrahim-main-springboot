package com.entreprise.project.repositories;

import com.entreprise.project.entities.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProspectRepository extends JpaRepository<Prospect,Long> {
    List<Prospect> findByUtilisateurId(long id);
}
