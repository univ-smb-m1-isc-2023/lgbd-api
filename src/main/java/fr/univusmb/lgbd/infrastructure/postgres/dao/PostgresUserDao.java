package fr.univusmb.lgbd.infrastructure.postgres.dao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import fr.univusmb.lgbd.infrastructure.postgres.jpa.PostegresUserJPA;
import fr.univusmb.lgbd.model.User;

@Repository
public class PostgresUserDao implements Dao<User>{
    
    @Autowired
    private PostegresUserJPA userJPA;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Random random = new Random();

    private Long generateUniqueId(){
        Long id = random.nextLong();
        if(userJPA.findById(id).isPresent()){
            return generateUniqueId();
        }
        return id;
    }

    @Override
    public User save(User element){
        assert element.getId() == null;
        Long uniqueId = generateUniqueId();
        User newUser = new User(uniqueId, element.getName(), element.getEmail(), passwordEncoder.encode(element.getPassword()), element.isAdmin());
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
        Optional<User> existingUserOpt = userJPA.findById(element.getId());
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setName(element.getName());
            existingUser.setEmail(element.getEmail());
            existingUser.setPassword(passwordEncoder.encode(element.getPassword()));
            return userJPA.save(existingUser);
        } else {
            throw new NoSuchElementException("User with id " + element.getId() + " not found");
        }
    }

    @Override
    public User delete(User element) {
        assert element.getId() != null;
        Optional<User> search = userJPA.findById(element.getId());
        assert search.isPresent();

        User deleteUser = search.get();
        User copy = new User(deleteUser.getId(), deleteUser.getName(), deleteUser.getEmail(), deleteUser.getPassword(), deleteUser.isAdmin());

        userJPA.deleteById(element.getId());
        return copy;
    }
}
