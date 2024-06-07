package dev.formation.api.dto;

public class ClientDto {
	private Long id;
	private int civ;
	private String nom;
	private String prenom;
	private String dateNaissance;
	private Long utilisateurId;
	private String utilisateurLogin;
	private String utilisateurPassword;
	private boolean utilisateurActive;
	private String rue;
	private String codePostal;
	private String ville;

	public ClientDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCiv() {
		return civ;
	}

	public void setCiv(int civ) {
		this.civ = civ;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Long getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public String getUtilisateurLogin() {
		return utilisateurLogin;
	}

	public void setUtilisateurLogin(String utilisateurLogin) {
		this.utilisateurLogin = utilisateurLogin;
	}

	public String getUtilisateurPassword() {
		return utilisateurPassword;
	}

	public void setUtilisateurPassword(String utilisateurPassword) {
		this.utilisateurPassword = utilisateurPassword;
	}

	public boolean isUtilisateurActive() {
		return utilisateurActive;
	}

	public void setUtilisateurActive(boolean utilisateurActive) {
		this.utilisateurActive = utilisateurActive;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
