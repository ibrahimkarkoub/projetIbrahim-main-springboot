package com.entreprise.project.entities.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FormatEmailDTO {
    private String to ;
    private String Subject ;
    private String tomporalPassword;
    private LocalDate deadline;
    private String description;



    public String getBody(String firstName, String lastName, String matricule,
                          String genderUser,
                          String type, String codeVerification) {


       if(type.equals("ForgetPassword")) {
            return "Bonjour "  + firstName +" "+lastName+" . Nous espérons que cet e-mail vous trouve bien.\n" +
                    "Suite à votre tentative de réinitialsation de votre mot de passe avec ce nom d'utilisateur "+matricule+" , nous vous envoyons un nouveau mot de passe pour accèder à l'application.\n " +
                    codeVerification + "\n Cordialement,";
        }else if(type.equals("AddingTask")) {
           return "Bonjour "  + firstName +" "+lastName+" . Nous espérons que cet e-mail vous trouve bien.\n" +
                   "Une nouvelle Tâche a été affectée chez vous avec un deadline au  "+deadline+".\n " +
                   "\nDétails de la tache : "+description+"\n"+
                   "\nBon courage à vous !";
       }else{
            return null;
        }

    }
}

