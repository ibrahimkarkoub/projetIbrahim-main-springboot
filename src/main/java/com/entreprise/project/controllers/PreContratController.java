package com.entreprise.project.controllers;

import com.entreprise.project.entities.PreContrat;
import com.entreprise.project.repositories.PreContratRepository;
import com.entreprise.project.services.IPreContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/precontrats")
public class PreContratController {
    @Autowired
    private IPreContratService preContratService;

    @Autowired
    PreContratRepository preContratRepository;

    @PostMapping("/create/{prospectId}")
    public ResponseEntity<PreContrat> createPreContrat(@PathVariable Long prospectId) {
        PreContrat preContrat = preContratService.create(prospectId);
        return new ResponseEntity<>(preContrat, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PreContrat> getPreContratById(@PathVariable Long id) {
        PreContrat preContrat = preContratService.getById(id);
        return new ResponseEntity<>(preContrat, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PreContrat> updatePreContrat(@PathVariable Long id, @RequestBody PreContrat updatedPreContrat) {
        PreContrat preContrat = preContratService.update(id, updatedPreContrat);
        return new ResponseEntity<>(preContrat, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<PreContrat>> getAllPreContrats() {
        List<PreContrat> preContrats = preContratService.getAll();
        return new ResponseEntity<>(preContrats, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePreContrat(@PathVariable Long id) {
        preContratService.delete(id);

    }
}
