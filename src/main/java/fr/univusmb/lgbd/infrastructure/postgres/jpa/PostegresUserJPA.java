package fr.univusmb.lgbd.infrastructure.postgres.jpa;

import fr.univusmb.lgbd.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostegresUserJPA extends JpaRepository<User, Long> {
}
