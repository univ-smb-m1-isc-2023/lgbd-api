package fr.univusmb.lgbd.infrastructure.postegre.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostegresUserDao {
    private final JdbcTemplate jdbcTemplate;
    private int id = 1;

    @Autowired
    public PostegresUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(String name, String email, String password) {
        id++;
        String sql = "INSERT INTO users (identifiant, nom, email, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, name, email, password);
    }
}
