package com.entreprise.project.entities.dto;

import java.util.ArrayList;
import java.util.List;

public class EmailSubjectDTO {
    private static final List<String> emailSubjects = new ArrayList<>();
    private static final List<String> emailTypes = new ArrayList<>();

    static {
        emailSubjects.add("Une Tâche a été affectée à vous");
        emailSubjects.add("Demande de réinitialisation de mot de passe ");
    }
    static {
        emailTypes.add("AddingTask");
        emailTypes.add("ForgetPassword");
    }

    public static String getSubject(int index) {
        if (index >= 0 && index < emailSubjects.size()) {
            return emailSubjects.get(index);
        } else {
            return "sujet d'e-mail non trouvé";
        }
    }
    public static String getType(int index) {
        if (index >= 0 && index < emailTypes.size()) {
            return emailTypes.get(index);
        } else {
            return "type d'e-mail non trouvé";
        }
    }
}
