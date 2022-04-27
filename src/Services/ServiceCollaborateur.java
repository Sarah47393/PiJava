/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Collaborateur;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
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
public class ServiceCollaborateur implements IService<Collaborateur>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
    @Override
    public void ajouter(Collaborateur t) {
    try {
        String querry= "INSERT INTO `collaborateur`( `nom_collaborateur`, `prenom_collaborateur`, `role`, `numero_tel`, `entreprise`) VALUES ('"+t.getNomCollaborateur()+"','"+t.getPrenomCollaborateur()+"','"+t.getRole()+"','"+t.getNumeroTel()+"','"+t.getEntreprise()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }
@Override
    public void archiver(Collaborateur t) {
         try {
            String req = "update collaborateur set archiver= ? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,"1");
            ps.setInt(2, t.getId());
            ps.executeUpdate();
            System.out.println("Collaborateur archivé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }

    @Override
    public List<Collaborateur> afficher() {
     List<Collaborateur> collaborateurs = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `collaborateur` where archiver is NULL";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
            
        while (rs.next()){
            Collaborateur c = new Collaborateur();
         
            c.setId(rs.getInt(1));
            c.setNomCollaborateur(rs.getString("nom_collaborateur"));
            c.setPrenomCollaborateur(rs.getString("prenom_collaborateur"));
            c.setRole(rs.getString("role"));
            c.setNumeroTel(rs.getInt("numero_tel"));
            c.setEntreprise(rs.getString("entreprise"));
            collaborateurs.add(c);
        }
                
        return collaborateurs;
    } catch (SQLException ex) {
        }
    return collaborateurs;
    }

    @Override
   public void modifier(Collaborateur t) {
        try {
            String requete =  "UPDATE `collaborateur`  SET `nom_collaborateur`=?,`prenom_collaborateur`=?,`role`=?,`numero_tel`=?,`entreprise`=? WHERE id =?";
           
            PreparedStatement pst= MyDb.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getNomCollaborateur());
            pst.setString(2, t.getPrenomCollaborateur());
            pst.setString(3, t.getRole());
            pst.setInt(4, t.getNumeroTel());
            pst.setString(5, t.getEntreprise());
             pst.setInt(6, t.getId());
            pst.executeUpdate();
            System.out.println("un collaborateur a ete MODIFIE!");                        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());            
        }
       }

    @Override
    public void supprimer(int z) {
        try
       {
           //DELETE FROM  WHERE 0
           String requete="DELETE FROM collaborateur WHERE id ="+z ;
          PreparedStatement pste=MyDb.getInstance().getCnx().prepareStatement(requete);
           if (pste.execute())
          { System.out.println("un collaborateur a été supprimé");}
           
       }catch(SQLException ex)
       {
          System.out.println(ex.getMessage());
       }
        
         }
    

    
    
    
    
   public  void  sms () {
 
   
      //  Twilio.init("ACc081a8d42c1f270e76774227bd218f66","eeff0956f708da011f751d102d0cae2e");
        String a = "votre prochaine evenemenet est le " ;
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21646354484"),
                new com.twilio.type.PhoneNumber("+19593011606"),
                "votre modification a été éffectuée avec succès " )
            .create();

        System.out.println(message.getSid());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  /*  @Override
    public void rechercherCollaborateur(int x) {
        List<Collaborateur> collaborateurs = new ArrayList();
      boolean test ;
         try {
       
        String query ="SELECT * FROM collaborateur";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            Collaborateur c = new Collaborateur();
            
             c.setId(rs.getInt(1));
            c.setNomCollaborateur(rs.getString("nom_collaborateur"));
            c.setPrenomCollaborateur(rs.getString("prenom_collaborateur"));
            c.setRole(rs.getString("role"));
            c.setNumeroTel(rs.getInt("numero_tel"));
            c.setEntreprise(rs.getString("entreprise"));
            collaborateurs.add(c);
        }
       
   test =collaborateurs.stream().anyMatch((p -> p.getId()==x ));
   if (test ==true ){
     for (int i = 0; i < collaborateurs.size(); i++) {
           if (collaborateurs.get(i).getId()== x) {
                 System.out.println( collaborateurs.get(i));
           
           } }
   
   }
   else
   {
       System.out.println( "aucun collaborateur");
   }
        } catch (SQLException ex){} ;
       
    }
    
    
@Override
    public void trierCollaborateur() {
        List<Collaborateur> collaborateurs = new ArrayList();
         try {
       
        String query ="SELECT * FROM collaborateur";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            Collaborateur c = new Collaborateur();
            
            c.setId(rs.getInt(1));
            c.setNomCollaborateur(rs.getString("nom_collaborateur"));
            c.setPrenomCollaborateur(rs.getString("prenom_collaborateur"));
            c.setRole(rs.getString("role"));
            c.setNumeroTel(rs.getInt("numero_tel"));
            c.setEntreprise(rs.getString("entreprise"));
            collaborateurs.add(c);
        }
       
   collaborateurs.stream().sorted((a,b) -> a.getNomCollaborateur().compareTo(b.getNomCollaborateur())).forEach(System.out::println);;
    } catch (SQLException ex) {} 
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

    @Override
    public List<Collaborateur> tristreamdescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Collaborateur> rechstream(Collaborateur x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   
}