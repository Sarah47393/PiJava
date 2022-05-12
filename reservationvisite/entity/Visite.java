/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationvisite.entity;

/**
 *
 * @author Mortadha
 */
public class Visite {
    private int id;
    private String date;
    private String description;
    private String sexe;
    private String num_carte;
    private String montant;

    public Visite() {
    }

    public Visite(String date, String description, String sexe, String num_carte, String montant) {
        this.date = date;
        this.description = description;
        this.sexe = sexe;
        this.num_carte = num_carte;
        this.montant = montant;
    }

    public Visite(int id, String date, String description, String sexe, String num_carte, String montant) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.sexe = sexe;
        this.num_carte = num_carte;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(String num_carte) {
        this.num_carte = num_carte;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Visite{" + "id=" + id + ", date=" + date + ", description=" + description + ", sexe=" + sexe + ", num_carte=" + num_carte + ", montant=" + montant + '}'+"\n";
    }

    
}
