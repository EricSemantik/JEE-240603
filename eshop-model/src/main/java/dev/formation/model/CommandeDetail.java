package dev.formation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "achat")
public class CommandeDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int quantite;

	private Double prix;

	@ManyToOne
	@JoinColumn(name = "commande_id")
	private Commande commande;

	@ManyToOne
	@JoinColumn(name = "produit_id")
	private Produit produit;

	public CommandeDetail() {

	}

	public CommandeDetail(Double prix, int quantite, Produit produit, Commande commande) {
		this.prix = prix;
		this.quantite = quantite;
		this.produit = produit;
		this.commande = commande;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Override
	public String toString() {
		return this.prix + " euros, " + this.quantite + " produit(s) : " + this.produit;
	}
}
