package fr.univusmb.lgbd.infrastructure.postgres.jpa;

import fr.univusmb.lgbd.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresAuteurJPA extends JpaRepository<Auteur, Long> {

}
