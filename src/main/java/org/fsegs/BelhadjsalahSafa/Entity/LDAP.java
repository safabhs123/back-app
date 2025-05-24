package org.fsegs.BelhadjsalahSafa.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ldap")
public class LDAP {
    @Id
    @Column(name="matricule", length=30, unique=true, nullable=false)
    private String matricule;
    //private String nom;
    //private String prenom;
    //private LocalDate dateDebutTravail;
    private String password;
    @Enumerated(EnumType.ORDINAL)  // Stocke l'enum sous forme numérique (0, 1...)
    private Role role;
    

}

