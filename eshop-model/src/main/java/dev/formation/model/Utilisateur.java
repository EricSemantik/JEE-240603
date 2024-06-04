package dev.formation.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Transient;

public class Utilisateur {
	private Long id;
	private String identifiant;
	private String motDePasse;
	private boolean active = true;
	@Transient
	private Set<Role> roles = new HashSet<>();

	public Utilisateur() {
		super();
	}

	public Utilisateur(String identifiant, String motDePasse, boolean active, Role... roles) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.active = active;
		this.roles = Set.of(roles);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
