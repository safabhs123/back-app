package org.fsegs.BelhadjsalahSafa.Entity;


public class PassageDetail {
    
    private double trp;
    private double trt;
 
    private String ordPassage;
    private String agence;
    private String resultat;
    
    private String date; // nouvelle ligne

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // getters & setters

    
	
	public String getAgence() {
		return agence;
	}
	public double getTrp() {
		return trp;
	}
	public void setTrp(double trp) {
		this.trp = trp;
	}
	public double getTrt() {
		return trt;
	}
	public void setTrt(double trt) {
		this.trt = trt;
	}
	public PassageDetail(double trt, double trp, String ordPassage, String agence, String resultat) {
	    this.trt = trt;
	    this.trp = trp;
	    this.ordPassage = ordPassage;
	    this.agence = agence;
	    this.resultat = resultat;
	}
	public void setAgence(String agence) {
		this.agence = agence;
	}
	public String getordPassage() {
		return ordPassage;
	}
	public void setordPassage(String ordPassage) {
		this.ordPassage = ordPassage;
	}
	public String getresultat() {
		return resultat;
	}
	public void setresultat(String typePassage) {
		this.resultat = typePassage;
	}
    

}
