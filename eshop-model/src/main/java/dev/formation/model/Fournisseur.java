package dev.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Transient;

public class Fournisseur extends Personne {
	private String responsable;
	@Transient
	private List<Adresse> adresses = new ArrayList<>();
	@Transient
	private List<Produit> produits = new ArrayList<>();

	public Fournisseur() {
		super();
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
