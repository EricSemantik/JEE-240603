package dev.formation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "commande")
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dt_commande")
	private LocalDateTime dtCommande;

	@Column(name = "prix_total")
	private Double prixTotal;

	@Enumerated(EnumType.STRING)
	private EtatCommande etat;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToMany(mappedBy = "commande")
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
