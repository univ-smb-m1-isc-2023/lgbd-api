package fr.univusmb.lgbd.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "serie")
public class Serie {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @OneToMany(mappedBy = "bd")
    private List<Bd> bd;

    public Serie(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /* Gestion Serie */
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
    /* *** */
    
}
