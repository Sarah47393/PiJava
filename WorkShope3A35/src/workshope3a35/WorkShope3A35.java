/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshope3a35;

//import Model.Personne;
//import Services.ServicePersonne;
import utils.MyDb;
import Model.User;
import Services.ServiceUser;
import Model.Emplois;
import Services.ServiceEmplois;

/**
 *
 * @author Mohamed
 */
public class WorkShope3A35 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  //Personne p1 = new Personne(3,"ben salah" , "salah");
  //Personne p2 = new Personne(4,"ben salah" , "ali");
  User t=new User();
   Emplois ee=new Emplois();
  User t1=new User("hedi","zarrouk",1234,20508090,"Admin","Admin","bcbubrbr.jpg","1999-06-25");
   User t3=new User("hedi5","zarrouk",1234,20558090,"Admin","Admin","bcbubrbr.jpg","1999-06-25");
 Emplois e1=new Emplois(626,"1991-06-26 00:00:00","1999-06-25 00:01:01");
  //ServicePersonne sp =  new ServicePersonne();
  ServiceUser u = new ServiceUser();
    ServiceEmplois e = new ServiceEmplois();
  
  e1.setId(25);
    /*sp.ajouter(p1);
        sp.ajouter(p2);
        System.out.println(sp.afficher().toString());*/
    System.out.println(u.afficher().toString());
  // t3.setId(48);
      // System.out.println("tat"+"\nntut"+t3.getId());
    //  u.supprimer(t);
  //  u.ajouter(t3);
   // u.modifier(t1,1414, "hod", "hod");
  //System.out.println(u.afficher().toString());
  // u.supprimer(t3);
 //  System.out.println(u.afficherTrier("Nom","Desc").toString());
            //  System.out.println(u.recherche("2022-02-15").toString());
              //   System.out.println(u.filtre("Membre").toString());
//System.out.println(e.afficher().toString());
 //System.out.println(u.recherche("").toString());
 // System.out.println(e.afficherTrier("cin","Desc").toString());
 //System.out.println(e.recherche("2023").toString());
 e.modifier(e1);
 //System.out.println(e.afficher().toString());

// e.ajouter(e1);
 //System.out.println(e.afficher().toString());
// ee.setId(22);
 //e.supprimer(ee);
 //System.out.println(e.afficher().toString());
 //e.tristream();
 //e1.setCin(99);
 //e.rechstream(18);
//System.out.println( e.rechstream(e1).toString());
 
    }
   
}
