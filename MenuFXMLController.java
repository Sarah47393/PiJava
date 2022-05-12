/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class MenuFXMLController implements Initializable {

    @FXML
    private Button productsNavigationButton;
    @FXML
    private Button orderNavigationButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        productsNavigationButton.setOnAction((t) -> {
           // navigate("Products");
            try {
                    Parent parent = FXMLLoader.load(getClass().getResource("ProductsFXML.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Visual Arts");
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        orderNavigationButton.setOnAction((t) -> {
          //  navigate("Orders");
             try {
                    Parent parent = FXMLLoader.load(getClass().getResource("OrdersFXML.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Visual Arts");
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
    }    
    public void navigate(String panel){
        try {
                    Parent parent = FXMLLoader.load(getClass().getResource(panel+"FXML.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Visual Arts");
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
