package org.fsegs.BelhadjsalahSafa.Entity;

import java.time.LocalDate;

public class PassageBanque {

    private String agsa;
    private LocalDate date; 
    private String type;
    private int nombrePassages;
    private String nomp;
    public String getNomp() {
		return nomp;
	}

	public void setNomp(String nomp) {
		this.nomp = nomp;
	}

	public PassageBanque(LocalDate date, int nombrePassages) {
        this.date = date;
        this.nombrePassages = nombrePassages;
    }
   
	
	public PassageBanque(String agsa, LocalDate date, String type, int nombrePassages, String nomp) {
		super();
		this.agsa = agsa;
		this.date = date;
		this.type = type;
		this.nombrePassages = nombrePassages;
		this.nomp = nomp;
	}

	public String getAgsa() {
		return agsa;
	}
	public void setAgsa(String agsa) {
		this.agsa = agsa;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNombrePassages() {
		return nombrePassages;
	}
	public void setNombrePassages(int nombrePassages) {
		this.nombrePassages = nombrePassages;
	}
    

    
}
