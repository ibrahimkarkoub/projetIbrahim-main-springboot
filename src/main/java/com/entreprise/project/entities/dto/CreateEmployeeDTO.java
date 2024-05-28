package com.entreprise.project.entities.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateEmployeeDTO {
    String userName;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String password;
}
