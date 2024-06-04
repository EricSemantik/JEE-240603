package dev.formation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dev.formation.repository.IAdresseRepository;
import dev.formation.repository.IPersonneRepository;
import dev.formation.repository.IProduitRepository;
import dev.formation.repository.csv.PersonneRepositoryCsv;
import dev.formation.repository.csv.ProduitRepositoryCsv;
import dev.formation.repository.sql.AdresseRepositorySql;

public class Application {
	private static Application instance = null;

//	private final IAdresseRepository adresseRepository = new AdresseRepositoryCsv("adresses.csv");
	private final IAdresseRepository adresseRepository = new AdresseRepositorySql();
	private final IPersonneRepository personneRepository = new PersonneRepositoryCsv("personnes.csv");
	private final IProduitRepository produitRepository = new ProduitRepositoryCsv("produits.csv");

	private Application() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=GMT","root", "admin");
	}

}