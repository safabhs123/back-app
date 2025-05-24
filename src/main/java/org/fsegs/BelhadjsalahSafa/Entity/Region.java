package org.fsegs.BelhadjsalahSafa.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;


@Entity
public class Region {
	
    public Region() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Region(Long id, String nom, List<Transporteur> transporteurs) {
		super();
		this.id = id;
		this.nom = nom;
		this.transporteurs = transporteurs;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public List<Transporteur> getTransporteurs() {
		return transporteurs;
	}

	public void setTransporteurs(List<Transporteur> transporteurs) {
		this.transporteurs = transporteurs;
	}

	

	private String nom;

	@ManyToMany(fetch = FetchType.EAGER)
    private List<Transporteur> transporteurs = new ArrayList<>();;

 

    // Getters, Setters, Constructors
}

