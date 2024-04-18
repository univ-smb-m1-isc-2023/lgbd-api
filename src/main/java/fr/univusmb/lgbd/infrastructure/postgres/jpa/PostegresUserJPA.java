package fr.univusmb.lgbd.infrastructure.postgres.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univusmb.lgbd.model.User;

public interface PostegresUserJPA extends JpaRepository<User, Long> {
}
