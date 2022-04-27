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
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
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
public class FXMLfonttCollaborateurController implements Initializable {

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
}
