package dev.formation;

import org.junit.Test;

import dev.formation.model.Adresse;
import dev.formation.model.Produit;
import dev.formation.repository.IAdresseRepository;
import dev.formation.repository.IProduitRepository;
import dev.formation.repository.csv.AdresseRepositoryCsv;
import dev.formation.repository.csv.ProduitRepositoryCsv;

public class PersistenceTest {

	@Test
	public void populateTest() {
		IAdresseRepository adresseRepository = new AdresseRepositoryCsv("adresses.csv");
		IProduitRepository produitRepository = new ProduitRepositoryCsv("produits.csv");
		
		Adresse adrFournisseur = new Adresse("1 rue Silicon", "25000", "Silicon Valley");
		adrFournisseur = adresseRepository.save(adrFournisseur);
		
		Produit produit = new Produit();
		produit.setNom("IPhone");
		produit.setPrix(100d);
		produit.setModele("XS 4");
		produit.setReference("IPhone XS 4");
		
		produit = produitRepository.save(produit);
		
	}
}
