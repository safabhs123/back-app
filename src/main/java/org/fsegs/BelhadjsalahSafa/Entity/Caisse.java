package org.fsegs.BelhadjsalahSafa.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.fsegs.BelhadjsalahSafa.Entity.Region;


@Entity
public class Caisse {
    public Caisse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    private Long id;

    

    @ManyToOne
   // @JoinColumn(name = "region_id")
    private Region region;

	public Caisse(Long id, Region region) {
		super();
		this.id = id;
		
		this.region = region;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

    // Getters, Setters, Constructors
}

