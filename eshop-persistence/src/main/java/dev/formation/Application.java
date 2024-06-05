package dev.formation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dev.formation.repository.IAdresseRepository;
import dev.formation.repository.ICommandeDetailRepository;
import dev.formation.repository.ICommandeRepository;
import dev.formation.repository.ICommentaireRepository;
import dev.formation.repository.IPersonneRepository;
import dev.formation.repository.IProduitRepository;
import dev.formation.repository.IUtilisateurRepository;
import dev.formation.repository.jpa.AdresseRepositoryJpa;
import dev.formation.repository.jpa.CommandeDetailRepositoryJpa;
import dev.formation.repository.jpa.CommandeRepositoryJpa;
import dev.formation.repository.jpa.CommentaireRepositoryJpa;
import dev.formation.repository.jpa.PersonneRepositoryJpa;
import dev.formation.repository.jpa.ProduitRepositoryJpa;
import dev.formation.repository.jpa.UtilisateurRepositoryJpa;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
	private static Application instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("eshop-pu");
//	private final IAdresseRepository adresseRepository = new AdresseRepositoryCsv("adresses.csv");
//	private final IAdresseRepository adresseRepository = new AdresseRepositorySql();
//	private final IPersonneRepository personneRepository = new PersonneRepositoryCsv("personnes.csv");
//	private final IPersonneRepository personneRepository = new PersonneRepositorySql();
//	private final IProduitRepository produitRepository = new ProduitRepositoryCsv("produits.csv");
	private final IAdresseRepository adresseRepository = new AdresseRepositoryJpa();
	private final ICommandeRepository commandeRepository = new CommandeRepositoryJpa();
	private final ICommandeDetailRepository commandeDetailRepository = new CommandeDetailRepositoryJpa();
	private final ICommentaireRepository commentaireRepository = new CommentaireRepositoryJpa();
	private final IPersonneRepository personneRepository = new PersonneRepositoryJpa();
	private final IProduitRepository produitRepository = new ProduitRepositoryJpa();
	private final IUtilisateurRepository utilisateurRepository = new UtilisateurRepositoryJpa();

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

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop?serverTimezone=GMT", "root", "admin");
	}

	public IAdresseRepository getAdresseRepository() {
		return adresseRepository;
	}

	public ICommandeRepository getCommandeRepository() {
		return commandeRepository;
	}

	public ICommandeDetailRepository getCommandeDetailRepository() {
		return commandeDetailRepository;
	}

	public ICommentaireRepository getCommentaireRepository() {
		return commentaireRepository;
	}

	public IPersonneRepository getPersonneRepository() {
		return personneRepository;
	}

	public IProduitRepository getProduitRepository() {
		return produitRepository;
	}

	public IUtilisateurRepository getUtilisateurRepository() {
		return utilisateurRepository;
	}

}
