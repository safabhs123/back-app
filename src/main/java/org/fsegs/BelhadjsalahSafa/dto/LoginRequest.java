package org.fsegs.BelhadjsalahSafa.dto; // Package correspondant au chemin

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	 @NotBlank(message = "Le matricule est obligatoire")
    private String matricule;
	 @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    // Getters et Setters (obligatoires pour Spring)
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}