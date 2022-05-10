/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import Model.User;
import Services.ServiceUser;
import GUI.FXMLUserController;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLUserfrontController implements Initializable {
public User user5=new User();
    @FXML
    private ImageView imguser;
    @FXML
    private Label nomuser;
    @FXML
    private Label prenomuser;
    @FXML
    private Label cinuser;
    @FXML
    private Label emailuser;
    @FXML
    private Label datenaissanceuser;
    @FXML
    private Label roleuser;
    @FXML
    private Label accessuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
     //  sp.rechstream(a);
      //  System.out.println( sp.rechstream(a));
       
      user5=user5.getMyVariable();
      nomuser.setText(user5.getNom());
      prenomuser.setText(user5.getPrenom());
      cinuser.setText(String.valueOf(user5.getCin()));
      emailuser.setText(user5.getEmail());
      roleuser.setText(user5.getRole());
        accessuser.setText(user5.getAccess());
          datenaissanceuser.setText(user5.getDatenaissance());
           String url1 = "http://127.0.0.1/projetpi/public/Uploads/image/";
   
            
      
  
     Image image = new Image(url1+user5.getImage(),300,350,false,false);
    

  imguser.setImage(image);
  imguser.setFitHeight(350);
  imguser.setFitWidth(300);
         
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
       private void back(ActionEvent event)throws IOException {
            
      Parent root = FXMLLoader.load(getClass().getResource("FXMLViewfront.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
}
