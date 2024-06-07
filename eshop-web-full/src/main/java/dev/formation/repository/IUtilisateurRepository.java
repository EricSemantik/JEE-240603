package dev.formation.repository;

import java.util.Optional;

import dev.formation.model.Utilisateur;

public interface IUtilisateurRepository extends IRepository<Utilisateur, Long>{
	Optional<Utilisateur> findByIdentifiant(String identifiant);
	
	Optional<Utilisateur> findByIdWithRoles(Long id);
}
