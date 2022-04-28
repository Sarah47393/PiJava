/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.User;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLMdpoubController implements Initializable {

    @FXML
    private Button ajout;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfcin;
    @FXML
    private DatePicker tfdate;
    @FXML
    private Button nomtri;
    @FXML
    private TextField mdp1;
    @FXML
    private TextField mdp2;
    @FXML
    private Label champ1;
    @FXML
    private Label chmp2;
    @FXML
    private Button modifier1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterPersonne(ActionEvent event) {
ServiceUser sp = new ServiceUser();
User a=new User();
String email=a.getMyVariable().getEmail();
String nom=tfnom.getText();
String prenom=tfprenom.getText();
int cin = Integer.parseInt(tfcin.getText());
String date=tfdate.getValue().toString();
User usc=new User(email,nom,prenom,date,cin);
usc.setMyVariable(usc);
if(sp.existemdp(usc)){
    mdp1.setVisible(true);
mdp2.setVisible(true);
 champ1.setVisible(true);
chmp2.setVisible(true);
 modifier1.setVisible(true);
}
else{ Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("verifier vos information");
                         alert.setContentText("verifier vos information");
                         alert.show();
}
    }

    @FXML
    private void deco(ActionEvent event)throws IOException {
         User aa=new User(); 
       aa.setMyVariable(aa);
      Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    @FXML
    private void mod(ActionEvent event)throws IOException {
      
     if(mdp1.getText().isEmpty()){
               Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Veuillez remplir tous les champs");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
         }
     else if(mdp2.getText().isEmpty()){
               Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Veuillez remplir tous les champs");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
         }
         else{
          //int cin = Integer.parseInt(champ1.getText());
       //int cin1 = Integer.parseInt(chmp2.getText());
         if(!mdp1.getText().equals(mdp2.getText())){
          Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("les 2 mdp entrer ne sont pas identique");
                         alert.setContentText("les 2 mdp entrer ne sont pas identique");
                         alert.show();
         }
         else{
          ServiceUser sp = new ServiceUser();
             int cin = Integer.parseInt(mdp1.getText());
       
          User usc=new User();
          usc.getMyVariable().setPassword(cin);
          usc=usc.getMyVariable();
          sp.modpass(usc);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("votre mdp a ete modifier reconnnecter vous");
                         alert.setContentText("votre mdp a ete modifier reconnnecter vous");
                         alert.show();
                           User aa=new User(); 
       aa.setMyVariable(aa);
      Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
         }
         
         
         }
    }
    
}
