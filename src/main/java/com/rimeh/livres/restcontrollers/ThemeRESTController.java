package com.rimeh.livres.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rimeh.livres.entities.Theme;
import com.rimeh.livres.repos.ThemeRepository;

@RestController
@RequestMapping("/api/the")
@CrossOrigin("*")
public class ThemeRESTController {

    @Autowired
    ThemeRepository themeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Theme getThemeById(@PathVariable("id") Long id) {
        return themeRepository.findById(id).get();
    }
}