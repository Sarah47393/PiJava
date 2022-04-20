package Model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sarah
 */
public class Collaborateur  {
      private int id,NumeroTel;
    private String NomCollaborateur,PrenomCollaborateur,Role,Entreprise;

    public int getId() {
        return id;
    }

    public String getNomCollaborateur() {
        return NomCollaborateur;
    }

    public String getPrenomCollaborateur() {
        return PrenomCollaborateur;
    }
    public String getRole() {
        return Role;
    }
    public int getNumeroTel() {
        return NumeroTel;
    }
     public String getEntreprise() {
        return Entreprise;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setNomCollaborateur(String NomCollaborateur) {
        this.NomCollaborateur = NomCollaborateur;
    }

    public void setPrenomCollaborateur(String PrenomCollaborateur) {
        this.PrenomCollaborateur = PrenomCollaborateur;
    }
    public void setRole(String Role) {
        this.Role = Role;
    }
    public void setNumeroTel(int NumeroTel) {
        this.NumeroTel = NumeroTel;
    }
     public void setEntreprise(String Entreprise) {
        this.Entreprise = Entreprise;
    }

    public Collaborateur() {
    }

   public Collaborateur(int id, String NomCollaborateur, String PrenomCollaborateur, String Role, int NumeroTel, String Entreprise) {
        this.id = id;
        this.NomCollaborateur = NomCollaborateur;
        this.PrenomCollaborateur = PrenomCollaborateur;
        this.Role = Role;
        this.NumeroTel = NumeroTel;
        this.Entreprise = Entreprise;
    }

   public Collaborateur(String NomCollaborateur, String PrenomCollaborateur, String Role, int NumeroTel, String Entreprise) {
        this.NomCollaborateur = NomCollaborateur;
        this.PrenomCollaborateur = PrenomCollaborateur;
        this.Role = Role;
        this.NumeroTel = NumeroTel;
        this.Entreprise = Entreprise;
    }

    @Override
    public String toString() {
        return "Collaborateur{" + "id=" + id + ", NomCollaborateur=" + NomCollaborateur + ", PrenomCollaborateur=" + PrenomCollaborateur +  " , Role=" + Role +  ", NumeroTel=" + NumeroTel + ", Entreprise=" + Entreprise + "\n";
    }
}