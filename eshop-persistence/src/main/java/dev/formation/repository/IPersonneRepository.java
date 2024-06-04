package dev.formation.repository;

import java.util.List;

import dev.formation.model.Client;
import dev.formation.model.Fournisseur;
import dev.formation.model.Personne;

public interface IPersonneRepository extends IRepository<Personne, Long> {
	List<Client> findAllClient();
	
	List<Fournisseur> findAllFournisseur();
}
