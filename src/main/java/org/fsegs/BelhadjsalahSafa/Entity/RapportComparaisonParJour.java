package org.fsegs.BelhadjsalahSafa.Entity;

import java.time.LocalDate;

public class RapportComparaisonParJour {

    private LocalDate date;
    private int nbPassagesBanque;
    private int nbPassagesTransporteur;
    private double montantTransporteur;
    private int ecartPassages;
    private double ecartMontant;
    private String messageErreur;
	private boolean equal;


    public String getMessageErreur() {
		return messageErreur;
	}

	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}

	private String statut;  // Nouveau champ pour le statut

    // Constructeurs, getters et setters classiques

	public boolean isEqual() {
	    return equal;
	}

	public void setEqual(boolean equal) {
	    this.equal = equal;
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

    public int getNbPassagesTransporteur() {
        return nbPassagesTransporteur;
    }

    public void setNbPassagesTransporteur(int nbPassagesTransporteur) {
        this.nbPassagesTransporteur = nbPassagesTransporteur;
    }

    public double getMontantTransporteur() {
        return montantTransporteur;
    }

    public void setMontantTransporteur(double montantTransporteur) {
        this.montantTransporteur = montantTransporteur;
    }

    public int getEcartPassages() {
        return ecartPassages;
    }

    public void setEcartPassages(int ecartPassages) {
        this.ecartPassages = ecartPassages;
    }

    public double getEcartMontant() {
        return ecartMontant;
    }

    public void setEcartMontant(double ecartMontant2) {
        this.ecartMontant = ecartMontant2;
    }

    public String getStatut() {
        // Calcul dynamique à la demande si tu veux toujours avoir un statut à jour
        return calculerStatut();
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
   

        private DetailBanque detailsBanque;
        private DetailTransporteur detailsTransporteur;

        public DetailBanque getDetailsBanque() {
            return detailsBanque;
        }

        public void setDetailsBanque(DetailBanque detailsBanque) {
            this.detailsBanque = detailsBanque;
        }

        public DetailTransporteur getDetailsTransporteur() {
            return detailsTransporteur;
        }

        public void setDetailsTransporteur(DetailTransporteur detailsTransporteur) {
            this.detailsTransporteur = detailsTransporteur;
        }

        public static class DetailBanque {
            public LocalDate getDate() {
				return date;
			}
			public void setDate(LocalDate date) {
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
			private LocalDate date;
            private String agsa;
            private String type;
            private int nbPassages;
            private String nomp;
			public String getNomp() {
				return nomp;
			}
			public void setNomp(String nomp) {
				this.nomp = nomp;
			}

            // getters/setters
        }

        public static class DetailTransporteur {
            public LocalDate getDate() {
				return date;
			}
			public void setDate(LocalDate date) {
				this.date = date;
			}
			public int getNbPassages() {
				return nbPassages;
			}
			public void setNbPassages(int nbPassages) {
				this.nbPassages = nbPassages;
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
			public double getMontant() {
				return montant;
			}
			public void setMontant(double montant) {
				this.montant = montant;
			}
			private LocalDate date;
            private int nbPassages;
            private double trp;
            private double trt;
            private double montant;
            private String  nature;
			public String getNature() {
				return nature;
			}
			public void setNature(String nature) {
				this.nature = nature;
			}

            // getters/setters
        }

      
    


    // Ta méthode de calcul du statut, privée pour éviter d’être appelée de l’extérieur directement
    private String calculerStatut() {
        boolean hasEcartPassages = ecartPassages != 0;
        boolean hasEcartMontant = ecartMontant != 0;

        if (!hasEcartPassages && !hasEcartMontant) {
            return "OK";
        } else if (hasEcartPassages && hasEcartMontant) {
            return "Facturé sans preuve + écart passages";
        } else if (hasEcartMontant) {
            return "Facturé sans preuve";
        } else {
            return "Écart passages";
        }
    }
}
