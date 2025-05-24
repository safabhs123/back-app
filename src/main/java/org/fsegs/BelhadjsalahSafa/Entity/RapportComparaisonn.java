package org.fsegs.BelhadjsalahSafa.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RapportComparaisonn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int nbPassagesBanque;
    private int nbPassagesTransport;
    private BigDecimal montantBanque;
    private BigDecimal montantTransport;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getNbPassagesBanque() {
		return nbPassagesBanque;
	}
	public void setNbPassagesBanque(int nbPassagesBanque) {
		this.nbPassagesBanque = nbPassagesBanque;
	}
	public int getNbPassagesTransport() {
		return nbPassagesTransport;
	}
	public void setNbPassagesTransport(int nbPassagesTransport) {
		this.nbPassagesTransport = nbPassagesTransport;
	}
	public BigDecimal getMontantBanque() {
		return montantBanque;
	}
	public void setMontantBanque(BigDecimal montantBanque) {
		this.montantBanque = montantBanque;
	}
	public BigDecimal getMontantTransport() {
		return montantTransport;
	}
	public void setMontantTransport(BigDecimal montantTransport) {
		this.montantTransport = montantTransport;
	}

    
}

