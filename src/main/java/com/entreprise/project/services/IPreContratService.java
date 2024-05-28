package com.entreprise.project.services;

import com.entreprise.project.entities.PreContrat;

import java.util.List;

public interface IPreContratService {
    PreContrat create(Long prospectId);
    PreContrat getById(Long id);
    PreContrat update(Long id, PreContrat updatedPreContrat);
    void delete(Long id);
    List<PreContrat> getAll();
}
