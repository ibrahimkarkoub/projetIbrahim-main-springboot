package com.entreprise.project.services;

import com.entreprise.project.entities.Client;
import com.entreprise.project.entities.PreContrat;
import com.entreprise.project.exceptions.DataNotFoundException;
import com.entreprise.project.repositories.ClientRepository;
import com.entreprise.project.repositories.PreContratRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PreContratRepository preContratRepository;
    @Override
    public Client create(Long preContratId) {
        PreContrat preContrat = preContratRepository.findById(preContratId)
                .orElseThrow(() -> new DataNotFoundException("PreContrat not found with id: " + preContratId));

        Client client = Client.builder()
                .preContrat(preContrat)
                .DateAjout(LocalDate.now())
                .build();

        return clientRepository.save(client);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Client not found with id: " + id));
    }

    @Override //Indique que cette méthode implémente une méthode définie dans l'interface IClientService.
    @Transactional
    public Client update(Long id, Client updatedClient) {
        Client client = getById(id);
        client.setPreContrat(updatedClient.getPreContrat());
        client.setDateAjout(LocalDate.now());

        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }


}
