package com.rimeh.livres.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nomLiv", types = { Livre.class })
public interface LivreProjection {
	public String getNomLivre();
}
