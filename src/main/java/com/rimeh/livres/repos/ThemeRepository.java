package com.rimeh.livres.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rimeh.livres.entities.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>{

}
