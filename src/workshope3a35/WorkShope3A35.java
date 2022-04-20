/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshope3a35;

import Model.Collaborateur;
import Model.Evenement;
import Services.ServiceCollaborateur;
import Services.ServiceEvenement;
import java.sql.SQLException;
import utils.MyDb;

/**
 *
 * @author Mohamed
 */
public class WorkShope3A35 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
  //Collaborateur c1 = new Collaborateur("ben salah5555" , "salah","artist",12345,"mlk");
  //Collaborateur c2 = new Collaborateur("ben salah55444444444455" , "salah","artist",12345,"mlk");
  Collaborateur c3 = new Collaborateur("ben dddddd" , "salahwc","artistss",12345,"mlxk");
  Evenement c1 = new Evenement (1,"sss", "xwcxwx", 15, "xcwxc",10,"2022-02-03",1,1);
   //Evenement c = new Evenement(8,1 , "art","arttt",14,"mlxk",10,2022-03-04,1.2,1.3);
  
  //Evenement c = new Evenement(1 , 1,"artistss","ken","kenP");
  ServiceCollaborateur sp =  new ServiceCollaborateur();
 ServiceEvenement cc =  new ServiceEvenement();
  //sp.setId(20);
 // cc.ajouter(c1);
    
    // sp.modifier(c1, 16);
     //  sp.supprimer();
        cc.afficher();
       // cc.eventjoin(8);
 // sp.rechercherCollaborateur(4);
       System.out.println(cc.afficher().toString());
     // System.out.println(cc.eventjoin(8));
    // sp.trierCollaborateur();
    }
}
   

