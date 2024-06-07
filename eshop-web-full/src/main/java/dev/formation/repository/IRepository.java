package dev.formation.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, PK> {
	List<T> findAll();

	Optional<T> findById(PK id);

	T save(T obj);

	boolean deleteById(PK id);
}
