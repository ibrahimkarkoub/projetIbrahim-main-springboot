package com.entreprise.project.controllers;


import com.entreprise.project.entities.Client;
import com.entreprise.project.repositories.ClientRepository;
import com.entreprise.project.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/create/{preContratId}")
    public ResponseEntity<Client> createClient(@PathVariable Long preContratId) {
        Client client = clientService.create(preContratId);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client client = clientService.update(id, updatedClient);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
