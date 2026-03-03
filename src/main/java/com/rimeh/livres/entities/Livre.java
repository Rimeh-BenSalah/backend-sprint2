package com.rimeh.livres.entities;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Livre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLivre;
	private String nomLivre;
	private String auteur;
	private Double prixLivre;
	private Date Datedepublication;
	private String email;
	@ManyToOne
	private Theme theme;
	public Livre(String nomLivre, String auteur, Double prixLivre, Date datedepublication, String email) {
		super();
		this.nomLivre = nomLivre;
		this.auteur = auteur;
		this.prixLivre = prixLivre;
		Datedepublication = datedepublication;
		this.email = email;
	}
	public Livre() {
		super();
	}
	public Long getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(Long idLivre) {
		this.idLivre = idLivre;
	}
	public String getNomLivre() {
		return nomLivre;
	}
	public void setNomLivre(String nomLivre) {
		this.nomLivre = nomLivre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public Double getPrixLivre() {
		return prixLivre;
	}
	public void setPrixLivre(Double prixLivre) {
		this.prixLivre = prixLivre;
	}
	public Date getDatedepublication() {
		return Datedepublication;
	}
	public void setDatedepublication(Date datedepublication) {
		Datedepublication = datedepublication;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Livre [idLivre=" + idLivre + ", nomLivre=" + nomLivre + ", auteur=" + auteur + ", prixLivre="
				+ prixLivre + ", Datedepublication=" + Datedepublication + ", email=" + email + "]";
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	
}
