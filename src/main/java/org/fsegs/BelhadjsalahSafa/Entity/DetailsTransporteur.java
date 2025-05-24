package org.fsegs.BelhadjsalahSafa.Entity;

import java.time.LocalDate;

public class DetailsTransporteur {
    private String nature;
    private double montant;
    private String nbPassages;
    private LocalDate date;
	private String libelle;
	private String resultat;
	 private double trt;
	 private double trp;
	
	
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	public double getTrt() {
		return trt;
	}
	public void setTrt(double trt) {
		this.trt = trt;
	}
	public double getTrp() {
		return trp;
	}
	public void setTrp(double trp) {
		this.trp = trp;
	}
	public DetailsTransporteur(String nature, double montant, String nbPassages, LocalDate date, String libelle,
			String resultat, double trt, double trp) {
		super();
		this.nature = nature;
		this.montant = montant;
		this.nbPassages = nbPassages;
		this.date = date;
		this.libelle = libelle;
		this.resultat = resultat;
		this.trt = trt;
		this.trp = trp;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getResultat() {
		return resultat;
	}
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getNbPassages() {
		return nbPassages;
	}
	public void setNbPassages(String nbPassages) {
		this.nbPassages = nbPassages;
	}  

  
}

