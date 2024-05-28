package com.entreprise.project.services;

import com.entreprise.project.entities.Prospect;
import com.entreprise.project.entities.Provenance;
import com.entreprise.project.entities.Utilisateur;
import com.entreprise.project.entities.dto.ProspectDTO;
import com.entreprise.project.exceptions.DataNotFoundException;
import com.entreprise.project.repositories.ProspectRepository;
import com.entreprise.project.repositories.ProvenanceRepository;
import com.entreprise.project.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectServiceImpl implements IProspectService {
    @Autowired
    ProspectRepository prospectRepository;
    @Autowired
    ProvenanceRepository provenanceRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Prospect add(Long userId, ProspectDTO prospectDTO) {
        Utilisateur user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new DataNotFoundException("user not exist");
        }
        Prospect prospect = new Prospect();
        prospect.setNumeroTelephone(prospectDTO.getNumeroTelephone());
        prospect.setNom(prospectDTO.getNom());
        prospect.setPrenom(prospectDTO.getPrenom());
        prospect.setAdresse(prospectDTO.getAdresse());
        prospect.setJob(prospectDTO.getJob());
        prospect.setAge(prospectDTO.getAge());
        prospect.setGenre(prospectDTO.getGenre());
        prospect.setUtilisateur(user);
        Provenance provenance = new Provenance();
        provenance.setReseauxSociaux(prospectDTO.getReseauxSociaux());
        provenance.setDescription(prospectDTO.getDescription());

        provenanceRepository.save(provenance);

        prospect.setProvenance(provenance);

        return prospectRepository.save(prospect);
    }
    @Override
    public Prospect update(Long prospectId, ProspectDTO prospectDTO) {
        Prospect existingProspect = prospectRepository.findById(prospectId)
                .orElseThrow(() -> new DataNotFoundException("Prospect not found with id: " + prospectId));
        existingProspect.setNumeroTelephone(prospectDTO.getNumeroTelephone());
        existingProspect.setNom(prospectDTO.getNom());
        existingProspect.setPrenom(prospectDTO.getPrenom());
        existingProspect.setAdresse(prospectDTO.getAdresse());
        existingProspect.setJob(prospectDTO.getJob());
        existingProspect.setAge(prospectDTO.getAge());
        existingProspect.setGenre(prospectDTO.getGenre());
        Provenance provenance = existingProspect.getProvenance();
        if (provenance == null) {
            provenance = new Provenance();
        }
        provenance.setReseauxSociaux(prospectDTO.getReseauxSociaux());
        provenance.setDescription(prospectDTO.getDescription());

        provenanceRepository.save(provenance);

        existingProspect.setProvenance(provenance);

        return prospectRepository.save(existingProspect);
    }

    @Override
    public List<Prospect> getAll() {
        return prospectRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long prospectId) {
        prospectRepository.deleteById(prospectId);
    }
}
