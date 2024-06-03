package dev.formation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Commande {
	private Long id;
	private LocalDateTime dtCommande;
	private Double prixTotal;
	private EtatCommande etat;
	private Client client;
	private List<CommandeDetail> commandeDetails = new ArrayList<>();

	public Commande() {
		super();
	}

	public Commande(LocalDateTime dtCommande, EtatCommande etat, Client client) {
		super();
		this.dtCommande = dtCommande;
		this.etat = etat;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDtCommande() {
		return dtCommande;
	}

	public void setDtCommande(LocalDateTime dtCommande) {
		this.dtCommande = dtCommande;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public EtatCommande getEtat() {
		return etat;
	}

	public void setEtat(EtatCommande etat) {
		this.etat = etat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<CommandeDetail> getCommandeDetails() {
		return commandeDetails;
	}

	public void setCommandeDetails(List<CommandeDetail> commandeDetails) {
		this.commandeDetails = commandeDetails;
	}

}
