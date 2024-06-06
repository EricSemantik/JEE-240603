package dev.formation.repository;

import java.util.List;

import dev.formation.model.Produit;

public interface IProduitRepository extends IRepository<Produit, Long>{
	
	List<Produit> findAllByNoteLeatherThan(int note);
	
}
