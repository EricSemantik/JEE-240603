package dev.formation.model;

import java.time.LocalDateTime;

import jakarta.persistence.Transient;

public class Commentaire {
	private Long id;
	private LocalDateTime dtCommentaire = LocalDateTime.now();
	private int note = 0;
	private String detail;
	@Transient
	private Produit produit;
	@Transient
	private Client client;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDtCommentaire() {
		return dtCommentaire;
	}

	public void setDtCommentaire(LocalDateTime dtCommentaire) {
		this.dtCommentaire = dtCommentaire;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
