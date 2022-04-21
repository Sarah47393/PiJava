/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDb;


/**
 *
 * @author Sarah
 */
public class ServiceEvenement implements IService<Evenement>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Evenement t) {
    try {
        String querry= "INSERT INTO `evenement`( `collaborateur1_id`, `nom_evenement`, `description`, `nombre_de_participants`, `qr_code`, `billet`, `date_de_evenement`, `longitude`, `latitude`) VALUES ('"+t.getCollaborateur1()+"','"+t.getNomEvenement()+"','"+t.getDescription()+"','"+t.getNombreDeParticipants()+"','"+t.getQrCode()+"','"+t.getBillet()+"','"+t.getDateDeEvenement()+"','"+t.getLongitude()+"','"+t.getLatitude()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Evenement> afficher() {
     List<Evenement> evenements = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `evenement` where archiver is null";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            
        while (rs.next()){
            Evenement c = new Evenement();
         
            c.setId(rs.getInt(1));
            c.setNomEvenement(rs.getString("nom_evenement"));
            c.setDescription(rs.getString("description"));
            c.setNombreDeParticipants(rs.getInt("nombre_de_Participants"));  
            c.setQrCode(rs.getString("qr_code"));
            c.setDateDeEvenement(rs.getString("date_de_evenement"));
            c.setCollaborateur1(rs.getInt("collaborateur1_id"));
            c.setBillet(rs.getInt("billet"));
            c.setLongitude(rs.getFloat("longitude"));
            c.setLatitude(rs.getFloat("latitude"));
            evenements.add(c);
        }
                
        return evenements;
    } catch (SQLException ex) {
        }
    return evenements;
    }

    @Override
   public void modifier(Evenement t) {
        try {
            String requete =  "UPDATE `evenement` SET `nom_evenement`=?,`description`=?,`nombre_de_participants`=?,`qr_code`=?,`billet`=?,`date_de_evenement`=?,`longitude`=?,`latitude`=? WHERE id =?";
           
            PreparedStatement pst= MyDb.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getNomEvenement());
            pst.setString(2, t.getDescription());
            pst.setInt(3, t.getNombreDeParticipants());
            pst.setString(4, t.getQrCode());
            pst.setString(6, t.getDateDeEvenement());
  
            pst.setInt(5, t.getBillet());
            pst.setFloat(7, t.getLongitude());
            pst.setFloat(8, t.getLatitude());
            pst.setInt(9, t.getId());
            pst.executeUpdate();
            System.out.println("un évènement a été MODIFIE!");                        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());            
        }
       }

    @Override
    public void supprimer(int z) {
        try
       {
           //DELETE FROM  WHERE 0
           String requete="DELETE FROM evenement WHERE id ="+z ;
          PreparedStatement pste=MyDb.getInstance().getCnx().prepareStatement(requete);
           if (pste.execute())
          { System.out.println("un evenement a été supprimé");}
           
       }catch(SQLException ex)
       {
          System.out.println(ex.getMessage());
       }
         }

  public List<Evenement> eventjoin(int id) throws SQLException {

        List<Evenement> lu = new ArrayList<>();
        Statement stm = cnx.createStatement();
        
        PreparedStatement pre  = cnx.prepareStatement("SELECT e.id, e.collaborateur1_id, e.nom_evenement, c.nom_collaborateur, c.prenom_collaborateur FROM evenement e , collaborateur c WHERE e.collaborateur1_id=c.id AND e.id = ?");
        
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int ide=rs.getInt("e.id");
            
            int Collaborateur1 = rs.getInt("collaborateur1_id");
            String nom_evenement = rs.getString("nom_evenement");
            String nom_collaborateur = rs.getString("nom_collaborateur");
            String prenom_collaborateur = rs.getString("prenom_collaborateur");
            
            
            Evenement p = new Evenement(ide ,Collaborateur1,nom_evenement,nom_collaborateur,prenom_collaborateur);
            lu.add(p);
        }
        return lu;
    }  
/*public List<Reclamation> tri(boolean isAsc) throws SQLException {
        List<Reclamation> list = new ArrayList<>();
        stm = connexion.createStatement();  

         String ordre = isAsc ? "" : 
                 "desc";
       
        ResultSet rs = stm.executeQuery("select * from reclamation order by date" + ordre );
       while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            String message = rs.getString("message");
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
            boolean etat  = rs.getBoolean("etat");
            Date date = rs.getDate("date");
       
            Reclamation rec = new Reclamation(id,titre,message,idUser,idCategoryReclamation,etat,date);
            list.add(rec);
        }
        return list;
        
  }
    
    */

    @Override
    public void rechercherCollaborateur(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void trierCollaborateur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void archiver(Evenement t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    }


  /*  @Override
    public void modifier(Collaborateur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

  /*  @Override
    public void modifier(Collaborateur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

  /*  @Override
    public List<Evenement> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Evenement t, int z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
