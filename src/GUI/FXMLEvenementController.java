/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Collaborateur;
import Model.Evenement;
import Services.ServiceCollaborateur;
import Services.ServiceEvenement;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
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
public class FXMLEvenementController implements Initializable {

    @FXML
    private TextField tfco1;
      @FXML
    private TextField idin;
    @FXML
    private TextField tfbillet;
    @FXML
    private TextField tfqr;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfnb;
    @FXML
    private TextField tflon;
    @FXML
    private TextField tflat;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TableView<Evenement> tableEvent;
    @FXML
    private TableColumn<Evenement,String> collaborateur1;
    @FXML
    private TableColumn<Evenement,String> nomcol;
    @FXML
    private TableColumn<Evenement,String> desc;
    @FXML
    private TableColumn<Evenement,String> nb;
    @FXML
    private TableColumn<Evenement,String> im;
    @FXML
    private TableColumn<Evenement,String> billet;
    @FXML
    private TableColumn<Evenement,String> date;
    @FXML
    private TableColumn<Evenement,String> longitude;
    @FXML
    private TableColumn<Evenement,String> latitude;
    @FXML
    private Button reb;
    @FXML
    private Button btnm;
    @FXML
    private Button btnc;
    @FXML
    private Button btnf;
    @FXML
    private Button ff;
      @FXML
    private Button aj;
    
    @FXML
    private TextField re;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ServiceEvenement sp = new ServiceEvenement ();
       
  
        collaborateur1.setCellValueFactory(new PropertyValueFactory<Evenement, String>("collaborateur1"));
         
        nomcol.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvenement"));
        nomcol.setCellFactory(TextFieldTableCell.<Evenement> forTableColumn());
        desc.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Description"));
        desc.setCellFactory(TextFieldTableCell.<Evenement> forTableColumn());
        nb.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NombreDeParticipants"));
        im.setCellValueFactory(new PropertyValueFactory<Evenement, String>("QrCode"));
        im.setCellFactory(TextFieldTableCell.<Evenement> forTableColumn());
        billet.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Billet"));
        
        date.setCellValueFactory(new PropertyValueFactory<Evenement, String>("DateDeEvenement"));
        date.setCellFactory(TextFieldTableCell.<Evenement> forTableColumn());
        longitude.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Longitude"));
        
        latitude.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Latitude"));
        
        
        
        
        tableEvent.setEditable(true);
        ObservableList<Evenement> list = FXCollections.observableArrayList(sp.afficher());
//ImageView imagecol=new ImageView(new image(this.getClass().getResourceAsStream(url1)));
        tableEvent.setItems(list);
     
    nomcol.setOnEditCommit((TableColumn.CellEditEvent<Evenement, String> event) -> {
            TablePosition<Evenement, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Evenement c = event.getTableView().getItems().get(row);
            c.setNomEvenement(newFullName);
            sp.modifier(c);
        });
        
    }    

    @FXML
   
    private void afficher(ActionEvent event) {
        ServiceEvenement sp = new ServiceEvenement();
    }
     @FXML
    private void ajouterE(ActionEvent event)  {
        
       ServiceEvenement sp= new ServiceEvenement();
if ((tfco1.getText().isEmpty()) ||(tfnom.getText().isEmpty()) || (tfdesc.getText().isEmpty())|| (nb.getText().isEmpty()) ||(im.getText().isEmpty())||(billet.getText().isEmpty())||(date.getText().isEmpty())||(tflon.getText().isEmpty())||(tflat.getText().isEmpty()))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Champ(s) vide(s)");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    }
sp.ajouter(new Evenement(Integer.parseInt(tfco1.getText()),tfnom.getText(), tfdesc.getText(), Integer.parseInt(tfnb.getText()),tfqr.getText(),Integer.parseInt(tfbillet.getText()) ,tfdate.getValue().toString(),Float.parseFloat(tflon.getText()),Float.parseFloat(tflat.getText())) );
ObservableList<Evenement> list = FXCollections.observableArrayList(sp.afficher());
tableEvent.setItems(list);


} 
@FXML
    private void supprimerE(ActionEvent event) {
         ServiceEvenement sp= new ServiceEvenement();
         Evenement act  =tableEvent.getSelectionModel().getSelectedItem();
         sp.supprimer(act.getId());
       int selectedID = tableEvent.getSelectionModel().getSelectedIndex();
        tableEvent.getItems().remove(selectedID);
       
    }
       @FXML
    private void afficherEE(ActionEvent event) {
   
     ServiceEvenement sp= new ServiceEvenement();
     Evenement c = tableEvent.getSelectionModel().getSelectedItem();
     String idd=String.valueOf(c.getId());
     idin.setText(idd);
     String nbb=String.valueOf(c.getNombreDeParticipants());
     tfnb.setText(nbb);
     String BII=String.valueOf(c.getBillet());
     tfbillet.setText(BII);
     String col1= String.valueOf(c.getCollaborateur1());
     tfco1.setText(col1);
     String lonn=String.valueOf(c.getLongitude());
     tflon.setText(lonn);
     String latt=String.valueOf(c.getLatitude());
     tflat.setText(latt);
     LocalDate localDate = LocalDate.parse(c.getDateDeEvenement());
     tfdate.setValue(localDate);
     tfnom.setText(c.getNomEvenement());
     tfdesc.setText(c.getDescription());
     tfqr.setText(c.getQrCode());
     
    
 
    }
   @FXML
    private void modifierE(ActionEvent event) {
       
    ServiceEvenement sp= new ServiceEvenement();
               
 Evenement c = tableEvent.getSelectionModel().getSelectedItem();
 int idd = Integer.parseInt(idin.getText());
  int bi = Integer.parseInt(tfbillet.getText());

 int nbb = Integer.parseInt(tfnb.getText());
 Float lonn =  Float.parseFloat(tflon.getText());
 Float latt = Float.parseFloat(tflat.getText());
       c.setId(idd);
       c.setNombreDeParticipants(nbb);
       c.setLatitude(lonn);
       c.setLongitude(latt);
     
   
       c.setNomEvenement(tfnom.getText());
       c.setDescription(tfdesc.getText());
       c.setBillet(bi);
       c.setQrCode(tfqr.getText());
       c.setDateDeEvenement(tfdate.getValue().toString());
       sp.modifier(c);
       ObservableList<Evenement> list = FXCollections.observableArrayList(sp.afficher());

    tableEvent.setItems(list);
  //LBshow.setText("aa");
    }
    
    
    }
    
  
