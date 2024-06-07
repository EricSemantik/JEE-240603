package dev.formation.api.views;

public interface Views {
	public static interface ViewNone {}
	
	public static interface ViewBasic {} 
	
	public static interface ViewAdresse extends ViewBasic {}
	
	public static interface ViewPersonne extends ViewBasic {}
	
	public static interface ViewClient extends ViewPersonne {}
	
	public static interface ViewFournisseur extends ViewPersonne {}
	
	public static interface ViewFournisseurProduits extends ViewFournisseur {}
	
	public static interface ViewProduit extends ViewBasic {}
	
	public static interface ViewProduitCommentaires extends ViewProduit {}
	
	public static interface ViewUtilisateur extends ViewBasic {}
	
	public static interface ViewUtilisateurDetail extends ViewUtilisateur {}
}
