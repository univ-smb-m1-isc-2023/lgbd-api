package fr.univusmb.lgbd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = { "*" })
public class HelloController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PostgresUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private LocalDateTime startTime = LocalDateTime.now();

    @GetMapping("/startTime")
    public String startTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = startTime.format(formatter);
        return "App started at: " + formattedDateTime;
    }

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
        if (existingUser.isPresent()
                && passwordEncoder.matches(loginRequest.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.ok(existingUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userDao.update(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        User deletedUser = userDao.delete(user);
        if (deletedUser != null) {
            return ResponseEntity.ok(deletedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}