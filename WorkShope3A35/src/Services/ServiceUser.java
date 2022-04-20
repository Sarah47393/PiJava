/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Personne;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDb;
import Model.User;
import java.sql.PreparedStatement;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */

public class ServiceUser  implements IService<User>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
   @Override
    public void ajouter(User t) {
    try {
        String querry= "INSERT INTO User(`nom`, `prenom`,`password`,`cin`,`role`,`access`,`image`,`datenaissance`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getPassword()+"','"+t.getCin()+"','"+t.getRole()+"','"+t.getAccess()+"','"+t.getImage()+"','"+t.getDatenaissance()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<User> afficher() {
     List<User> Users = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `User`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            User p = new User();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString(3));
            p.setPassword(rs.getInt("password"));
            p.setCin(rs.getInt("cin"));
            p.setRole(rs.getString("role"));
            p.setAccess(rs.getString("access"));
            p.setImage(rs.getString("image"));
            p.setDatenaissance(rs.getString("datenaissance"));
            Users.add(p);
        }
        
        
        
        return Users;
    } catch (SQLException ex) {
        }
    return Users;
    }

    @Override
    public void modifier(User t) {
         try {
            String req = "update User set nom= ?, prenom =?, password =? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setInt(3, t.getPassword());
            ps.setInt(4, t.getId());
            ps.executeUpdate();
            System.out.println("User modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }
 @Override
    public List<User> afficherTrier(String s,String a) {
     List<User> Users = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `User` order by "+s+" "+a;
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            User p = new User();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString(3));
            p.setPassword(rs.getInt("password"));
            p.setCin(rs.getInt("cin"));
            p.setRole(rs.getString("role"));
            p.setAccess(rs.getString("access"));
            p.setImage(rs.getString("image"));
            p.setDatenaissance(rs.getString("datenaissance"));
            Users.add(p);
        }
        
        
        
        return Users;
    } catch (SQLException ex) {
        }
    return Users;
    }
    @Override
    public void supprimer(User t) {
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
 /*try {
            String req = "delete From User  where id = ?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setInt(3, t.getPassword());
            ps.setInt(4, t.getId());
            ps.execute();
            System.out.println("User deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         }*/
 String req = "delete From User  where id = ?" ;
     try(PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(req)) {
            preparedStatement.setInt(1, t.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se.getMessage());;
        }}
     @Override
    public List<User> recherche(String s) {
     List<User> Users = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `User` where nom like '%"+s+"%' or prenom  like '%"+s+"%' or cin   like '%"+s+"%' or datenaissance   like '%"+s+"%'";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            User p = new User();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString(3));
            p.setPassword(rs.getInt("password"));
            p.setCin(rs.getInt("cin"));
            p.setRole(rs.getString("role"));
            p.setAccess(rs.getString("access"));
            p.setImage(rs.getString("image"));
            p.setDatenaissance(rs.getString("datenaissance"));
            Users.add(p);
        }
        
        
        
        return Users;
    } catch (SQLException ex) {
        }
    return Users;
    }
    @Override
    public List<User> filtre(String s) {
     List<User> Users = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `User` where role like '%"+s+"%'";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            User p = new User();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString(3));
            p.setPassword(rs.getInt("password"));
            p.setCin(rs.getInt("cin"));
            p.setRole(rs.getString("role"));
            p.setAccess(rs.getString("access"));
            p.setImage(rs.getString("image"));
            p.setDatenaissance(rs.getString("datenaissance"));
            Users.add(p);
        }
        
        
        
        return Users;
    } catch (SQLException ex) {
        }
    return Users;
    }
  @Override
    public List<User> rechstream(User x) {

     
     return afficher().stream().filter(p->(String.valueOf(p.getCin()).contains(String.valueOf(x.getCin())))||(p.getNom().contains(x.getNom()))||(p.getDatenaissance().contains(x.getDatenaissance()))||(p.getPrenom().contains(x.getPrenom()))||(p.getRole().contains(x.getRole()))||(p.getAccess().contains(x.getAccess()))).collect(Collectors.toList());
  
//return afficher().stream().filter(p->(String.valueOf(p.getCin()).contains(String.valueOf(x.getCin())))).collect(Collectors.toList());
  
       
    }
    
   public List<User> tristreamnom() {
   
  return afficher().stream().sorted((p1,p2)->p1.getNom().compareTo(p2.getNom())).collect(Collectors.toList());

    }
    public List<User> tristreamprenom() {
   
  return afficher().stream().sorted((p1,p2)->p1.getPrenom().compareTo(p2.getPrenom())).collect(Collectors.toList());

    }
     public List<User> tristreamdate() {
   
  return afficher().stream().sorted((p1,p2)->p1.getDatenaissance().compareTo(p2.getDatenaissance())).collect(Collectors.toList());

    }
  public List<User> affichercombo() {
     List<User> Users = new ArrayList();
        try {
       
        String querry ="SELECT id,nom,prenom,cin FROM `User`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            User p = new User();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setCin(rs.getInt("cin"));
        
            Users.add(p);
        }   return Users;
    } catch (SQLException ex) {
        }
    return Users;}
         public List<String> affichercombo1() {
     List<String> Users = new ArrayList();
        try {
       
        String querry ="SELECT id,nom,prenom,cin FROM `User`";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
           // User p = new User();
            
          /*  p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setCin(rs.getInt("cin"));*/
        
            Users.add(rs.getString("nom"));
        }   return Users;
    } catch (SQLException ex) {
        }
    return Users;}
}

