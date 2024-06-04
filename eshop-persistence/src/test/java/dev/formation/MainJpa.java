package dev.formation;

import java.util.List;

import dev.formation.model.Adresse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class MainJpa {

	public static void main(String[] args) {
		EntityManagerFactory emf = Application.getInstance().getEmf();

		Adresse adrFournisseur = new Adresse("1 rue Silicon", "25000", "Silicon Valley"); // new ou transient
		
		Adresse adrClient = new Adresse("1 rue de Toulouse", "33000", "Bordeaux"); // new ou transient
		
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.persist(adrFournisseur); // managed

			adrFournisseur.setRue("ddsqdsqdsq"); // dirty checking - synchro auto

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			adrClient = em.merge(adrClient); // managed

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		adrFournisseur.setCodePostal("44120"); // detached

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Adresse adrFournisseurCopy = em.merge(adrFournisseur); // adrFournisseur : detached - adrFournisseurCopy :
																	// managed
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		try {
			em = emf.createEntityManager();

			Adresse adrFournisseurFind = em.find(Adresse.class, adrFournisseur.getId()); // managed

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		try {
			em = emf.createEntityManager();

			TypedQuery<Adresse> query = em.createQuery("select a from Adresse a", Adresse.class);

			List<Adresse> adresses = query.getResultList(); // managed

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.remove(em.merge(adrFournisseur));

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

//		Query queryDeleted = em.createQuery("delete from Adresse a where a.id = ?1");
//		queryDeleted.setParameter(1, adrClient.getId());

			Query queryDeleted = em.createQuery("delete from Adresse a where a.id = :monId");
			queryDeleted.setParameter("monId", adrClient.getId());

			queryDeleted.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		emf.close();
	}

}
