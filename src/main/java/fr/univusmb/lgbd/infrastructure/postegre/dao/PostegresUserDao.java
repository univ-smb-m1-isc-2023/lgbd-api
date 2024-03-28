package fr.univusmb.lgbd.infrastructure.postegre.dao;

import fr.univusmb.lgbd.infrastructure.postegre.jpa.PostegresUserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostegresUserDao {

    @Autowired
    private PostegresUserJPA postegresUserDao;
}
