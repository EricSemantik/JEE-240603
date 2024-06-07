package dev.formation.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
@NamedQuery(name = "findByIdentifiant", query = "select u from Utilisateur u where u.identifiant = :login")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false, unique = true)
	private String identifiant;

	@Column(name = "mot_de_passe", length = 200, nullable = false)
	private String motDePasse;

	private boolean active = true;

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "utilisateur_roles", joinColumns = @JoinColumn(name = "utilisateur_id"))
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
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
