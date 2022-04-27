/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mohamed
 */
public class NewFXMain extends Application {
      public User user1 = new User();
    @Override
    public void start(Stage primaryStage) {
      
       
   
        try {
            
           // Parent root =FXMLLoader.load(getClass().getResource("FXMLUser.fxml"));
           Parent root =FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
                      //  Parent root =FXMLLoader.load(getClass().getResource("FXMLEmplois.fxml"));

            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("-----Gestion User ------");
            primaryStage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
