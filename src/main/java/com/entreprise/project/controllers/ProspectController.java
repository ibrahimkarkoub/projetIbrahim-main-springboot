package com.entreprise.project.controllers;

import com.entreprise.project.entities.Client;
import com.entreprise.project.entities.PreContrat;
import com.entreprise.project.entities.Prospect;
import com.entreprise.project.entities.dto.ProspectDTO;
import com.entreprise.project.repositories.ClientRepository;
import com.entreprise.project.repositories.PreContratRepository;
import com.entreprise.project.repositories.ProspectRepository;
import com.entreprise.project.services.IProspectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/prospects")
public class ProspectController {
    @Autowired
    private IProspectService prospectService;

    @Autowired
    private ProspectRepository prospectRepository;
    @Autowired
    private PreContratRepository preContratRepository ;
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/add/{employeeId}")
    public ResponseEntity<Prospect> addProspect(@PathVariable Long employeeId, @RequestBody ProspectDTO prospectDTO) {
        Prospect prospect = prospectService.add(employeeId, prospectDTO);
        return new ResponseEntity<>(prospect, HttpStatus.CREATED);
    }

    @PutMapping("/update/{prospectId}")
    public ResponseEntity<Prospect> updateProspect(@PathVariable Long prospectId, @RequestBody ProspectDTO prospectDTO) {
        Prospect updatedProspect = prospectService.update(prospectId, prospectDTO);
        return new ResponseEntity<>(updatedProspect, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Prospect>> getAllProspects() {
        List<Prospect> prospects = prospectService.getAll();
        return new ResponseEntity<>(prospects, HttpStatus.OK);
    }

    @GetMapping("/getAllByEntreprise/{id}")
    public ResponseEntity<List<Prospect>> getProspectsByEntrerise(@PathVariable Long id) {
        List<Prospect> prospects = prospectService.getAll().stream()
                .filter(prospect -> prospect.getUtilisateur().getEntreprise().getId().equals(id))
                .toList();
        return new ResponseEntity<>(prospects, HttpStatus.OK);
    }
@Transactional
    @DeleteMapping("/delete/{prospectId}")
    public void deleteProspect(@PathVariable Long prospectId) {
        PreContrat preContrat = preContratRepository.findPreContratByProspectId(prospectId);
        if(preContrat != null){
            Client client = clientRepository.findClientByPreContratId(preContrat.getId());
            if(client != null){
                clientRepository.deleteById(client.getId());
                preContratRepository.deleteById(preContrat.getId());
            }
        }




        prospectRepository.deleteById(prospectId);
    }
}
