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

    @Column(name = "bd", nullable = true)
    @OneToMany(mappedBy = "isbn")
    private List<Bd> bd;

    public Auteur() {
        this.auteurId = null;
        this.nom = null;
    }

    public Auteur(Long id, String nom) {
        this.auteurId = id;
        this.nom = nom;
    }

    public Auteur(String nom) {
        this.nom = nom;
    }

    public void addBd(Bd bd) {
        this.bd.add(bd);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /* Getter */

    public Long getId() {
        return auteurId;
    }

    public String getNom() {
        return nom;
    }

    public List<Bd> getBd() {
        return bd;
    }

    /* *** */
}
