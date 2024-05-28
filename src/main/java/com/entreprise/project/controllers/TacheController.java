package com.entreprise.project.controllers;

import com.entreprise.project.entities.Tache;
import com.entreprise.project.entities.Utilisateur;
import com.entreprise.project.entities.dto.TacheDTO;
import com.entreprise.project.enums.TacheStatus;
import com.entreprise.project.exceptions.DataNotFoundException;
import com.entreprise.project.repositories.TacheRepository;
import com.entreprise.project.repositories.UserRepository;
import com.entreprise.project.services.ITacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/taches")
public class TacheController {
    @Autowired
    private TacheRepository tacheRepository ;
    @Autowired
    private ITacheService tacheService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping(path = "/add/{assignId}/{creatorId}")
    public ResponseEntity<Tache> createTache(@PathVariable("assignId") Long idAssign,@PathVariable("creatorId") Long idCreator,@RequestBody TacheDTO tache) {
        Utilisateur creatorUser = userRepository.findById(idCreator).orElse(null);
        Utilisateur assignUser = userRepository.findById(idAssign).orElse(null);
        if(creatorUser == null){
            throw new DataNotFoundException("creator User not found");
        }else if(assignUser == null){
            throw new DataNotFoundException("Assign User not found");
        }else{
            Tache tache1 = Tache.builder().build();
            tache1.setAssigne(assignUser);
            tache1.setCreateur(creatorUser);
            tache1.setDateAffectation(LocalDate.now());
            tache1.setDateDeadline(tache.getDateDeadline());
            tache1.setDescription(tache.getDescription());
            tache1.setActive(true);
            tache1.setStatus(TacheStatus.OUVERT);
            Tache createdTache = tacheService.createTache(tache1);
            return new ResponseEntity<>(createdTache, HttpStatus.CREATED);
        }

    }
    @PutMapping("/cloturer/{tacheId}")
    public ResponseEntity<String> cloturerTache(@PathVariable Long tacheId) {
        try {
            tacheService.changeStatus(tacheId);
            return ResponseEntity.ok("Tache clôturée avec succès");
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable("id") Long id, @RequestBody Tache tache) {
        tache.setId(id);
        Tache updatedTache = tacheService.updateTache(tache);
        if (updatedTache != null) {
            return new ResponseEntity<>(updatedTache, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByAssignee/{assigneeId}")
    public ResponseEntity<List<Tache>> getAllTachesByAssigneeId(@PathVariable("assigneeId") Long assigneeId) {
        List<Tache> taches = tacheService.getAllTachesByAssigneeId(assigneeId);
        return new ResponseEntity<>(taches, HttpStatus.OK);
    }

    @GetMapping("/getByCreator/{creatorId}")
    public ResponseEntity<List<Tache>> getAllTachesByCreatorId(@PathVariable("creatorId") Long creatorId) {
        List<Tache> taches = tacheService.getAllTachesByCreatorId(creatorId);
        return new ResponseEntity<>(taches, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Tache>> getAllTaches() {
        List<Tache> taches = tacheService.getAllTaches();
        return new ResponseEntity<>(taches, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTache(@PathVariable("id") Long id) {
        Tache tache = tacheRepository.findById(id).orElse(null);
        tache.setActive(false);
        tacheRepository.save(tache);

    }
    @DeleteMapping("/delete-forceForce/{id}")
    public void deleteTacheForce(@PathVariable("id") Long id) {
        tacheRepository.deleteById(id);

    }
}
