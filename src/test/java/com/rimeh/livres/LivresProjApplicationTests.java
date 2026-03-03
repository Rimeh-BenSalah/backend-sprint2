package com.rimeh.livres;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.rimeh.livres.entities.Livre;
import com.rimeh.livres.entities.Theme;
import com.rimeh.livres.repos.LivreRepository;
import com.rimeh.livres.service.LivreService;

@SpringBootTest
class LivresProjApplicationTests {
	@Autowired
	private LivreRepository livreRepository;
	@Autowired
	private LivreService livreService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateLivre() {
		Livre liv = new Livre("Germinal", "Émile Zola", 24.0, new Date(), "germinal@gmail.com");
		livreRepository.save(liv);
	}

	@Test
	public void testFindLivre() {
		Livre l = livreRepository.findById(1L).get();
		System.out.println(l);
	}

	@Test
	public void testUpdateLivre() {
		Livre l = livreRepository.findById(1L).get();
		l.setPrixLivre(30.0);
		livreRepository.save(l);
	}

	@Test
	public void testDeleteLivre() {
		livreRepository.deleteById(1L);
		;
	}

	@Test
	public void testListerTousLivre() {
		List<Livre> livs = livreRepository.findAll();
		for (Livre l : livs) {
			System.out.println(l);
		}
	}

	@Test
	public void testFindByNomLivreContains() {
		Page<Livre> livs = livreService.getAllLivresParPage(0, 2);
		System.out.println(livs.getSize());
		System.out.println(livs.getTotalElements());
		System.out.println(livs.getTotalPages());
		livs.getContent().forEach(l -> {
			System.out.println(l.toString());
		});
		/*
		 * ou bien for (Livre l : livs) { System.out.println(l); }
		 */
	}

	@Test
	public void testFindByNomLivre() {
		List<Livre> livs = livreRepository.findByNomLivre("Germinal");
		for (Livre l : livs) {
			System.out.println(l);
		}
	}

	@Test
	public void testFindByNomlivreContains() {
		List<Livre> livs = livreRepository.findByNomLivreContains("l");
		for (Livre l : livs) {
			System.out.println(l);
		}
	}

	@Test
	public void testfindByNomPrix() {
		List<Livre> livs = livreRepository.findByNomPrix("Germinal", 23.0);
		for (Livre l : livs) {
			System.out.println(l);
		}
	}

	@Test
	public void testfindByTheme() {
		Theme the = new Theme();
		the.setIdThe(1L);
		List<Livre> livs = livreRepository.findByTheme(the);
		for (Livre l : livs) {
			System.out.println(l);
		}
	}

	@Test
	public void findByThemeIdThe() {
		List<Livre> livs = livreRepository.findByThemeIdThe(1L);
		for (Livre l : livs) {
			System.out.println(l);
		}
	}

	@Test
	public void testfindByOrderByNomLivreAsc() {
		List<Livre> livs = livreRepository.findByOrderByNomLivreAsc();
		for (Livre l : livs) {
			System.out.println(l);
		}
	}

	@Test
	public void testTrierLivresNomsPrix() {
		List<Livre> livs = livreRepository.trierLivresNomsPrix();
		for (Livre l : livs) {
			System.out.println(l);
		}
	}

}
