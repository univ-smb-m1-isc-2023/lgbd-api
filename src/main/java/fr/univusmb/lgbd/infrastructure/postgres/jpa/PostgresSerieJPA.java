package fr.univusmb.lgbd.infrastructure.postgres.jpa;

import fr.univusmb.lgbd.model.Serie;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresSerieJPA extends JpaRepository<Serie, Long> {
}
