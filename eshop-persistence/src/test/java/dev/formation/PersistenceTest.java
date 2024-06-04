package dev.formation;

import org.junit.Test;

import dev.formation.model.Adresse;
import dev.formation.repository.IAdresseRepository;
import dev.formation.repository.IPersonneRepository;
import dev.formation.repository.IProduitRepository;

public class PersistenceTest {

	@Test
	public void populateTest() {
		IAdresseRepository adresseRepository = Application.getInstance().getAdresseRepository();
		IPersonneRepository personneRepository = Application.getInstance().getPersonneRepository();
		IProduitRepository produitRepository = Application.getInstance().getProduitRepository();

		Adresse adrFournisseur = new Adresse("1 rue Silicon", "25000", "Silicon Valley");
		adrFournisseur = adresseRepository.save(adrFournisseur);

		Adresse adrClient = new Adresse("1 rue de Toulouse", "33000", "Bordeaux");
		adrClient = adresseRepository.save(adrClient);

//		Client client = new Client();
//		client.setCivilite(Civilite.M);
//		client.setNom("SULTAN");
//		client.setPrenom("Eric");
//		client.setEmail("eric@semantik.fr");
//		client.setDtNaissance(LocalDate.of(1978, 1, 4));
//		client.setAdresse(adrClient);
//		
//		client = (Client) personneRepository.save(client);
//		
//		Fournisseur fournisseur = new Fournisseur();
//		fournisseur.setNom("AMAZON");
//		fournisseur.setResponsable("BEZOS");
//		fournisseur.setEmail("contact@amazon.fr");
//		
//		fournisseur = (Fournisseur) personneRepository.save(fournisseur);

//		Produit produit = new Produit();
//		produit.setNom("IPhone");
//		produit.setPrix(100d);
//		produit.setModele("XS 4");
//		produit.setReference("IPhone XS 4");
//		
//		produit = produitRepository.save(produit);

	}
}
