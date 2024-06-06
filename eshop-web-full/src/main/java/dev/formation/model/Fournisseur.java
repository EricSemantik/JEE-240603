package dev.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("fournisseur")
public class Fournisseur extends Personne {
	@Column(length = 200)
	private String responsable;
	@OneToMany
	@JoinTable(name = "fournisseur_adresses", joinColumns = @JoinColumn(name = "fournisseur_id"), inverseJoinColumns = @JoinColumn(name = "adresse_id"))
	private List<Adresse> adresses = new ArrayList<>();
	@OneToMany(mappedBy = "fournisseur")
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
