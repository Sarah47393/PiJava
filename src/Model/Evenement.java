/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
/**
 *
 * @author Sarah
 */
public class Evenement  {
      private int id,NombreDeParticipants,Billet,collaborateur1;
    private String NomEvenement,Description,QrCode,DateDeEvenement,NomCollaborateur,PrenomCollaborateur;
    private float longitude,latitude;

    public Evenement(int ide, int Collaborateur1, String nom_evenement, String nom_collaborateur, String prenom_collaborateur) {
        this.id=ide;
        this.collaborateur1=Collaborateur1;
        this.NomEvenement=nom_evenement;
        this.NomCollaborateur=nom_collaborateur;
        this.PrenomCollaborateur=prenom_collaborateur;
        
        
       
    }

 

    

    public String getNomEvenement() {
        return NomEvenement;
    }
    public String getDateDeEvenement() {
        return DateDeEvenement;
    }
    public int getId() {
        return id;
    }

    public String getDescription() {
        return Description;
    }
    public String getQrCode() {
        return QrCode;
    }
    public int getCollaborateur1() {
        return collaborateur1;
    }
    public int getNombreDeParticipants() {
        return NombreDeParticipants;
    }
    public int getBillet() {
        return Billet;
    }
     public float getLongitude() {
        return longitude;
    }
     public float getLatitude() {
        return latitude;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setNomEvenement(String NomEvenement) {
        this.NomEvenement = NomEvenement;
    }
    public void setDateDeEvenement(String DateDeEvenement) {
        this.DateDeEvenement = DateDeEvenement;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    public void setQrCode(String QrCode) {
        this.QrCode = QrCode;
    }
    public void setCollaborateur1(int collaborateur1) {
        this.collaborateur1 = collaborateur1;
    }
     public void setNombreDeParticipants(int NombreDeParticipants) {
        this.NombreDeParticipants = NombreDeParticipants;
    }
     public void setBillet(int Billet) {
        this.Billet = Billet;
    }
     public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
     public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Evenement() {
    }

    public Evenement(int id,int collaborateur1, String NomEvenement, String Description, int NombreDeParticipants, String QrCode, int Billet,String DateDeEvenement,float longitude,float latitude) {
        this.id = id;
        this.collaborateur1 = collaborateur1;
        this.NomEvenement = NomEvenement;
        this.Description = Description;
        this.NombreDeParticipants = NombreDeParticipants;
        this.QrCode = QrCode;
        this.Billet = Billet;
        this.DateDeEvenement = DateDeEvenement;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public Evenement( int collaborateur1,String NomEvenement, String Description, int NombreDeParticipants, String QrCode, int Billet,String DateDeEvenement,float longitude,float latitude) {   
        this.collaborateur1 = collaborateur1;
        this.NomEvenement = NomEvenement;
        this.Description = Description;
        this.NombreDeParticipants = NombreDeParticipants;
        this.QrCode = QrCode;
        this.Billet = Billet;
        this.DateDeEvenement = DateDeEvenement;
        this.longitude = longitude;
        this.latitude = latitude;
    }

/*    @Override
    public String toString() {
  return "Evenement{" + "id=" + id + ", collaborateur1=" + collaborateur1 + 
                ", NomEvenement=" + NomEvenement + ", NomCollaborateur=" + NomCollaborateur + ", PrenomCollaborateur=" + PrenomCollaborateur +'}';    }
    

     */ 

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", NombreDeParticipants=" + NombreDeParticipants + ", Billet=" + Billet + ", collaborateur1=" + collaborateur1 + ", NomEvenement=" + NomEvenement + ", Description=" + Description + ", QrCode=" + QrCode + ", DateDeEvenement=" + DateDeEvenement + ", NomCollaborateur=" + NomCollaborateur + ", PrenomCollaborateur=" + PrenomCollaborateur + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }
    


    }

    

