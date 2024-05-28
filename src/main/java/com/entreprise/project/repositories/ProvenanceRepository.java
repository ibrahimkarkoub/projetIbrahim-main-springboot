package com.entreprise.project.repositories;

import com.entreprise.project.entities.Provenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvenanceRepository extends JpaRepository<Provenance,Long> {
}
