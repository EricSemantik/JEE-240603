package dev.formation.repository.csv;

import java.io.File;
import java.util.List;
import java.util.Optional;

import dev.formation.model.Produit;
import dev.formation.repository.IProduitRepository;

public class ProduitRepositoryCsv implements IProduitRepository {

	private File file;

	public ProduitRepositoryCsv(String chemin) {
		super();
		this.file = new File(chemin);
	}
	
	@Override
	public List<Produit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Produit> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Produit save(Produit obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
