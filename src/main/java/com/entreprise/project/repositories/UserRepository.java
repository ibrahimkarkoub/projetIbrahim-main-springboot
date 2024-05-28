package com.entreprise.project.repositories;

import com.entreprise.project.entities.Utilisateur;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByUserName(String username);
    List<Utilisateur> findByEntreprise_Id(Long id);

}