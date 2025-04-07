package code.main.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "T_Entreprise")
public class Entreprise {

    @Id
    @Column(name = "PK_Entreprise")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // si MySQL auto-incrémente
    private Integer pkEntreprise;

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

    public Integer getPkEntreprise() {
        return pkEntreprise;
    }

    public void setPkEntreprise(Integer pkEntreprise) {
        this.pkEntreprise = pkEntreprise;
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
        return "Entreprise{" +
                "pkEntreprise=" + pkEntreprise +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
