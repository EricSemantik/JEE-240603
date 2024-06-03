package dev.formation.model;

public enum Role {
	CLIENT, FOURNISSEUR, ADMIN, SUPER_ADMIN;
	
	public String authority() {
		return "ROLE_" + name();
	}
}
