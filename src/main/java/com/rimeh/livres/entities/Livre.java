package com.rimeh.livres.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivre;

    @NotNull(message = "Le nom du livre est obligatoire")
    @Size(min = 4, max = 50, message = "Le nom du livre doit contenir entre 4 et 50 caractères")
    private String nomLivre;

    @NotNull(message = "Le nom de l'auteur est obligatoire")
    @Size(min = 4, max = 50, message = "Le nom de l'auteur doit contenir entre 4 et 50 caractères")
    private String auteur;

    @NotNull(message = "Le prix est obligatoire")
    @Min(value = 10, message = "Le prix minimum est 10")
    @Max(value = 1000, message = "Le prix maximum est 1000")
    private Double prixLivre;

    @NotNull(message = "La date de publication est obligatoire")
    @PastOrPresent(message = "La date doit être passée ou aujourd'hui")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Datedepublication;

    @NotNull(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    @ManyToOne
    private Theme theme;

    public Livre() {}

    public Livre(String nomLivre, String auteur, Double prixLivre, Date Datedepublication, String email) {
        this.nomLivre = nomLivre;
        this.auteur = auteur;
        this.prixLivre = prixLivre;
        this.Datedepublication = Datedepublication;
        this.email = email;
    }

    // Getters et setters
    public Long getIdLivre() { return idLivre; }
    public void setIdLivre(Long idLivre) { this.idLivre = idLivre; }

    public String getNomLivre() { return nomLivre; }
    public void setNomLivre(String nomLivre) { this.nomLivre = nomLivre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public Double getPrixLivre() { return prixLivre; }
    public void setPrixLivre(Double prixLivre) { this.prixLivre = prixLivre; }

    public Date getDatedepublication() { return Datedepublication; }
    public void setDatedepublication(Date Datedepublication) { this.Datedepublication = Datedepublication; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Theme getTheme() { return theme; }
    public void setTheme(Theme theme) { this.theme = theme; }

    @Override
    public String toString() {
        return "Livre [idLivre=" + idLivre + ", nomLivre=" + nomLivre + ", auteur=" + auteur +
               ", prixLivre=" + prixLivre + ", Datedepublication=" + Datedepublication + ", email=" + email + "]";
    }
}