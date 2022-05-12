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
import reservationvisite.utils.Myconnexion;

/**
 *
 * @author Mortadha
 */
public class ServiceDons implements IService<Dons> {
    Connection cnx;
    public ServiceDons(){
        cnx=Myconnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(Dons t) {
        try {
            Statement st;
            String query="INSERT INTO `dons`(`user_id`, `num_carte`, `montant`) VALUES ('"+t.getUser_id()+"','"+t.getNum_carte()+"','"+t.getMontant()+"')";
            st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDons.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            Dons t=getDonsById(id);
            Statement st;
            String query2="INSERT INTO `dons_archive`(`user_id`, `num_carte`, `montant`) VALUES ('"+t.getUser_id()+"','"+t.getNum_carte()+"','"+t.getMontant()+"')";
  
            
            String query="DELETE FROM `dons` WHERE id="+id;
            st=cnx.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query2);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Dons t, int id_amodifier) {
        try {
            Statement st;
            String query="UPDATE `dons` SET `user_id`='"+t.getUser_id()+"',`num_carte`='"+t.getNum_carte()+"',`montant`='"+t.getMontant()+"' WHERE id="+id_amodifier;
            st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Dons> afficher() {
        List<Dons> ld=new ArrayList<>();
        try {
            String query="SELECT * FROM `dons` ";
            Statement st;
            st=cnx.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Dons d=new Dons();
                d.setId(rs.getInt("id"));
                d.setMontant(rs.getString("montant"));
                d.setNum_carte(rs.getString("num_carte"));
                d.setUser_id(rs.getInt("user_id"));
                
                ld.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVisite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ld;
    }
    public Dons getDonsById(int id){
        List<Dons> dons=afficher();
        return dons.stream().filter(d->d.getId()==id).findFirst().orElse(null);
    }
    
}
