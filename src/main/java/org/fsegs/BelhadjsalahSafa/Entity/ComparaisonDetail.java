package org.fsegs.BelhadjsalahSafa.Entity;

import java.util.List;

public class ComparaisonDetail {
    private String date;
    private int nbPassagesBanque;
    private int nbPassagesTransporteur;
    private boolean equal;
    private double montantTransporteur;
   // private int toleranceJours;

  /* int getToleranceJours() {
		return toleranceJours;
	}
	public void setToleranceJours(int toleranceJours) {
		this.toleranceJours = toleranceJours;
	}*/
    private List<DetailsBanque> banqueDetails;
    public List<DetailsBanque> getBanqueDetails() {
		return banqueDetails;
	}
	public void setBanqueDetails(List<DetailsBanque> banqueDetails) {
		this.banqueDetails = banqueDetails;
	}
	private List<PassageDetail> transporteurDetails;
    
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNbPassagesBanque() {
		return nbPassagesBanque;
	}
	public void setNbPassagesBanque(int nbPassagesBanque) {
		this.nbPassagesBanque = nbPassagesBanque;
	}
	public int getNbPassagesTransporteur() {
		return nbPassagesTransporteur;
	}
	public void setNbPassagesTransporteur(int nbPassagesTransporteur) {
		this.nbPassagesTransporteur = nbPassagesTransporteur;
	}
	public boolean isEqual() {
		return equal;
	}
	public void setEqual(boolean equal) {
		this.equal = equal;
	}
	public double getMontantTransporteur() {
		return montantTransporteur;
	}
	public void setMontantTransporteur(double montantTransporteur) {
		this.montantTransporteur = montantTransporteur;
	}
	
	public List<PassageDetail> getTransporteurDetails() {
		return transporteurDetails;
	}
	public void setTransporteurDetails(List<PassageDetail> transporteurDetails) {
		this.transporteurDetails = transporteurDetails;
	}
    
}