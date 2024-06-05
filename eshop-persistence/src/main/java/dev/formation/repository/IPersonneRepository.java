package dev.formation.repository;

import java.util.List;
import java.util.Optional;

import dev.formation.model.Client;
import dev.formation.model.Fournisseur;
import dev.formation.model.Personne;

public interface IPersonneRepository extends IRepository<Personne, Long> {
	Optional<Personne> findByIdentifiant(String identifiant);
	
	List<Client> findAllClient();
	
	List<Fournisseur> findAllFournisseur();
	
}
