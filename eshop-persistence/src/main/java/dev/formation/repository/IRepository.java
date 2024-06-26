package dev.formation.repository;

import java.util.List;
import java.util.Optional;

import dev.formation.model.Produit;

public interface IRepository<T,PK> {
	List<T> findAll();
	Optional<T> findById(PK id);
	T save(T obj);
	void deleteById(PK id);
}
