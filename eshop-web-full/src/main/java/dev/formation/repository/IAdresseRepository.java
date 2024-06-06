package dev.formation.repository;

import java.util.List;

import dev.formation.model.Adresse;

public interface IAdresseRepository extends IRepository<Adresse, Long>{
	List<Adresse> findAllByVille(String ville);
}
