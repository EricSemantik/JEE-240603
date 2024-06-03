package dev.formation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

public class ModelTest {
	@Test
	public void populateTest() {
		Adresse adrFournisseur = new Adresse("1 rue Silicon", "25000", "Silicon Valley");

		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom("AMAZON");
		fournisseur.setResponsable("BEZOS");
		fournisseur.setEmail("contact@amazon.fr");
		fournisseur.getAdresses().add(adrFournisseur);

		Produit produit = new Produit();
		produit.setNom("IPhone");
		produit.setPrix(100d);
		produit.setFournisseur(fournisseur);
		produit.setModele("XS 4");
		produit.setReference("IPhone XS 4");

		Utilisateur utiAdmin = new Utilisateur("admin", "123456", true, Role.SUPER_ADMIN);

		Utilisateur utiAngel = new Utilisateur("angel", "123456", true, Role.ADMIN);

		Utilisateur utiClient = new Utilisateur("esultan", "123456", true, Role.CLIENT);

		Adresse adrClient = new Adresse("1 rue de Toulouse", "33000", "Bordeaux");

		Client client = new Client();
		client.setCivilite(Civilite.M);
		client.setNom("SULTAN");
		client.setPrenom("Eric");
		client.setEmail("eric@semantik.fr");
		client.setDtNaissance(LocalDate.of(1978, 1, 4));
		client.setAdresse(adrClient);
		client.setUtilisateur(utiClient);

		Commentaire comment1 = new Commentaire();
		comment1.setClient(client);
		comment1.setProduit(produit);
		comment1.setDtCommentaire(LocalDateTime.now());
		comment1.setNote(18);
		comment1.setDetail("Produit qui correspond à mes attentes (je mens)");

		Commande commande = new Commande(LocalDateTime.now(), EtatCommande.ENCOURS, client);

		CommandeDetail commandeIphone = new CommandeDetail(200d, 2, produit, commande);

		commande.setPrixTotal(200d);

	}
}
