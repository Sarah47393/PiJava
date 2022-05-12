/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationvisite.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import reservationvisite.entity.Reservation;
import reservationvisite.entity.Visite;
import reservationvisite.utils.Myconnexion;

/**
 *
 * @author Mortadha
 */
public class ServiceVisite implements IService<Visite> {
    Connection cnx;
    public ServiceVisite(){
        cnx=Myconnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Visite t) {
        try {
            Statement st;
            String query="INSERT INTO `visite`"
                    + "( `date`, `description`,"
                    + " `num_carte`, `montant`,"
                    + " `sexe`) VALUES"
                    + " ('"+t.getDate()+"','"+t.getDescription()+"',"
                    + "'"+t.getNum_carte()+"','"+t.getMontant()+"',"
                    + "'"+t.getSexe()+"')";
            st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVisite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            Visite t=getVisiteById(id);
            Statement st;
            String query="DELETE FROM `visite` WHERE id="+id;
            String query2="INSERT INTO `visite_archive`"
                    + "( `date`, `description`,"
                    + " `num_carte`, `montant`,"
                    + " `sexe`) VALUES"
                    + " ('"+t.getDate()+"','"+t.getDescription()+"',"
                    + "'"+t.getNum_carte()+"','"+t.getMontant()+"',"
                    + "'"+t.getSexe()+"')";
            st=cnx.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query2);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVisite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Visite t, int id_amodifier) {
        try {
            Statement st;
            String query="UPDATE `visite` SET "
                    + "`date`='"+t.getDate()+"',"
                    + "`description`='"+t.getDescription()+"',"
                    + "`num_carte`='"+t.getNum_carte()+"',"
                    + "`montant`='"+t.getMontant()+"',"
                    + "`sexe`='"+t.getSexe()+"' WHERE id="+id_amodifier;
            st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVisite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Visite> afficher() {
        List<Visite> lv=new ArrayList<>();
        try {
            String query="SELECT * FROM `visite` ";
            Statement st;
            st=cnx.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Visite v=new Visite();
                v.setId(rs.getInt("id"));
                v.setDate(rs.getString("date"));
                v.setDescription(rs.getString("description"));
                v.setMontant(rs.getString("montant"));
                v.setNum_carte(rs.getString("num_carte"));
                v.setSexe(rs.getString("sexe"));
                lv.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVisite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lv;
    }
     public Visite getVisiteById(int id){
        List<Visite> visites=afficher();
        return visites.stream().filter(v->v.getId()==id).findFirst().orElse(null);
    }
    public List<Visite> getVisiteByUser(int id_user){
        List<Reservation> lr=new ArrayList<>();
        try {
            String query="SELECT * FROM `reservation` WHERE user_id="+id_user;
            Statement st;
            st=cnx.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reservation r=new Reservation();
                r.setId(rs.getInt("id"));
                r.setUser_id(rs.getInt("user_id"));
                r.setVisite_id(rs.getInt("visite_id"));
                lr.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVisite.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Visite> resultat=new ArrayList<>();
        for(Reservation r :lr){
            resultat.add(getVisiteById(r.getVisite_id()));
        }
        return resultat;
    }
     public List<Visite> sortByDescription(){
         List<Visite> visites=afficher();
         List<Visite> resultat=visites.stream().sorted(Comparator.comparing(Visite::getDescription)).collect(Collectors.toList());
         return resultat;
     }
}
