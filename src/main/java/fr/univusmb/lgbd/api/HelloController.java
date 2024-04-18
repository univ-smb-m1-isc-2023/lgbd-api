package fr.univusmb.lgbd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.univusmb.lgbd.infrastructure.postgres.dao.PostgresUserDao;
import fr.univusmb.lgbd.model.User;

import java.util.List;

import javax.sql.DataSource;

@RestController
@CrossOrigin(origins = {"*"})
public class HelloController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PostgresUserDao userDao;

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
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM users";

        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("identifiant"));
            user.setName(rs.getString("nom"));
            user.setEmail(rs.getString("email"));
            return user;
        });
        return users;
    }

    @PostMapping("/addUser")
    public ResponseEntity<Void> addUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        System.out.println("CREATE : name : " + name + " email : " + email);
        userDao.save(new User(name, email, password));
        return ResponseEntity.ok().build();
    }
}