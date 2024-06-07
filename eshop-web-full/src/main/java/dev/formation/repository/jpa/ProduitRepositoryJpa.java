package dev.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.formation.exception.EShopDataException;
import dev.formation.model.Produit;
import dev.formation.repository.IProduitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class ProduitRepositoryJpa implements IProduitRepository {

	@Inject
	private EntityManagerFactory emf;
	
	@Override
	public List<Produit> findAll() {
		List<Produit> produits = new ArrayList<Produit>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Produit> query = em.createQuery("select p from Produit p", Produit.class);
			produits = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new EShopDataException(e);
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return produits;
	}

	@Override
	public Optional<Produit> findById(Long id) {
		Optional<Produit> optProduit = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Produit produit = em.find(Produit.class, id);
			optProduit = Optional.of(produit);

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new EShopDataException(e);
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return optProduit;
	}

	@Override
	public Produit save(Produit obj) {
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
			throw new EShopDataException(e);
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return obj;
	}

	@Override
	public boolean deleteById(Long id) {
		int rows = 0;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createQuery("delete from Produit p where p.id = ?1");
			query.setParameter(1, id);

			rows = query.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new EShopDataException(e);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return rows > 0;
	}

	@Override
	public List<Produit> findAllByNoteLeatherThan(int note) {
		List<Produit> produits = new ArrayList<Produit>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Produit> query = em.createQuery("select p from Produit p join p.commentaires c where c.note < ?1", Produit.class);
			query.setParameter(1, note);
			
			produits = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new EShopDataException(e);
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return produits;
	}

}
