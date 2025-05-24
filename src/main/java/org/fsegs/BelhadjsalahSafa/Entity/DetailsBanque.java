package org.fsegs.BelhadjsalahSafa.Entity;

import java.time.LocalDate;

public class DetailsBanque {

	    private String agsa;
	    private String nomp;
	    private String type;
	    private int nbPassages;
	    private String date;
   
    
  
	public String getNomp() {
		return nomp;
	}
	public void setNomp(String nomp) {
		this.nomp = nomp;
	}
	

	    public DetailsBanque(String agsa, String nomp, String type, int nbPassages, String date) {
	        this.agsa = agsa;
	        this.nomp = nomp;
	        this.type = type;
	        this.nbPassages = nbPassages;
	        this.date = date;
	    }
	public String getAgsa() {
		return agsa;
	}
	public void setAgsa(String agsa) {
		this.agsa = agsa;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNbPassages() {
		return nbPassages;
	}
	public void setNbPassages(int nbPassages) {
		this.nbPassages = nbPassages;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
    

    


