package dev.formation.model;

import java.util.ArrayList;
import java.util.List;

public class Fournisseur extends Personne {
	private String responsable;
	private List<Adresse> adresses = new ArrayList<>();
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
