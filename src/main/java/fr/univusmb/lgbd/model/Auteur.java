package fr.univusmb.lgbd.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "auteur")
public class Auteur {

    @Id
    private Long auteurId;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @OneToMany(mappedBy = "isbn")
    private List<Bd> bd;

    public Auteur() {
        this(null, null, null);
    }

    public Auteur(Long id, String nom, String prenom) {
        this.auteurId = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Auteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public void addBd(Bd bd) {
        this.bd.add(bd);
    }

    /* Getter */

    public Long getId() {
        return auteurId;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    /* *** */
}
