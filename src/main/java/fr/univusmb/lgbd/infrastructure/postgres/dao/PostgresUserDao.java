package fr.univusmb.lgbd.infrastructure.postgres.dao;

import java.util.List;
import java.util.Optional;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.univusmb.lgbd.infrastructure.postgres.jpa.PostegresUserJPA;
import fr.univusmb.lgbd.model.User;

@Repository
public class PostgresUserDao implements Dao<User>{
    
    @Autowired
    private PostegresUserJPA userJPA;
    private Long nextId = 1L;

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        String hashedPswd = number.toString(16);

        while(hashedPswd.length() < 32) {
            hashedPswd = "0" + hashedPswd;
        }

        return hashedPswd;
    }

    @Override
    public User save(User element){
        assert element.getId() == null;
        User newUser = new User(nextId++, element.getName(), element.getEmail(), element.getPassword());
        return userJPA.save(newUser);
    }

    @Override
    public Optional<User> get(Long id) {
        return userJPA.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userJPA.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userJPA.findByEmail(email);
    }

    @Override
    public User update(User element) {
        assert element.getId() != null;
        return userJPA.save(element);
    }

    @Override
    public User delete(User element) {
        assert element.getId() != null;
        Optional<User> search = userJPA.findById(element.getId());
        assert search.isPresent();

        User deleteUser = search.get();
        User copy = new User(deleteUser.getId(), deleteUser.getName(), deleteUser.getEmail(), deleteUser.getPassword());

        userJPA.deleteById(element.getId());
        return copy;
    }
}
