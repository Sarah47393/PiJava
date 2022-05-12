/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationvisite.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import reservationvisite.entity.Dons;
import reservationvisite.entity.Reservation;
import reservationvisite.entity.Visite;
import reservationvisite.service.MyListener;
import reservationvisite.service.ServiceReservation;
import reservationvisite.service.ServiceVisite;
import reservationvisite.utils.Mailapi;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLreservationController implements Initializable {

    @FXML
    private TextField tfrecherche;
    ObservableList<Visite> data=FXCollections.observableArrayList();
    ServiceVisite sv=new ServiceVisite();
    ServiceReservation sr=new ServiceReservation();
    @FXML
    private GridPane grid;
    @FXML
    private Label idgetter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        recherche_avance(sv.afficher());
    }    

    @FXML
    private void Reserver(ActionEvent event) {
        
            Reservation r=new Reservation(1, Integer.parseInt(idgetter.getText()));
            sr.ajouter(r);
            Mailapi.send("testapimail63@gmail.com", "TESTapimail2022", "ikram.bargaoui@esprit.tn", "Reservation", "Vous avez bien reserver une visite");

        
        
        
    }

    @FXML
    private void logout(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void gotoreservation(ActionEvent event) {
        try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/reservationvisite/GUI/FXMLviewreservation.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("signup");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLreservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void recherche_avance(List<Visite> visites){
        refresh(visites);
        ObservableList<Visite> visitess=FXCollections.observableArrayList(visites);
        
        FilteredList<Visite> filterddata=new FilteredList<>(visitess,b->true);
        tfrecherche.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    filterddata.setPredicate(
                        u->{
                            if(u.getMontant().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getNum_carte().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getDescription().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getSexe().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getDate().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            
                            else{
                                return false;
                            }
                            
                        }
                            
                    );
                    refresh(filterddata);
                }
                
        );
        
    }

   
    MyListener myListener;
    public void selectedVisite(Visite v){
        idgetter.setText(String.valueOf(v.getId()));
  
         System.out.println(idgetter.getText());
        
    }
    public void refresh(List<Visite> visites){
grid.getChildren().clear();
         if(visites.size() > 0){
          selectedVisite(visites.get(0));
          myListener = new MyListener() {
              

              @Override
              public void onClickListener(Dons d) {
              }

              @Override
              public void onClickListener2(Visite v) {
                  selectedVisite(v);
              }

              @Override
              public void onClickListener3(Reservation r) {
              }
              
         };
                  }
        int column = 0;
        int row = 1;
        
        try {
            for (int i = 0; i < visites.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/reservationvisite/gui/FXMLaffichervisite.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FXMLaffichervisiteController itemController = fxmlLoader.getController();
                itemController.setData(visites.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
