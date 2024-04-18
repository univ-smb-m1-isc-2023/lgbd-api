package fr.univusmb.lgbd.infrastructure.postgres.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import fr.univusmb.lgbd.infrastructure.postgres.jpa.PostegresUserJPA;
import fr.univusmb.lgbd.model.User;

@Repository
public class PostgresUserDao implements Dao<User>{
    
    @Autowired
    private PostegresUserJPA userJPA;
    private Long nextId = 1L;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(User element){
        assert element.getId() == null;
        User newUser = new User(nextId++, element.getName(), element.getEmail(), bCryptPasswordEncoder.encode(element.getPassword()));
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
        element.setPassword(bCryptPasswordEncoder.encode(element.getPassword()));
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
