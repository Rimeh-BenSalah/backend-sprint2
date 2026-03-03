package com.rimeh.livres.entities;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Theme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idThe;
	private String nomThe;
	private String descriptionThe;
	@JsonIgnore
	@OneToMany(mappedBy = "theme")
	private List<Livre> livres;
	
	/*public Theme() {}
	public Theme(String nomThe, String descriptionThe, List<Livre> livres) {
	super();
	this.nomThe = nomThe;
	this.descriptionThe = descriptionThe;
	this.livres = livres;
	}
	public Long getIdThe() {
		return idThe;
	}
	public void setIdThe(Long idThe) {
		this.idThe = idThe;
	}
	public String getNomThe() {
		return nomThe;
	}
	public void setNomThe(String nomThe) {
		this.nomThe = nomThe;
	}
	public String getDescriptionThe() {
		return descriptionThe;
	}
	public void setDescriptionThe(String descriptionThe) {
		this.descriptionThe = descriptionThe;
	}*/
	
}
