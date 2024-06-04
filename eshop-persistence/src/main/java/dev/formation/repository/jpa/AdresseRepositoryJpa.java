package dev.formation.repository.jpa;

import java.util.List;
import java.util.Optional;

import dev.formation.Application;
import dev.formation.model.Adresse;
import dev.formation.repository.IAdresseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AdresseRepositoryJpa implements IAdresseRepository {

	@Override
	public List<Adresse> findAll() {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			

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
		return null;
	}

	@Override
	public Optional<Adresse> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Adresse save(Adresse obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Adresse> findAllByVille(String ville) { // select adr from Adresse adr where adr.ville = ?1
		// TODO Auto-generated method stub
		return null;
	}

}
