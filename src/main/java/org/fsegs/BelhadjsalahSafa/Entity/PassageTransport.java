package org.fsegs.BelhadjsalahSafa.Entity;

import java.time.LocalDate;

public class PassageTransport {
    private Nature nature;
    private double montant;
    private int nbPassages;
    private LocalDate date;
    private double trp;
    private double trt;
	
	public double getTrt() {
		return trt;
	}
	public void setTrt(double trt) {
		this.trt = trt;
	}
	public PassageTransport(Nature nature, double montant, int nbPassages, LocalDate date) {
		super();
		this.nature = nature;
		this.montant = montant;
		this.nbPassages = nbPassages;
		this.date = date;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Nature getNature() {
		return nature;
	}
	public void setNature(Nature nature) {
		this.nature = nature;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public int getNbPassages() {
		return nbPassages;
	}
	public void setNbPassages(int nbPassages) {
		this.nbPassages = nbPassages;
	}  

  
}

