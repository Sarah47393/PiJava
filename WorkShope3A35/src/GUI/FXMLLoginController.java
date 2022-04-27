/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.User;
import Services.ServiceUser;
import Model.Emplois;
import Services.ServiceEmplois;
import GUI.FXMLUserController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import workshope3a35.WorkShope3A35;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfpassword;
    @FXML
    private Button btnlogin;
    @FXML
    private WebView capt;
private String s="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        WebEngine engine=capt.getEngine();
        engine.load("http://localhost/html.html");
         //engine.setOnAlert(event -> showAlert(event.getData()));
       // engine.setConfirmHandler(message -> showConfirm(message));
          engine.setOnAlert(event -> s=(event.getData()));
      //  engine.loadContent("");
      //  URL urlHello = getClass().getResource("hello.html");
        //WebEngine.load(urlHello.toString());
    }    
    private void showAlert(String message) {
        Dialog<Void> alert = new Dialog<>();
        alert.getDialogPane().setContentText(message);
        alert.getDialogPane().getButtonTypes().add(ButtonType.APPLY.OK);
        alert.showAndWait();
    }

    private boolean showConfirm(String message) {
        Dialog<ButtonType> confirm = new Dialog<>();
        confirm.getDialogPane().setContentText(message);
        confirm.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        boolean result = confirm.showAndWait().filter(ButtonType.YES::equals).isPresent();

        // for debugging:
        System.out.println(result);

        return result ;
    }

     @FXML
    private void login(ActionEvent event) throws IOException {
         ServiceUser sp = new ServiceUser();
      
        String log=tflogin.getText();
        String pass=tfpassword.getText();
         if(tflogin.getText().isEmpty()){
               Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Veuillez remplir tous les champs");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
         }
            else if(tfpassword.getText().isEmpty()){
               Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Veuillez remplir tous les champs");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
         }
            else{
         User usc = new User(tflogin.getText(),Integer.parseInt(tfpassword.getText()));
          System.out.println(usc.getEmail()); 
            
          if(!(s.equals("55"))){
               Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("captcha");
                         alert.setContentText("captcha");
                         alert.show();
         }
          else if(tflogin.getText().isEmpty()){
               Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("captcha");
                         alert.setContentText("captcha");
                         alert.show();
         }
        else if((s.equals("55"))&&(sp.existe(usc).contains("Admin"))){
           
             Parent root = FXMLLoader.load(getClass().getResource("FXMLUser.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
             
             
            
       }
          else if(((s.equals("55"))&&sp.existe(usc).contains("Membre"))){
             Parent root = FXMLLoader.load(getClass().getResource("FXMLEmplois.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
             
             }
         else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("connexion impossible");
                         alert.setContentText("Veuillez verifier votre login ou mot de passe");
                         alert.show();
         }
            } 
        /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/pidev/ui/users/AdminHome.fxml"));
        Parent root = loader.load();
        FXMLUserController HomeScene = loader.getController();
        HomeScene.user1 = a;
        HomeScene.id=id;
        HomeScene.iniializeFxml(a);
        HomeScene.showData(a);
        Stage window = (Stage) connectLog.getScene().getWindow();
        window.setScene(new Scene(root, 800, 800));*/
    }
}
