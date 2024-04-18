package fr.univusmb.lgbd.infrastructure.postgres.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univusmb.lgbd.model.User;

public interface PostegresUserJPA extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
