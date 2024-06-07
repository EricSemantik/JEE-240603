package dev.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.formation.exception.EShopDataException;
import dev.formation.model.Client;
import dev.formation.model.Fournisseur;
import dev.formation.model.Personne;
import dev.formation.repository.IPersonneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class PersonneRepositoryJpa implements IPersonneRepository {

	@Inject
	private EntityManagerFactory emf;

	@Override
	public List<Personne> findAll() {
		List<Personne> personnes = new ArrayList<Personne>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Personne> query = em.createQuery("select p from Personne p", Personne.class);
			personnes = query.getResultList();

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

		return personnes;
	}

	@Override
	public Optional<Personne> findById(Long id) {
		Optional<Personne> optPersonne = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Personne personne = em.find(Personne.class, id);
			optPersonne = Optional.of(personne);

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

		return optPersonne;
	}

	@Override
	public Personne save(Personne obj) {
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

			Query query = em.createQuery("delete from Personne p where p.id = ?1");
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
	public List<Client> findAllClient() {
		List<Client> clients = new ArrayList<Client>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);
			clients = query.getResultList();

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

		return clients;
	}

	@Override
	public List<Fournisseur> findAllFournisseur() {
		List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Fournisseur> query = em.createQuery("select f from Fournisseur f", Fournisseur.class);
			fournisseurs = query.getResultList();

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

		return fournisseurs;
	}

	@Override
	public Optional<Personne> findByIdentifiant(String identifiant) {
		Optional<Personne> optPersonne = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Personne> query = em.createQuery("select p from Personne p where p.utilisateur.identifiant = ?1",
					Personne.class);
			query.setParameter(1, identifiant);
			Personne personne = query.getSingleResult();

			optPersonne = Optional.of(personne);

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

		return optPersonne;
	}

	@Override
	public Optional<Fournisseur> findFournisseurByIdWithProduits(Long id) {
		Optional<Fournisseur> optFournisseur = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Fournisseur> query = em.createQuery(
					"select distinct f from Fournisseur f left join fetch f.produits where f.id = ?1",
					Fournisseur.class);
			query.setParameter(1, id);
			Fournisseur fournisseur = query.getSingleResult();

			optFournisseur = Optional.of(fournisseur);

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

		return optFournisseur;
	}

	@Override
	public Optional<Long> findClientIdByAdresseId(Long id) {
		Optional<Long> optClient = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Long> query = em.createQuery("select c.id from Client c where c.adresse.id = ?1", Long.class);
			query.setParameter(1, id);
			Long clientId = query.getSingleResult();

			optClient = Optional.of(clientId);

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

		return optClient;
	}

	@Override
	public Optional<Long> findFournisseurIdByAdresseId(Long id) {
		Optional<Long> optFournisseur = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Long> query = em.createQuery("select f.id from Fournisseur f join f.adresses a where a.id = ?1",
					Long.class);
			query.setParameter(1, id);
			Long fournisseurId = query.getSingleResult();

			optFournisseur = Optional.of(fournisseurId);

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

		return optFournisseur;
	}

}
