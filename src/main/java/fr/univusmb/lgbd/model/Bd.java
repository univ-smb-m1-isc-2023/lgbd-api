package fr.univusmb.lgbd.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "bd")
public class Bd {
    @Id
    @Column(name = "isbn", nullable = false, unique = true)
    private Long isbn;

    @Column(name = "titre", nullable = false)
    private String titre;

    @ManyToOne
    private Auteur auteur;

    @Column(name = "editeur", nullable = false)
    private String editeur;

    @Column(name = "annee", nullable = false)
    private Integer annee;

    @Column(name = "genre")
    private String genre;

    @Column(name = "resume")
    private String resume;

    @Column(name = "note")
    private Integer note;

    @Column(name = "image")
    private String image;

    @ManyToOne
    private Serie serie;

    public Bd(Long isbn,
            String titre,
            String editeur,
            Integer annee) {

        this.isbn = isbn;
        this.titre = titre;
        this.editeur = editeur;
        this.annee = annee;
    }

    public Bd(Long isbn,
            String titre,
            String editeur,
            Integer annee,
            String genre,
            String resume,
            Integer note,
            Auteur auteur,
            String image,
            Serie serie) {

        this.isbn = isbn;
        this.titre = titre;
        this.editeur = editeur;
        this.annee = annee;
        this.auteur = auteur;
        this.genre = genre;
        this.resume = resume;
        this.note = note;
        this.image = image;
        this.serie = serie;
    }

    public Bd() {

    }



    /* Comparaison */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Bd))
            return false;
        Bd bd = (Bd) o;
        return bd.isbn.equals(isbn);
    }

    public boolean sameAuthor(Bd bd) {
        return bd.auteur.equals(auteur);
    }

    /* */

    /* Gestion Bd */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    /* *** */

    /* getter */
    public Long getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public Integer getAnnee() {
        return annee;
    }

    public String getGenre() { return genre;}

    public String getResume() { return resume;}

    public Integer getNote() { return note;}

    public String getImage() { return image; }

    public Auteur getAuteur() { return auteur ; }

    public Serie getSerie() { return serie ; }

}
