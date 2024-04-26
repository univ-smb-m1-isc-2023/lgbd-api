package fr.univusmb.lgbd.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "identifiant", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "admin", nullable = false)
    private boolean admin;

    @ManyToMany
    private List<Bd> bdLiked;

    @ManyToMany
    private List<Bd> collection;

    @ManyToMany
    private List<Bd> bdPret;

    @ManyToMany
    private List<Serie> seriesSuivi;

    @ManyToMany
    private List<Auteur> auteursSuivi;

    public User() {

        collection = new ArrayList<Bd>();
        bdLiked = new ArrayList<Bd>();
        bdPret = new ArrayList<Bd>();
        seriesSuivi = new ArrayList<Serie>();
    }

    public User(String name, String email, String password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String name, String email, String password) {
        this();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String name, String email, String password, boolean admin) {
        this();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void addFollowAuteur(Auteur auteur) {
        auteursSuivi.add(auteur);
    }

    public void addFollowSerie(Serie serie) {
        seriesSuivi.add(serie);
    }

    public void addLikedBd(Bd bd) {
        bdLiked.add(bd);
    }

    public void addBd(Bd bd) {
        collection.add(bd);
    }

    public void addPretBd(Bd bd) {
        bdPret.add(bd);
    }

    public void removeFollowAuteur(Auteur auteur) {
        auteursSuivi.remove(auteur);
    }

    public void removeFollowSerie(Serie serie) {
        seriesSuivi.remove(serie);
    }

    public void removeLikedBd(Bd bd) {
        bdLiked.remove(bd);
    }

    public void removeBd(Bd bd) {
        collection.remove(bd);
    }

    public void removePretBd(Bd bd) {
        bdPret.remove(bd);
    }

    public List<Auteur> getAuteursSuivi() {
        return auteursSuivi;
    }

    public List<Serie> getSeriesSuivi() {
        return seriesSuivi;
    }

    public List<Bd> getBdLiked() {
        return bdLiked;
    }

    public List<Bd> getCollection() {
        return collection;
    }

    public List<Bd> getBdPret() {
        return bdPret;
    }

}
