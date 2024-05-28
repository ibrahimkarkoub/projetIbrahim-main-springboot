package com.entreprise.project.services;

import com.entreprise.project.entities.Client;

import java.util.List;

public interface IClientService {
    Client create(Long preContratId);
    Client getById(Long id);
    Client update(Long id, Client updatedClient);
    void delete(Long id);
    List<Client> getAll();



}
