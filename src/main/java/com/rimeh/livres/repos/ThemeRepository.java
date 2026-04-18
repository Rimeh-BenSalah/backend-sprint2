package com.rimeh.livres.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rimeh.livres.entities.Theme;
@RepositoryRestResource(path = "the") 
@CrossOrigin(origins = "http://localhost:4200")
public interface ThemeRepository extends JpaRepository<Theme, Long>{

}
