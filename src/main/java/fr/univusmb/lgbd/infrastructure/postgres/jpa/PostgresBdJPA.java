package fr.univusmb.lgbd.infrastructure.postgres.jpa;

import fr.univusmb.lgbd.model.Auteur;
import fr.univusmb.lgbd.model.Bd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresBdJPA extends JpaRepository<Bd, Long>{

}
