package com.entreprise.project.services;

import com.entreprise.project.entities.Prospect;
import com.entreprise.project.entities.dto.ProspectDTO;

import java.util.List;

public interface IProspectService {
    Prospect add(Long userId, ProspectDTO prospectDTO);
    Prospect update(Long prospectId, ProspectDTO prospectDTO);
    List<Prospect> getAll();
    void delete(Long prospectId);
}
