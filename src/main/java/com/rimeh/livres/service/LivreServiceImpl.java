package com.rimeh.livres.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rimeh.livres.LivreDTO;
import com.rimeh.livres.entities.Livre;
import com.rimeh.livres.entities.Theme;
import com.rimeh.livres.repos.LivreRepository;
import com.rimeh.livres.repos.ThemeRepository;
@Service
public class LivreServiceImpl implements LivreService {

	@Autowired 
	LivreRepository livreRepository;
	@Autowired
	ThemeRepository themeRepository;
	@Autowired
	ModelMapper modelMapper;
	/*@Override
	public Livre saveLivre(Livre l) {
		return livreRepository.save(l);
	}*/
	/*@Override
	public LivreDTO saveLivre(Livre l) {
	return convertEntityToDto( livreRepository.save(l));
	}*/
	@Override
	public LivreDTO saveLivre(LivreDTO l) {
	return convertEntityToDto( livreRepository.save(convertDtoToEntity(l)));
	}

	/*@Override
	public Livre updateLivre(Livre l) {
		return livreRepository.save(l);
	}*/
	@Override
	public LivreDTO updateLivre(LivreDTO l) {
	return convertEntityToDto(livreRepository.save(convertDtoToEntity(l)));
	}

	@Override
	public void deleteLivre(Livre l) {
		livreRepository.delete(l);
		
	}

	@Override
	public void deleteLivreById(Long id) {
		livreRepository.deleteById(id);
		
	}

	/*@Override
	public Livre getLivre(Long id) {
		return livreRepository.findById(id).get();
	}*/
	@Override
	public LivreDTO getLivre(Long id) {
	return convertEntityToDto( livreRepository.findById(id).get());
	}

	/*@Override
	public List<Livre> getAllLivres() {
		return livreRepository.findAll();
	}*/
	@Override
	public List<LivreDTO> getAllLivres() {
	return livreRepository.findAll().stream()
	.map(this::convertEntityToDto) .collect(Collectors.toList());
	
	
	/*OU BIEN
	List<Livre> livs = livreRepository.findAll();
	List<LivreDTO> listlivDto = new ArrayList<>(livs.size());
	for (Livre l : livs)
	listlivDto.add(convertEntityToDto(l));
	return listlivDto;*/
	
	}

	@Override
	public Page<Livre> getAllLivresParPage(int page, int size) {
		return livreRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public List<Livre> findByNomLivre(String nom) {
		return livreRepository.findByNomLivre(nom);
	}

	@Override
	public List<Livre> findByNomLivreContains(String nom) {
		return livreRepository.findByNomLivreContains(nom);
	}

	@Override
	public List<Livre> findByNomPrix(String nom, Double prix) {
		return livreRepository.findByNomPrix(nom, prix);
	}

	@Override
	public List<Livre> findByTheme(Theme theme) {
		return livreRepository.findByTheme(theme);
	}

	@Override
	public List<Livre> findByThemeIdThe(Long id) {
		return livreRepository.findByThemeIdThe(id);
	}

	@Override
	public List<Livre> findByOrderByNomLivreAsc() {
		return livreRepository.findByOrderByNomLivreAsc();
	}

	@Override
	public List<Livre> trierLivresNomsPrix() {
		return livreRepository.trierLivresNomsPrix();
	}
	@Override
	public List<Theme> getAllThemes() {
	     return themeRepository.findAll();
	}

	@Override
	public LivreDTO convertEntityToDto(Livre l) {
		/*LivreDTO livreDTO = new LivreDTO();
		livreDTO.setIdLivre(l.getIdLivre());
	    livreDTO.setNomLivre(l.getNomLivre());
	    livreDTO.setAuteur(l.getAuteur());
	    livreDTO.setPrixLivre(l.getPrixLivre());
	    livreDTO.setDatedepublication(l.getDatedepublication());
	    livreDTO.setEmail(l.getEmail());
	    livreDTO.setTheme(l.getTheme());
	    return livreDTO;*/
	    /*return LivreDTO.builder()
        .idLivre(l.getIdLivre())
        .nomLivre(l.getNomLivre())
        .auteur(l.getAuteur())
        .prixLivre(l.getPrixLivre())
        .datedepublication(l.getDatedepublication())
        .email(l.getEmail())
        //.theme(l.getTheme())
        .nomthe(l.getTheme().getNomThe())
        .build();*/
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		LivreDTO livreDTO = modelMapper.map(l, LivreDTO.class);
		return livreDTO;
		
	}

	@Override
	public Livre convertDtoToEntity(LivreDTO livreDto) {
		Livre livre = new Livre();
		livre.setIdLivre(livreDto.getIdLivre()); 
		livre.setNomLivre(livreDto.getNomLivre()); 
		livre.setAuteur(livreDto.getAuteur());
		livre.setPrixLivre(livreDto.getPrixLivre()); 
		livre.setDatedepublication(livreDto.getDatedepublication()); 
		//livre.setEmail(livreDto.getEmail());
		livre.setTheme(livreDto.getTheme());
		return livre;
		/*Livre livre = new Livre(); 
		livre = modelMapper.map(livreDto, Livre.class);
		return livre;*/
	}

}
