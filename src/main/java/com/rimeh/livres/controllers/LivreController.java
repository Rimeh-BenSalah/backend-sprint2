package com.rimeh.livres.controllers;

import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

import com.rimeh.livres.entities.Livre;
import com.rimeh.livres.entities.Theme;
import com.rimeh.livres.service.LivreService;

import jakarta.validation.Valid;

//@RestController
@Controller
public class LivreController {

	/*
	 * @RequestMapping("/myView") public String myView() { return "myView"; }
	 */

	@Autowired
	LivreService livreService;

	/*
	 * @RequestMapping("/ListeLivres") public String listeLivres(ModelMap modelMap)
	 * { List<Livre> livs = livreService.getAllLivres();
	 * modelMap.addAttribute("livres", livs); return "listeLivres"; }
	 */
	@RequestMapping("/ListeLivres")
	public String listeLivres(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Page<Livre> livs = livreService.getAllLivresParPage(page, size);
		modelMap.addAttribute("livres", livs);
		modelMap.addAttribute("pages", new int[livs.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeLivres";
	}

	/*@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLivre";
	}*/
	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap)
	{
	    List<Theme> themes = livreService.getAllThemes();
	    modelMap.addAttribute("livre", new Livre());
	    modelMap.addAttribute("mode", "new");
	    modelMap.addAttribute("themes", themes);
	    return "formLivre";
	}

	/*@RequestMapping("/saveLivre")
	public String saveLivre(@ModelAttribute("livre") Livre livre, @RequestParam("date") String date, ModelMap modelMap)
			throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date datedepublication = dateformat.parse(String.valueOf(date));
		livre.setDatedepublication(datedepublication);
		Livre saveLivre = livreService.saveLivre(livre);
		String msg = "livre enregistré avec Id " + saveLivre.getIdLivre();
		modelMap.addAttribute("msg", msg);
		return "createLivre";
	}*/
	/*@RequestMapping("/saveLivre")
	public String saveLivre(@ModelAttribute("livre") Livre livre) {
		livreService.saveLivre(livre);
		return "createLivre";
	}*/
	@RequestMapping("/saveLivre")
	public String saveLivre(
	        @Valid Livre livre, 
	        BindingResult bindingResult,
	        @RequestParam(name="page", defaultValue = "0") int page,
	        @RequestParam(name="size", defaultValue = "2") int size) {

	    int currentPage;
	    boolean isNew = false;

	    if (bindingResult.hasErrors()) {
	        return "formLivre"; 
	    }
	    if (livre.getIdLivre() == null) {
	        isNew = true;
	    }
	    livreService.saveLivre(livre);
	    if (isNew) {
	        Page<Livre> livres = livreService.getAllLivresParPage(page, size);
	        currentPage = livres.getTotalPages() - 1; 
	    } else { // si modification
	        currentPage = page;
	    }
	    return "redirect:/ListeLivres?page=" + currentPage + "&size=" + size;
	}

	/*
	 * @RequestMapping("/supprimerLivre") public String
	 * supprimerLivre(@RequestParam("id") Long id, ModelMap modelMap) {
	 * livreService.deleteLivreById(id); List<Livre> livs =
	 * livreService.getAllLivres(); modelMap.addAttribute("livres", livs); return
	 * "listeLivres"; }
	 */
	@RequestMapping("/supprimerLivre")
	public String supprimerLivre(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		livreService.deleteLivreById(id);
		Page<Livre> livs = livreService.getAllLivresParPage(page, size);
		modelMap.addAttribute("livres", livs);
		modelMap.addAttribute("pages", new int[livs.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeLivres";
	}

	@RequestMapping("/modifierLivre")
	public String editerLivre(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size)
	{
	    Livre l = livreService.getLivre(id);
	    List<Theme> themes = livreService.getAllThemes();

	    modelMap.addAttribute("livre", l);
	    modelMap.addAttribute("mode", "edit");
	    modelMap.addAttribute("themes", themes);
	    modelMap.addAttribute("page", page);
	    modelMap.addAttribute("size", size);
	    return "formLivre";
	}

	@RequestMapping("/updateLivre")
	public String updateLivre(@Valid @ModelAttribute("livre") Livre livre,
	                          BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return "formLivre";
	    }
	    livreService.updateLivre(livre);
	    
	    return "redirect:/ListeLivres";
	}

}
