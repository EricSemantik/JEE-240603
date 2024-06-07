package dev.formation.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.formation.model.CommandeDetail;
import dev.formation.repository.ICommandeDetailRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class CommandeDetailRepositoryJpa implements ICommandeDetailRepository {

	@Inject
	private EntityManagerFactory emf;
	
	@Override
	public List<CommandeDetail> findAll() {
		List<CommandeDetail> commandes = new ArrayList<CommandeDetail>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<CommandeDetail> query = em.createQuery("select c from CommandeDetail c", CommandeDetail.class);
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
	public Optional<CommandeDetail> findById(Long id) {
		Optional<CommandeDetail> optCommandeDetail = Optional.empty();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			CommandeDetail commandeDetail = em.find(CommandeDetail.class, id);
			optCommandeDetail = Optional.of(commandeDetail);

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

		return optCommandeDetail;
	}

	@Override
	public CommandeDetail save(CommandeDetail obj) {
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

			Query query = em.createQuery("delete from CommandeDetail c where c.id = ?1");
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
