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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import reservationvisite.entity.Dons;
import reservationvisite.entity.Reservation;
import reservationvisite.entity.Visite;
import reservationvisite.utils.Myconnexion;

/**
 *
 * @author Mortadha
 */
public class ServiceReservation implements IService<Reservation> {
    Connection cnx;
    public ServiceReservation(){
        cnx=Myconnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(Reservation t) {
        try {
            Statement st;
            String query="INSERT INTO `reservation`(`user_id`, `visite_id`) VALUES ('"+t.getUser_id()+"','"+t.getVisite_id()+"')";
            st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            Reservation t=getReservationById(id);
            Statement st;
            String query="DELETE FROM `reservation` WHERE id="+id;
            String query2="INSERT INTO `reservation_archive`(`user_id`, `visite_id`) VALUES ('"+t.getUser_id()+"','"+t.getVisite_id()+"')";
            
            st=cnx.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query2);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Reservation t, int id_amodifier) {
        try {
            Statement st;
            String query="UPDATE `reservation` SET `user_id`='"+t.getUser_id()+"',`visite_id`='"+t.getVisite_id()+"' WHERE id="+id_amodifier;
            st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reservation> afficher() {
        List<Reservation> lr=new ArrayList<>();
        try {
            String query="SELECT * FROM `reservation` ";
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
        return lr;
    }
    public Reservation getByUserAndVisite(int id_user,int id_visite) {
        Reservation r=new Reservation();
        try {
            String query="SELECT * FROM `reservation` WHERE user_id="+id_user+" AND visite_id="+id_visite;
            Statement st;
            st=cnx.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                
                r.setId(rs.getInt("id"));
                r.setUser_id(rs.getInt("user_id"));
                r.setVisite_id(rs.getInt("visite_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVisite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    public Reservation getReservationById(int id){
        List<Reservation> reservations=afficher();
        return reservations.stream().filter(r->r.getId()==id).findFirst().orElse(null);
    }
    
    
}
