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
public class Dons {
    private int id;
    private int user_id;
    private String num_carte;
    private String montant;

    public Dons() {
    }

    public Dons(int user_id, String num_carte, String montant) {
        this.user_id = user_id;
        this.num_carte = num_carte;
        this.montant = montant;
    }

    public Dons(int id, int user_id, String num_carte, String montant) {
        this.id = id;
        this.user_id = user_id;
        this.num_carte = num_carte;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
        return "Dons{" + "id=" + id + ", user_id=" + user_id + ", num_carte=" + num_carte + ", montant=" + montant + '}'+"\n";
    }
    
}
