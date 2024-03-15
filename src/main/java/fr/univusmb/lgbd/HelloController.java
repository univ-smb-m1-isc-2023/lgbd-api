package fr.univusmb.lgbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class HelloController {
//    @Autowired
    private DataSource dataSource ;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

//    public DataSource getDataSource() {
//        return dataSource;
//    }

    @GetMapping("/base")
    public String base(){
        System.out.println(dataSource);
        if(dataSource !=null) {
            return "Hello base de données !";
        }else{
            return "Echec bdd ";
        }
//        return "ok ok ";
    }
}