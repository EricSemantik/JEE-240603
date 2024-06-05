package dev.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produit")
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 150)
	private String nom;

	private Double prix;

	@Column(name = "ref", length = 100)
	private String reference;

	@Column(length = 100)
	private String modele;

	@ManyToOne
	@JoinColumn(name = "fournisseur_id")
	private Fournisseur fournisseur;

	@OneToMany(mappedBy = "produit")
	private List<CommandeDetail> commandeDetails = new ArrayList<>();

	@OneToMany(mappedBy = "produit")
	private List<Commentaire> commentaires = new ArrayList<>();

	public Produit() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public List<CommandeDetail> getCommandeDetails() {
		return commandeDetails;
	}

	public void setCommandeDetails(List<CommandeDetail> commandeDetails) {
		this.commandeDetails = commandeDetails;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

}
