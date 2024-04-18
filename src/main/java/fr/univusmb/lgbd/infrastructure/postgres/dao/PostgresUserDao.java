package fr.univusmb.lgbd.infrastructure.postgres.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.univusmb.lgbd.infrastructure.postgres.jpa.PostegresUserJPA;
import fr.univusmb.lgbd.model.User;

@Repository
public class PostgresUserDao implements Dao<User>{
    
    @Autowired
    private PostegresUserJPA userJPA;
    private Long nextId = 1L;

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
