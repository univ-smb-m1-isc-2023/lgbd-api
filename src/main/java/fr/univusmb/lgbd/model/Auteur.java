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
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @OneToMany(mappedBy = "bd")
    @Column(name = "bd")
    private List<Bd> bd;

    public Auteur(Long id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    /* Gestion Auteur */
    public void addBd(Bd bd) {
        this.bd.add(bd);
    }

    public void removeBd(Bd bd) {
        this.bd.remove(bd);
    }

    /* *** */

    /* getter */

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    /* *** */
}
