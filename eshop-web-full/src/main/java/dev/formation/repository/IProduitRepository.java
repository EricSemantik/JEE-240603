package dev.formation.repository;

import java.util.List;
import java.util.Optional;

import dev.formation.model.Produit;

public interface IProduitRepository extends IRepository<Produit, Long>{
	
	List<Produit> findAllByNoteLeatherThan(int note);

	Optional<Produit> findProduitByIdWithCommentaires(Long id);
}
