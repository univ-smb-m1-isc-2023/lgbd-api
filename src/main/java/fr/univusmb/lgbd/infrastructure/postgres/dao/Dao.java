package fr.univusmb.lgbd.infrastructure.postgres.dao;

import java.util.Optional;
import java.util.List;

public interface Dao<T> {

    T save(T element);

    Optional<T> get(Long id);

    List<T> getAll();

    T update(T element);

    T delete(T element);
}
