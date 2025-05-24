package org.fsegs.BelhadjsalahSafa.dto;

import org.fsegs.BelhadjsalahSafa.Entity.Role;

import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class LoginResponse {
    
    private String matricule;

    // On ne stocke pas directement le Role, mais la chaîne représentant le rôle.
    private Role role; // Utilisation d'une chaîne pour le rôle.

	public LoginResponse(String matricule, Role role) {
		super();
		this.matricule = matricule;
		this.role = role;
	}


}
