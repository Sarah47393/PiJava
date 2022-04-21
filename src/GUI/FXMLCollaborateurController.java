/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Collaborateur;
import Services.ServiceCollaborateur;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author Sarah
 */
public class FXMLCollaborateurController implements Initializable {

    @FXML
    private TextField NomC;
     @FXML
    private TextField idin;
    @FXML
    private TextField Ent;
    @FXML
    private TextField PrenomC;
    @FXML
    private TextField r;
    @FXML
    private TextField Num;
     public int getNum() {
        return Integer.parseInt(Num.getText());
    }
     @FXML
    private Button btnm;
    @FXML
    private Button btna;
   @FXML
   private Button btnc;
    @FXML
    private Button ReB;
    @FXML
    private TextField Re;
    @FXML
    private TableView<Collaborateur> tableColl;
    @FXML
    private TableColumn<Collaborateur, String> Nomc;
    @FXML
    private TableColumn<Collaborateur, String> Prenomc;
    @FXML
    private TableColumn<Collaborateur, String> rol;
    @FXML
    private TableColumn<Collaborateur, String> numt;
    @FXML
    private TableColumn<Collaborateur, String> entre;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceCollaborateur sp = new ServiceCollaborateur();
       
  
    Nomc.setCellValueFactory(new PropertyValueFactory<Collaborateur, String>("NomCollaborateur"));
       Nomc.setCellFactory(TextFieldTableCell.<Collaborateur> forTableColumn());
   
        Prenomc.setCellValueFactory(new PropertyValueFactory<Collaborateur, String>("PrenomCollaborateur"));
        Prenomc.setCellFactory(TextFieldTableCell.<Collaborateur> forTableColumn());


        rol.setCellValueFactory(new PropertyValueFactory<Collaborateur, String>("role"));
        rol.setCellFactory(TextFieldTableCell.<Collaborateur> forTableColumn());
        numt.setCellValueFactory(new PropertyValueFactory<Collaborateur, String>("NumeroTel"));
       // numt.setCellFactory(TextFieldTableCell.<Collaborateur> forTableColumn());

        entre.setCellValueFactory(new PropertyValueFactory<Collaborateur, String>("entreprise"));
        entre.setCellFactory(TextFieldTableCell.<Collaborateur> forTableColumn());
 
        tableColl.setEditable(true);
        ObservableList<Collaborateur> list = FXCollections.observableArrayList(sp.afficher());
//ImageView imagecol=new ImageView(new image(this.getClass().getResourceAsStream(url1)));
    tableColl.setItems(list);
    Nomc.setOnEditCommit((CellEditEvent<Collaborateur, String> event) -> {
            TablePosition<Collaborateur, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Collaborateur c = event.getTableView().getItems().get(row);
            c.setNomCollaborateur(newFullName);
            sp.modifier(c);
        });
        
        
        
        
        
    }    

    @FXML
    private void afficher(ActionEvent event) {
        ServiceCollaborateur sp = new ServiceCollaborateur();
    
    
    }
    @FXML
 
    private void ajouterC(ActionEvent event) {
        
       ServiceCollaborateur sp= new ServiceCollaborateur();
if ((NomC.getText().isEmpty()) ||(PrenomC.getText().isEmpty()) || (r.getText().isEmpty())|| (Num.getText().isEmpty()) ||(Ent.getText().isEmpty()))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Champ(s) vide(s)");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    }
sp.ajouter(new Collaborateur(NomC.getText(),PrenomC.getText(),r.getText(),Integer.parseInt(Num.getText()),Ent.getText()));
ObservableList<Collaborateur> list = FXCollections.observableArrayList(sp.afficher());
tableColl.setItems(list);


}   
@FXML
    private void supprimerC(ActionEvent event) {
         ServiceCollaborateur sp= new ServiceCollaborateur();
         Collaborateur act  =tableColl.getSelectionModel().getSelectedItem();
         sp.supprimer(act.getId());
       int selectedID = tableColl.getSelectionModel().getSelectedIndex();
        tableColl.getItems().remove(selectedID);
       
    }
    @FXML
    private void afficherCC(ActionEvent event) {
   
     ServiceCollaborateur sp = new ServiceCollaborateur();
     Collaborateur c = tableColl.getSelectionModel().getSelectedItem();
  String idd=String.valueOf(c.getId());
  idin.setText(idd);
    NomC.setText(c.getNomCollaborateur());
     PrenomC.setText(c.getPrenomCollaborateur());
     r.setText(c.getRole());
   String numm=String.valueOf(c.getNumeroTel());
  Num.setText(numm);
  Ent.setText(c.getEntreprise());
 
    }
   @FXML
    private void modifierC(ActionEvent event) {
       
     ServiceCollaborateur sp= new ServiceCollaborateur();
               
 Collaborateur c = tableColl.getSelectionModel().getSelectedItem();
 int idd = Integer.parseInt(idin.getText());
 c.setId(idd);
      c.setNomCollaborateur(NomC.getText());
       c.setPrenomCollaborateur(PrenomC.getText());
          int NumeroTell = Integer.parseInt(Num.getText());
       c.setNumeroTel(NumeroTell);
       c.setRole(r.getText());
       c.setEntreprise(Ent.getText());
     
       sp.modifier(c);
        ObservableList<Collaborateur> list = FXCollections.observableArrayList(sp.afficher());

    tableColl.setItems(list);
  //LBshow.setText("aa");
    }
    @FXML
    private void archiverr(ActionEvent event) {
     ServiceCollaborateur sp = new ServiceCollaborateur();
               
  Collaborateur p = tableColl.getSelectionModel().getSelectedItem();
  // int idd = Integer.parseInt(idddd.getText());
//person.setId(idd);
      
       // System.out.println(person.getDfin());
       sp.archiver(p);
        ObservableList<Collaborateur> list = FXCollections.observableArrayList(sp.afficher());

    tableColl.setItems(list);
  //LBshow.setText("aa");
    }
 }


    

