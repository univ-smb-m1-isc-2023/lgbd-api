package fr.univusmb.lgbd.infrastructure.postegre.jpa;

import fr.univusmb.lgbd.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostegresUserJPA extends JpaRepository<User, Long> {
}
