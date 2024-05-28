package com.entreprise.project.services;

import com.entreprise.project.entities.Tache;

import java.util.List;

public interface ITacheService {
    Tache createTache(Tache tache);
    Tache updateTache(Tache tache);
    List<Tache> getAllTachesByAssigneeId(Long assigneeId);
    List<Tache> getAllTachesByCreatorId(Long creatorId);
    List<Tache> getAllTaches();
    void deleteTache(Long tacheId);
    void changeStatus(Long tacheId);
}
