package dev.formation;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainJpa {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("eshop-pu");
		
		
		emf.close();
	}

}
