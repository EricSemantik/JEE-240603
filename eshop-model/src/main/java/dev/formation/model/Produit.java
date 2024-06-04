package dev.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Transient;

public class Produit {
	private Long id;
	private String nom;
	private Double prix;
	private String reference;
	private String modele;
	@Transient
	private Fournisseur fournisseur;
	@Transient
	private List<CommandeDetail> commandeDetails = new ArrayList<>();
	@Transient
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
