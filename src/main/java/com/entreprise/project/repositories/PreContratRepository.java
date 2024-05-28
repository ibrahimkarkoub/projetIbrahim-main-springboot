package com.entreprise.project.repositories;

import com.entreprise.project.entities.PreContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreContratRepository extends JpaRepository<PreContrat,Long> {

    PreContrat findPreContratByProspectId(long id);
}
