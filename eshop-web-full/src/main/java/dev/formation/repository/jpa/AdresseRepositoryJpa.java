package dev.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.formation.model.Adresse;
import dev.formation.repository.IAdresseRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class AdresseRepositoryJpa implements IAdresseRepository {

	@Inject
	private EntityManagerFactory emf;
	
	@Override
	public List<Adresse> findAll() {
		List<Adresse> adresses = new ArrayList<Adresse>();
		
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Adresse> query = em.createQuery("select a from Adresse a", Adresse.class);
			adresses = query.getResultList();
			
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
		
		return adresses;
	}

	@Override
	public Optional<Adresse> findById(Long id) {
		Optional<Adresse> optAdresse = Optional.empty();
		
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Adresse adresse = em.find(Adresse.class, id);
			optAdresse = Optional.of(adresse);

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
		
		return optAdresse;
	}

	@Override
	public Adresse save(Adresse obj) {
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
			
//			Adresse adresse = em.find(Adresse.class, id);
//			em.remove(adresse);

			Query query = em.createQuery("delete from Adresse adr where adr.id = ?1");
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
	public List<Adresse> findAllByVille(String ville) { // select adr from Adresse adr where adr.ville = ?1
List<Adresse> adresses = new ArrayList<Adresse>();
		
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Adresse> query = em.createQuery("select a from Adresse a where a.ville = :ville", Adresse.class);
			query.setParameter("ville", ville);
			
			adresses = query.getResultList();
			
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
		
		return adresses;
	}

}
