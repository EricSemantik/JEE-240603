package dev.formation;

import dev.formation.repository.IAdresseRepository;
import dev.formation.repository.IPersonneRepository;
import dev.formation.repository.IProduitRepository;
import dev.formation.repository.csv.AdresseRepositoryCsv;
import dev.formation.repository.csv.PersonneRepositoryCsv;
import dev.formation.repository.csv.ProduitRepositoryCsv;

public class Application {
	private static Application instance = null;

	private final IAdresseRepository adresseRepository = new AdresseRepositoryCsv("adresses.csv");
	private final IPersonneRepository personneRepository = new PersonneRepositoryCsv("personnes.csv");
	private final IProduitRepository produitRepository = new ProduitRepositoryCsv("produits.csv");

	private Application() {
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	public IAdresseRepository getAdresseRepository() {
		return adresseRepository;
	}

	public IPersonneRepository getPersonneRepository() {
		return personneRepository;
	}

	public IProduitRepository getProduitRepository() {
		return produitRepository;
	}

}
