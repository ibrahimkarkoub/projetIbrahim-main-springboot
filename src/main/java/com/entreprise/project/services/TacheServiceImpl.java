package com.entreprise.project.services;

import com.entreprise.project.entities.Tache;
import com.entreprise.project.entities.dto.EmailSubjectDTO;
import com.entreprise.project.entities.dto.FormatEmailDTO;
import com.entreprise.project.enums.TacheStatus;
import com.entreprise.project.exceptions.DataNotFoundException;
import com.entreprise.project.mailConfig.MailConfig;
import com.entreprise.project.repositories.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheServiceImpl implements ITacheService {
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private MailConfig emailService;
    @Override
    public Tache createTache(Tache tache) {
        Tache tache1= tacheRepository.save(tache);
        FormatEmailDTO formatEmailDTO = FormatEmailDTO.builder().build();
        formatEmailDTO.setTo(tache1.getAssigne().getEmail());
        formatEmailDTO.setSubject(EmailSubjectDTO.getSubject(0));
        formatEmailDTO.setDeadline(tache1.getDateDeadline());
        formatEmailDTO.setDescription(tache1.getDescription());
        emailService.sendVerificationEmail(formatEmailDTO,tache1.getAssigne().getFirstName(), tache1.getAssigne().getLastName(),
                null,null, EmailSubjectDTO.getType(0),null);
        return tache1;
    }

    @Override
    public Tache updateTache(Tache tache) {
        Optional<Tache> existingTache = tacheRepository.findById(tache.getId());

        if (existingTache.isPresent()) {
            Tache existingTacheEntity = existingTache.get();
            existingTacheEntity.setAssigne(tache.getAssigne());
            existingTacheEntity.setDescription(tache.getDescription());
            existingTacheEntity.setDateDeadline(tache.getDateDeadline());
            return tacheRepository.save(existingTacheEntity);

        } else {
            return null;
        }
    }

    @Override
    public List<Tache> getAllTachesByAssigneeId(Long assigneeId) {
        return tacheRepository.findByAssigne_Id(assigneeId);
    }

    @Override
    public List<Tache> getAllTachesByCreatorId(Long creatorId) {
        return tacheRepository.findByCreateur_Id(creatorId);
    }

    @Override
    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    @Override
    public void deleteTache(Long tacheId) {
        tacheRepository.deleteById(tacheId);
    }

    @Override
    public void changeStatus(Long tacheId) {
        Tache existingTache = tacheRepository.findById(tacheId).orElse(null);
        existingTache.setStatus(TacheStatus.CLOTURER);
        tacheRepository.save(existingTache);
    }
}
