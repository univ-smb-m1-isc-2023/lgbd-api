package fr.univusmb.lgbd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresUserDao;
import fr.univusmb.lgbd.model.LoginRequest;
import fr.univusmb.lgbd.model.User;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

@RestController
@CrossOrigin(origins = {"*"})
public class HelloController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PostgresUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    // public DataSource getDataSource() {
    // return dataSource;
    // }

    @GetMapping("/base")
    public String base() {
        System.out.println(dataSource);
        if (dataSource != null) {
            return "Hello base de données !";
        } else {
            return "Echec bdd ";
        }
        // return "ok ok ";
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userDao.getAll();
    }

    @PostMapping("/addUser")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        System.out.println("CREATE : name : " + user.getName() + " email : " + user.getEmail());
        userDao.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checklogin")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> existingUser = userDao.findByEmail(loginRequest.getEmail());
        if(existingUser.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), existingUser.get().getPassword())){
            return ResponseEntity.ok(existingUser.get());   
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser = userDao.update(user);
        if(updatedUser != null){
            return ResponseEntity.ok(updatedUser);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestBody User user){
        User deletedUser = userDao.delete(user);
        if(deletedUser != null){
            return ResponseEntity.ok(deletedUser);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}