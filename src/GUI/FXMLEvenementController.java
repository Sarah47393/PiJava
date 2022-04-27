/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Collaborateur;
import Model.Evenement;
import Services.Sauvegarde;
import Services.ServiceCollaborateur;
import Services.ServiceEvenement;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import Services.Alerte;

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
    private Button btna;
      @FXML
    private TextField tfrech;
    
    @FXML
    private Button btnss;
    @FXML
    private ComboBox<String> trinom;
    @FXML
    private ComboBox<Evenement> cinuser;
    @FXML
    private TableColumn<?, ?> option;

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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
  /*       Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFoctory = new Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>>() {
    @Override
    public TableCell<Evenement, String> call(TableColumn<Evenement, String> param) {
        // make cell containing buttons
        final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
           

            @Override
            public void updateItem(String item, boolean empty) {
               
                //that cell created only on non-empty rows
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                    FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                   
                    deleteIcon.setStyle(
                            " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#ff1744;"
                    );
                    editIcon.setStyle(
                            " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#00E676;"
                    );
                     deleteIcon.setOnMouseClicked((MouseEvent event)-> {
                        ServiceEvenement sr = new ServiceEvenement();
                        Evenement ls = new Evenement();
                        ls = tableEvent.getSelectionModel().getSelectedItem();
                        sr.supprimer(ls.getId());
                       
                    });
                   
                    
                    HBox managebtn = new HBox(editIcon, deleteIcon);
                    managebtn.setStyle("-fx-alignment:center");
              // HBox.setMargin(deleteIcon,new Insets(2, 2, 0, 3));
              //HBox.setMargin(editIcon,new Insets(2, 3, 0, 2));
                    setGraphic(managebtn);
                    setText(null);
                }
            }
        };
        return cell;
    }
};
  option.setCellFactory(cellFoctory);
         tableEvent.setItems(list);


*/        
        
        
        
        
        
        
        
        
        
        
     
    nomcol.setOnEditCommit((TableColumn.CellEditEvent<Evenement, String> event) -> {
            TablePosition<Evenement, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Evenement c = event.getTableView().getItems().get(row);
            c.setNomEvenement(newFullName);
            sp.modifier(c);
        });
       
    
    
    
    
    
    
    tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
        ServiceEvenement sp1 = new ServiceEvenement();
        Evenement u1 =new Evenement();
        String nom = tfrech.getText();
        u1.setDescription(nom);
        u1.setNomEvenement(nom);
        u1.setDateDeEvenement(nom);
         try{
              int cin1 = Integer.parseInt(nom);
             u1.setId(cin1);
         }
   catch(Exception e){}
    //   LBshow.setText(nom);
          ObservableList<Evenement> list1 = FXCollections.observableArrayList(sp1.rechstream(u1));

    tableEvent.setItems(list1);
    if(tfrech.getText().trim().isEmpty()){    tableEvent.setItems(list);}
   ;
});
      trinom.getItems().setAll("description", "nom_evenement", "date_de_evenement");

    // bind the selected fruit label to the selected fruit in the combo box.
  //  LBshow.textProperty().bind(trinom.getSelectionModel().selectedItemProperty());

    // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
      trinom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        
      @Override 
      public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
     if(newFruit=="description"){  ObservableList<Evenement> list2 = FXCollections.observableArrayList(sp.tristreamDescription());
       tableEvent.setItems(list2);}
     if(newFruit=="nom_evenement"){  ObservableList<Evenement> list2 = FXCollections.observableArrayList(sp.tristreamnom());
       tableEvent.setItems(list2);}
     if(newFruit=="date_de_evenement"){  ObservableList<Evenement> list2 = FXCollections.observableArrayList(sp.tristreamdate());
       tableEvent.setItems(list2);}
    }  }); 
    
    
    ObservableList<Evenement> list22 = FXCollections.observableArrayList(sp.afficher());
//cinuser.add(list22.get(0));
cinuser.setItems(list22);
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
       

    @FXML
   
    private void afficher(ActionEvent event) {
        
        
        
        ServiceEvenement sp = new ServiceEvenement();
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
                Sauvegarde pp=new Sauvegarde();
        pp.imprimer();
        ObservableList<Evenement> list = FXCollections.observableArrayList(sp.afficher());

        
        
        
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
Alerte.display("VisualArtsTeam", "un évènement a été ajouté avec succès");
ObservableList<Evenement> list = FXCollections.observableArrayList(sp.afficher());
tableEvent.setItems(list);
int cin2    =  cinuser.getSelectionModel().getSelectedItem().getId();
        try {
            Calendar(tfnom.getText(),tfdate.getValue().toString(),tfdate.getValue().toString());
        } catch (IOException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    
    
    
    //Calendrier
    public void Calendar(String Titre , String Datedeb , String Datefin) throws MalformedURLException, IOException{
        // Using Calendar api
          URL url = new URL("https://www.googleapis.com/calendar/v3/calendars/c_9h3pelfcv9rerqvoob7q6mh7f0@group.calendar.google.com/events/");
HttpURLConnection http = (HttpURLConnection)url.openConnection();
http.setRequestMethod("POST");
http.setDoOutput(true);
http.setRequestProperty("Authorization", "Bearer ya29.A0ARrdaM9P_U9Tvke-OH_MYC6cuSyDIe4_4r30caN3gOxFkuocL4FWW24KADJiIJYgC4TxVFwLC-2ZWSgdFT5fBcDAH5WtQjCcpcfjX9VNL69AP4-wx9xPgLlkWRP3Bh_Owmgj-rpQrbvGpK1OD7a1pDWCbEMW");
String data = "{\n\"summary\": \""+Titre+"\",\n  \"location\": \"Visual Arts Application\",\n  \"start\": {\n    \"dateTime\": \""+Datedeb+"T10:00:00.000-07:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+Datefin+"T10:25:00.000-07:00\"\n    }\n\n}";
//String data = "{\n\"summary\": \"tournament\",\n  \"location\": \"Arena Application\",\n  \"start\": {\n    \"dateTime\": \""+tfDateDebut.getValue().format(DateTimeFormatter.ISO_DATE)+"T10:00:00.000-07:00\"\n  },\n  \"end\": {\n    \"dateTime\": \""+tfDateFin.getValue().format(DateTimeFormatter.ISO_DATE)+"\n    },\n\"etag\": \"\", \n      \"backgroundColor\": \"#b80672\", \n      \"timeZone\": \"UTC\", \n      \"accessRole\": \"reader\",\n\"kind\": \"calendar#calendarListEntry\", \n      \"foregroundColor\": \"#ffffff\", \n      \"defaultReminders\": [], \n      \"colorId\": \"2\"\n\n}\n";
byte[] out = data.getBytes(StandardCharsets.UTF_8);

OutputStream stream = http.getOutputStream();
stream.write(out);

System.out.println(http.getResponseCode() + " " + http.getResponseMessage() + " Event added to Calendar Successfully");
http.disconnect();
        
        // end Calendar 
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void sauvegarde(ActionEvent event) {
    }

    
    
    }
    
  
