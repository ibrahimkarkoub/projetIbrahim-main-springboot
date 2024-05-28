package com.entreprise.project.services;

import com.entreprise.project.entities.Utilisateur;
import com.entreprise.project.entities.dto.CreateEmployeeDTO;
import com.entreprise.project.entities.dto.CreateEntrepriseDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    List<Utilisateur> getAllUsers();
    Utilisateur createEntreprise(CreateEntrepriseDTO user);
    Utilisateur createEmploye(Long idEntreprise, CreateEmployeeDTO user);
    Utilisateur updateUser(Long userId, CreateEmployeeDTO employeeDTO);
    List<Utilisateur> getEmployesByEntrepriseId(Long idEntreprise);
    void deleteUser(Long id);
    Utilisateur findById(Long id);
    UserDetails loadUserByUsername(String username);
    boolean forgetPassword(String username);
    boolean changePassword(Long userId, String newPassword);
}
