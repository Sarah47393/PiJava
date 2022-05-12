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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import reservationvisite.entity.Dons;
import reservationvisite.entity.Reservation;
import reservationvisite.entity.Visite;
import reservationvisite.service.MyListener;
import reservationvisite.service.ServiceDons;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLdonsController implements Initializable {

    private ListView<Dons> listeView;
    @FXML
    private TextField tfnumcarte;
    @FXML
    private TextField tfmontant;
    @FXML
    private TextField tfrecherche;
    ObservableList<Dons> data=FXCollections.observableArrayList();
    ServiceDons sd=new ServiceDons();
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
        recherche_avance(sd.afficher());
    }    
     public String controleDeSaisie(){
        
        String erreur="";
        
        if(tfmontant.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Montant\n";
        }
        
        if(tfnumcarte.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le num carte Nom\n";
        }
        
        
        return erreur;
        
    }
    private void filldata() {
        
            Dons t=sd.getDonsById(Integer.parseInt(idgetter.getText()));
            
            tfmontant.setText(t.getMontant());
            tfnumcarte.setText(t.getNum_carte());
            
        
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            Dons d=new Dons(1, tfnumcarte.getText(), tfmontant.getText());
            sd.ajouter(d);
            recherche_avance(sd.afficher());
        }
    }
    

    @FXML
    private void modifier(ActionEvent event) {
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            
                Dons d=new Dons(1, tfnumcarte.getText(), tfmontant.getText());
                sd.modifier(d, Integer.parseInt(idgetter.getText()));
                recherche_avance(sd.afficher());
            
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
       
            sd.supprimer(Integer.parseInt(idgetter.getText()));
            recherche_avance(sd.afficher());
        
    }

    @FXML
    private void logout(ActionEvent event) {
        System.exit(0);
    }
    
    public void recherche_avance(List<Dons> dons){
        refresh(dons);
        ObservableList<Dons> data=FXCollections.observableArrayList(dons);
        
        FilteredList<Dons> filterddata=new FilteredList<>(data,b->true);
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
    public void selectedDons(Dons d){
        idgetter.setText(String.valueOf(d.getId()));
        filldata();
         System.out.println(idgetter.getText());
        
    }
    public void refresh(List<Dons> dons){
grid.getChildren().clear();
         if(dons.size() > 0){
          selectedDons(dons.get(0));
          myListener = new MyListener() {
              

              @Override
              public void onClickListener(Dons d) {
                  selectedDons(d);
              }

              @Override
              public void onClickListener2(Visite v) {
                  
              }

              @Override
              public void onClickListener3(Reservation r) {
              }
              
         };
                  }
        int column = 0;
        int row = 1;
        
        try {
            for (int i = 0; i < dons.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/reservationvisite/gui/FXMLafficherdons.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FXMLafficherdonsController itemController = fxmlLoader.getController();
                itemController.setData(dons.get(i),myListener);

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
