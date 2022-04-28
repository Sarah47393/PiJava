/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDb;

/**
 *
 * @author HP
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.Personne;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDb;
import Model.User;
import Model.Emplois;
import java.sql.PreparedStatement;
import java.util.stream.Collectors;

/**
 *
 * @author HP
 */

public class ServiceEmplois  implements IService<Emplois>{
private Connection cnx = MyDb.getInstance().getCnx() ;
   
   @Override
    public void ajouter(Emplois t) {

          User p = new User();
    try {
       // System.out.println(t.getCin());
         String querry1 ="SELECT * FROM `User` where cin = "+t.getCin();
        Statement stm1 = cnx.createStatement();
            ResultSet rs= stm1.executeQuery(querry1);
         while(rs.next()){
          
                 p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
                      p.setCin(rs.getInt("cin"));
                  //   System.out.println(p.getId());
         }
        
        // System.out.println(p.getNom());
        
        
        
        
        String querry= "INSERT INTO Emplois(`nom`, `prenom`,`cin`,`ddebut`,`dfin`,`user_id`) VALUES ('"+p.getNom()+"','"+p.getPrenom()+"','"+t.getCin()+"','"+t.getDdebut()+"','"+t.getDfin()+"','"+p.getId()+"')";
        Statement stm = cnx.createStatement();
    
    stm.executeUpdate(querry);
    
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    }
        
        
    }

    @Override
    public List<Emplois> afficher() {
     List<Emplois> Emploiss = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `Emplois` where arch =0 ";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Emplois p = new Emplois();
             //    p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setDdebut(rs.getString("ddebut"));
            p.setDfin(rs.getString("dfin"));
                      p.setCin(rs.getInt("cin"));
                       Emploiss.add(p);
        }
        
        
        
        return Emploiss;
    } catch (SQLException ex) {
        }
    return Emploiss;
    }

    @Override
    public void modifier(Emplois t) {
         try {
            String req = "update Emplois set ddebut= ?, dfin =? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getDdebut());
            ps.setString(2, t.getDfin());
            ps.setInt(3, t.getId());
            ps.executeUpdate();
            System.out.println("Emplois modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }
     @Override
    public void arch(Emplois t) {
         try {
            String req = "update Emplois set arch= ? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,"1");
           
            ps.setInt(2, t.getId());
            ps.executeUpdate();
            System.out.println("Emplois modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }
 @Override
    public List<Emplois> afficherTrier(String s,String a) {
     List<Emplois> Emploiss = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `Emplois` order by "+s+" "+a;
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Emplois p = new Emplois();
            
             p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setDdebut(rs.getString("ddebut"));
            p.setDfin(rs.getString("dfin"));
                      p.setCin(rs.getInt("cin"));
            Emploiss.add(p);
        }
        
        
        
        return Emploiss;
    } catch (SQLException ex) {
        }
    return Emploiss;
    }
    @Override
    public void supprimer(Emplois t) {
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
 String req = "delete From Emplois  where id = ?" ;
     try(PreparedStatement preparedStatement = (PreparedStatement) cnx.prepareStatement(req)) {
            preparedStatement.setInt(1, t.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println(se.getMessage());;
        }}
     @Override
    public List<Emplois> recherche(String s) {
     List<Emplois> Emploiss = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `Emplois` where nom like '%"+s+"%' or prenom  like '%"+s+"%' or cin   like '%"+s+"%'  or dfin   like '%"+s+"%' or ddebut   like '%"+s+"%'";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Emplois p = new Emplois();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setDdebut(rs.getString("ddebut"));
            p.setDfin(rs.getString("dfin"));
                      p.setCin(rs.getInt("cin"));
            Emploiss.add(p);
        }
        
        
        
        return Emploiss;
    } catch (SQLException ex) {
        }
    return Emploiss;
    }
    @Override
    public List<Emplois> filtre(String s) {
     List<Emplois> Emploiss = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `User` where role like '%"+s+"%'";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Emplois p = new Emplois();
            
          
            Emploiss.add(p);
        }
        
        
        
        return Emploiss;
    } catch (SQLException ex) {
        }
    return Emploiss;
    }
    @Override
   
    public List<Emplois> rechstream(Emplois x) {
       
       
      return afficher().stream().filter(p->(String.valueOf(p.getCin()).contains(String.valueOf(x.getCin())))||(p.getNom().contains(x.getNom()))||(p.getDfin().contains(x.getDfin()))||(p.getPrenom().contains(x.getPrenom()))||(p.getDdebut().contains(x.getDdebut()))).collect(Collectors.toList());

  
 
    }
  public List<Emplois> tristreamnom() {
   
  return afficher().stream().sorted((p1,p2)->p1.getNom().compareTo(p2.getNom())).collect(Collectors.toList());

    }
    public List<Emplois> tristreamprenom() {
   
  return afficher().stream().sorted((p1,p2)->p1.getPrenom().compareTo(p2.getPrenom())).collect(Collectors.toList());

    }
     public List<Emplois> tristreamdated() {
   
  return afficher().stream().sorted((p1,p2)->p1.getDdebut().compareTo(p2.getDdebut())).collect(Collectors.toList());

    }
      public List<Emplois> tristreamdatef() {
   
  return afficher().stream().sorted((p1,p2)->p1.getDfin().compareTo(p2.getDfin())).collect(Collectors.toList());

    }
 public List<String> afficher2() {
     List<Emplois> Emploiss = new ArrayList();
     String s="";
     List<String> ss = new ArrayList();
        try {
       
        String querry ="SELECT * FROM `Emplois` where arch =0 ";
        Statement stm = cnx.createStatement();
            ResultSet rs= stm.executeQuery(querry);
        while (rs.next()){
            Emplois p = new Emplois();
             //    p.setId(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setDdebut(rs.getString("ddebut"));
            p.setDfin(rs.getString("dfin"));
                      p.setCin(rs.getInt("cin"));
                       Emploiss.add(p);
                        s="nom: "+p.getNom()+" prenom: "+p.getPrenom()+" cin: "+p.getCin()+" datedebut: "+p.getDdebut()+" datefin: "+p.getDfin();
                        ss.add(s);
        }
        
        
        
        return ss;
    } catch (SQLException ex) {
        }
    return ss;
    }
}
