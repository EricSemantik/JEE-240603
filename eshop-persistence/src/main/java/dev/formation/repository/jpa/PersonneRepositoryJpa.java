package dev.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.formation.Application;
import dev.formation.model.Client;
import dev.formation.model.Fournisseur;
import dev.formation.model.Personne;
import dev.formation.repository.IPersonneRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class PersonneRepositoryJpa implements IPersonneRepository {

	@Override
	public List<Personne> findAll() {
		List<Personne> personnes = new ArrayList<Personne>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
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
			em = Application.getInstance().getEmf().createEntityManager();
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
			em = Application.getInstance().getEmf().createEntityManager();
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
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Query query = em.createQuery("delete from Personne p where p.id = ?1");
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

	@Override
	public List<Client> findAllClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fournisseur> findAllFournisseur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Personne> findByIdentifiant(String identifiant) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
