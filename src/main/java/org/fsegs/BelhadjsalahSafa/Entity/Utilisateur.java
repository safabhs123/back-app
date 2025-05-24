package org.fsegs.BelhadjsalahSafa.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name="utilisateur")

public class Utilisateur {
	@Id
	
    private String matricule;
	@Column(name="nom",nullable=false)
    private String nom;
	@Column(name="prenom",nullable = false)
	private String prenom;
	@Column(name="password",length=30,nullable=false)
    private String password;
	  public Utilisateur(String matricule, String nom, String prenom, String password, Role role, LocalDate dateDebutTravail) {
	        this.matricule = matricule;
	        this.nom = nom;
	        this.prenom = prenom;
	        this.password = password;
	        this.role = role;
	        this.dateDebutTravail = dateDebutTravail;
	    }

 

    @Enumerated(EnumType.STRING)
    @Column(name="role",nullable = false)
    private Role role;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="date_debut_travail",nullable = true)
    private LocalDate dateDebutTravail;
	
}



