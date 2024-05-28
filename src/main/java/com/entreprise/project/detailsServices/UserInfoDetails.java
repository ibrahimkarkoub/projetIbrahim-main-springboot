package com.entreprise.project.detailsServices;


import com.entreprise.project.entities.Tache;
import com.entreprise.project.entities.Utilisateur;
import com.entreprise.project.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// generate token taa
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoDetails implements UserDetails {
    Long id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String password;
    Utilisateur entreprise;
    List<Utilisateur> users;
    List<Tache> createdtaches;
    List<Tache> assigneetaches;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate HiringDate = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Role role;
    List<GrantedAuthority> authorities;
    public UserInfoDetails(Utilisateur user, List<Utilisateur> users,List<Tache> createdtaches,List<Tache> assigneetaches){
        id=user.getId();
        userName= user.getUserName();
        password= user.getPassword();
        firstName= user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        role=user.getRole();
        HiringDate = user.getHiringDate();
        this.users=users;
        this.createdtaches=createdtaches;
        this.assigneetaches=assigneetaches;
        this.entreprise=user.getEntreprise();
        authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}