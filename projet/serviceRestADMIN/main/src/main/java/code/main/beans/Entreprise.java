package code.main.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "t_entreprise")
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nom", length = 100)
    private String nom;

    @Column(name = "adresse", length = 255)
    private String adresse;

    public Entreprise() {
    }

    public Entreprise(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return nom + " " + adresse;
    }
}
