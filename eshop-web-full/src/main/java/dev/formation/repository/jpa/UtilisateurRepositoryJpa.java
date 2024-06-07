package dev.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.formation.exception.EShopDataException;
import dev.formation.model.Adresse;
import dev.formation.model.Utilisateur;
import dev.formation.repository.IUtilisateurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class UtilisateurRepositoryJpa implements IUtilisateurRepository {

	@Inject
	private EntityManagerFactory emf;

	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u", Utilisateur.class);
			utilisateurs = query.getResultList();

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

		return utilisateurs;
	}

	@Override
	public Optional<Utilisateur> findById(Long id) {
		Optional<Utilisateur> optUtilisateur = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Utilisateur utilisateur = em.find(Utilisateur.class, id);
			optUtilisateur = Optional.of(utilisateur);

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

		return optUtilisateur;
	}

	@Override
	public Utilisateur save(Utilisateur obj) {
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

			Query query = em.createQuery("delete from Utilisateur u where u.id = ?1");
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
	public Optional<Utilisateur> findByIdentifiant(String identifiant) {
		Optional<Utilisateur> optUtilisateur = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Utilisateur> query = em.createNamedQuery("findByIdentifiant", Utilisateur.class);
			query.setParameter("login", identifiant);
			Utilisateur utilisateur = query.getSingleResult();

			optUtilisateur = Optional.of(utilisateur);

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

		return optUtilisateur;
	}

	@Override
	public Optional<Utilisateur> findByIdWithRoles(Long id) {
		Optional<Utilisateur> optUtilisateur = Optional.empty();
		
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Utilisateur> query = em.createQuery("select distinct u from Utilisateur u left join fetch u.roles where u.id = ?1", Utilisateur.class);
			query.setParameter(1, id);
			
			Utilisateur utilisateur = query.getSingleResult();
			optUtilisateur = Optional.of(utilisateur);
			
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
		
		return optUtilisateur;
	}

}
