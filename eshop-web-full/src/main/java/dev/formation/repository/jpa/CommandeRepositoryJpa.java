package dev.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.formation.model.Commande;
import dev.formation.repository.ICommandeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class CommandeRepositoryJpa implements ICommandeRepository {

	@Inject
	private EntityManagerFactory emf;

	@Override
	public List<Commande> findAll() {
		List<Commande> commandes = new ArrayList<Commande>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Commande> query = em.createQuery("select c from Commande c", Commande.class);
			commandes = query.getResultList();

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

		return commandes;
	}

	@Override
	public Optional<Commande> findById(Long id) {
		Optional<Commande> optCommande = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Commande commande = em.find(Commande.class, id);
			optCommande = Optional.of(commande);

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

		return optCommande;
	}

	@Override
	public Commande save(Commande obj) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			obj = em.merge(obj);

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

		return obj;
	}

	@Override
	public void deleteById(Long id) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createQuery("delete from Commande c where c.id = ?1");
			query.setParameter(1, id);

			query.executeUpdate();

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
	}

}
