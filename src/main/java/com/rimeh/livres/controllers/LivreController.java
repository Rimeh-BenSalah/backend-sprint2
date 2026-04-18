package com.rimeh.livres.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rimeh.livres.LivreDTO;
import com.rimeh.livres.entities.Livre;
import com.rimeh.livres.entities.Theme;
import com.rimeh.livres.service.LivreService;

import jakarta.validation.Valid;

@Controller
public class LivreController {

    @Autowired
    LivreService livreService;
    
    @ModelAttribute("themes")
    public List<Theme> populateThemes() {
        return livreService.getAllThemes();
    }

    @RequestMapping("/ListeLivres")
    public String listeLivres(ModelMap modelMap,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "2") int size) {

        Page<Livre> livs = livreService.getAllLivresParPage(page, size);

        modelMap.addAttribute("livres", livs);
        modelMap.addAttribute("pages", new int[livs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "listeLivres";
    }

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("livre", new Livre());
        modelMap.addAttribute("mode", "new");
        return "formLivre";
    }

    @RequestMapping("/saveLivre")
    public String saveLivre(
            @Valid @ModelAttribute("livre") Livre livre,
            BindingResult bindingResult,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        // S'il y a des erreurs de validation, revenir au formulaire
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("mode", "new");
            modelMap.addAttribute("page", page);
            modelMap.addAttribute("size", size);
            return "formLivre";
        }

        // Vérifier si c'est un nouvel enregistrement
        boolean isNew = (livre.getIdLivre() == null);

        // Convertir Livre en DTO avant de sauvegarder
        LivreDTO livreDTO = new LivreDTO();
        livreDTO.setIdLivre(livre.getIdLivre());
        livreDTO.setNomLivre(livre.getNomLivre());
        livreDTO.setAuteur(livre.getAuteur());
        livreDTO.setPrixLivre(livre.getPrixLivre());
        livreDTO.setDatedepublication(livre.getDatedepublication());
        //livreDTO.setEmail(livre.getEmail());
        livreDTO.setTheme(livre.getTheme());

        // Sauvegarder via le service DTO
        livreService.saveLivre(livreDTO);

        // Calculer la page courante après ajout
        int currentPage = page;
        if (isNew) {
            Page<Livre> livres = livreService.getAllLivresParPage(page, size);
            currentPage = livres.getTotalPages() - 1; // aller à la dernière page
        }

        return "redirect:/ListeLivres?page=" + currentPage + "&size=" + size;
    }

    @RequestMapping("/supprimerLivre")
    public String supprimerLivre(@RequestParam("id") Long id,
                                 ModelMap modelMap,
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
    public String editerLivre(@RequestParam("id") Long id,
                              ModelMap modelMap,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "2") int size) {

        LivreDTO l = livreService.getLivre(id);

        modelMap.addAttribute("livre", l);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("size", size);

        return "formLivre";
    }

    @RequestMapping("/updateLivre")
    public String updateLivre(
            @Valid @ModelAttribute("livre") Livre livre,
            BindingResult bindingResult,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("mode", "edit");
            modelMap.addAttribute("page", page);
            modelMap.addAttribute("size", size);
            return "formLivre";
        }

        // Convertir l'entité Livre en DTO avant la mise à jour
        LivreDTO livreDTO = new LivreDTO();
        livreDTO.setIdLivre(livre.getIdLivre());
        livreDTO.setNomLivre(livre.getNomLivre());
        livreDTO.setAuteur(livre.getAuteur());
        livreDTO.setPrixLivre(livre.getPrixLivre());
        livreDTO.setDatedepublication(livre.getDatedepublication());
        //livreDTO.setEmail(livre.getEmail());
        livreDTO.setTheme(livre.getTheme());

        // Appeler le service avec le DTO
        livreService.updateLivre(livreDTO);

        return "redirect:/ListeLivres?page=" + page + "&size=" + size;
    }
}