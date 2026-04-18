package com.rimeh.livres.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rimeh.livres.LivreDTO;
import com.rimeh.livres.entities.Livre;
import com.rimeh.livres.entities.Theme;

public interface LivreService {
	/*Livre saveLivre(Livre l);*/
	/*Livre updateLivre(Livre l);*/
	void deleteLivre(Livre l);
	void deleteLivreById(Long id);
	/*Livre getLivre(Long id);*/
	/*List<Livre> getAllLivres();*/
	Page<Livre> getAllLivresParPage(int page, int size);
	List<Livre> findByNomLivre(String nom);
	List<Livre> findByNomLivreContains(String nom);
	List<Livre> findByNomPrix (String nom, Double prix);
	List<Livre> findByTheme (Theme theme);
	List<Livre> findByThemeIdThe(Long id);
	List<Livre> findByOrderByNomLivreAsc();
	List<Livre> trierLivresNomsPrix();
	List<Theme> getAllThemes();
	
	
	/*LivreDTO saveLivre(Livre l);*/
	LivreDTO getLivre(Long id);
	List<LivreDTO> getAllLivres();
	
	LivreDTO saveLivre(LivreDTO l);
	LivreDTO updateLivre(LivreDTO l);
	
	
	LivreDTO convertEntityToDto(Livre l);
	
	Livre convertDtoToEntity(LivreDTO livreDto);
	
	
}
