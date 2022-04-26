/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.User;
import Services.ServiceUser;
import Model.Emplois;
import Services.ServiceEmplois;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLEmploisController implements Initializable {

    @FXML
    private TableView<Emplois> tableemplois;

    @FXML
    private TableColumn<Emplois, String> nomcol;
    @FXML
    private TableColumn<Emplois, String> prenomcol;
    @FXML
    private TableColumn<Emplois, String> cincol;
    @FXML
    private TableColumn<Emplois, String> ddbut;
    @FXML
    private TableColumn<Emplois, String> dfin;
    @FXML
    private TextField recheerche;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button load;
    @FXML
    private ComboBox<String> tri;
    private DatePicker datedebut;
    private DatePicker datefin;
    @FXML
    private TextField idddd;
    @FXML
    private TextField debut;
    @FXML
    private TextField fin;
    @FXML
    private ComboBox<User> cinuser;
    @FXML
    private Button archiver;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ServiceEmplois sp = new ServiceEmplois();
         ServiceUser sp7 = new ServiceUser();
  
ObservableList<User> list22 = FXCollections.observableArrayList(sp7.affichercombo());
//cinuser.add(list22.get(0));
cinuser.setItems(list22);
   /*cinuser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
      @Override public void changed(ObservableValue<? extends User> selected, String oldFruit, String newFruit) {*/
    cinuser.valueProperty().addListener(new ChangeListener<User>() {

        @Override
        public void changed(ObservableValue<? extends User> observable, User oldValue,
                User newValue) 
        {
    /* if(newFruit=="nom"){  ObservableList<Emplois> list2 = FXCollections.observableArrayList(sp.tristreamnom());
       tableemplois.setItems(list2);}
     if(newFruit=="prenom"){  ObservableList<Emplois> list2 = FXCollections.observableArrayList(sp.tristreamprenom());
       tableemplois.setItems(list2);}
     if(newFruit=="debut"){  ObservableList<Emplois> list2 = FXCollections.observableArrayList(sp.tristreamdated());
       tableemplois.setItems(list2);}
     if(newFruit=="fin"){  ObservableList<Emplois> list2 = FXCollections.observableArrayList(sp.tristreamdatef());
       tableemplois.setItems(list2);}*/
            
    }  }); 


    
        nomcol.setCellValueFactory(new PropertyValueFactory<Emplois, String>("nom"));
              nomcol.setCellFactory(TextFieldTableCell.<Emplois> forTableColumn());
prenomcol.setCellValueFactory(new PropertyValueFactory<Emplois, String>("prenom"));
 prenomcol.setCellFactory(TextFieldTableCell.<Emplois> forTableColumn());
 cincol.setCellValueFactory(new PropertyValueFactory<Emplois, String>("cin"));
   //cincol.setCellFactory(TextFieldTableCell.<User> forTableColumn());
ddbut.setCellValueFactory(new PropertyValueFactory<Emplois, String>("ddebut"));
 ddbut.setCellFactory(TextFieldTableCell.<Emplois> forTableColumn());
 dfin.setCellValueFactory(new PropertyValueFactory<Emplois, String>("dfin"));
 dfin.setCellFactory(TextFieldTableCell.<Emplois> forTableColumn());

  tableemplois.setEditable(true);
    nomcol.setOnEditCommit((TableColumn.CellEditEvent<Emplois, String> event) -> {
            TablePosition<Emplois, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Emplois person = event.getTableView().getItems().get(row);
            person.setNom(newFullName);
            sp.modifier(person);
        });
            prenomcol.setOnEditCommit((TableColumn.CellEditEvent<Emplois, String> event) -> {
            TablePosition<Emplois, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Emplois person = event.getTableView().getItems().get(row);
            person.setPrenom(newFullName);
            sp.modifier(person);
        });
                    ddbut.setOnEditCommit((TableColumn.CellEditEvent<Emplois, String> event) -> {
            TablePosition<Emplois, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Emplois person = event.getTableView().getItems().get(row);
            person.setDdebut(newFullName);
            sp.modifier(person);
        });
                            dfin.setOnEditCommit((TableColumn.CellEditEvent<Emplois, String> event) -> {
            TablePosition<Emplois, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Emplois person = event.getTableView().getItems().get(row);
            person.setDfin(newFullName);
            sp.modifier(person);
        });
    ObservableList<Emplois> list = FXCollections.observableArrayList(sp.afficher());
//ImageView imagecol=new ImageView(new image(this.getClass().getResourceAsStream(url1)));
    tableemplois.setItems(list);
    recheerche.textProperty().addListener((observable, oldValue, newValue) -> {
           ServiceEmplois sp1 = new ServiceEmplois();
           Emplois u1 =new Emplois();
        String nom = recheerche.getText();
        u1.setNom(nom);
        //u1.setAccess(nom);
        u1.setPrenom(nom);
       // u1.setRole(nom);
        u1.setDdebut(nom);
                u1.setDfin(nom);

         try{
              int cin1 = Integer.parseInt(nom);
             u1.setCin(cin1);
         }
   catch(Exception e){}
       // LBshow.setText(nom);
          ObservableList<Emplois> list1 = FXCollections.observableArrayList(sp1.rechstream(u1));

    tableemplois.setItems(list1);
    if(recheerche.getText().trim().isEmpty()){    tableemplois.setItems(list);}
   ;
});
      tri.getItems().setAll("nom", "prenom", "debut","fin");

    // bind the selected fruit label to the selected fruit in the combo box.
  //  LBshow.textProperty().bind(trinom.getSelectionModel().selectedItemProperty());

    // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
    tri.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
     if(newFruit=="nom"){  ObservableList<Emplois> list2 = FXCollections.observableArrayList(sp.tristreamnom());
       tableemplois.setItems(list2);}
     if(newFruit=="prenom"){  ObservableList<Emplois> list2 = FXCollections.observableArrayList(sp.tristreamprenom());
       tableemplois.setItems(list2);}
     if(newFruit=="debut"){  ObservableList<Emplois> list2 = FXCollections.observableArrayList(sp.tristreamdated());
       tableemplois.setItems(list2);}
     if(newFruit=="fin"){  ObservableList<Emplois> list2 = FXCollections.observableArrayList(sp.tristreamdatef());
       tableemplois.setItems(list2);}
    }  });  } 
    @FXML
        private void ajouteremplois(ActionEvent event) {
       
             
              /*  if ((LocalDate.of(debut.getText())>fin.getText()))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("vous devez etre age au moins de 18 ans ");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    } */
             
        ServiceEmplois sp = new ServiceEmplois();
   //  int cinn2 = Integer.parseInt( cinuser.getSelectionModel().getSelectedItem().getCin());
     int cin2    =  cinuser.getSelectionModel().getSelectedItem().getCin();
    // int cinn = Integer.parseInt(tfid.getText());
          
        sp.ajouter(new Emplois(cin2,debut.getText(),fin.getText()));
          ObservableList<Emplois> list = FXCollections.observableArrayList(sp.afficher());

    tableemplois.setItems(list);
    }
     @FXML
    private void afficherPersonnes1(ActionEvent event) {
     //   FileInputStream ifp= new FileInputStream(file);
      
            
     ServiceEmplois sp = new ServiceEmplois();
            
      
  Emplois person = tableemplois.getSelectionModel().getSelectedItem();
   
   
  String idd=String.valueOf(person.getId());
  idddd.setText(idd);
   // tfnom.setText(person.getNom());
     debut.setText(person.getDdebut());
       fin.setText(person.getDfin());
      }
    
     @FXML
    private void modPersonnes1(ActionEvent event) {
     ServiceEmplois sp = new ServiceEmplois();
               
  Emplois person = tableemplois.getSelectionModel().getSelectedItem();
   int idd = Integer.parseInt(idddd.getText());
 person.setId(idd);
       person.setDdebut(debut.getText());
       person.setDfin(fin.getText());
       // System.out.println(person.getDfin());
       sp.modifier(person);
        ObservableList<Emplois> list = FXCollections.observableArrayList(sp.afficher());

    tableemplois.setItems(list);
  //LBshow.setText("aa");
    }
     @FXML
    private void archPersonnes1(ActionEvent event) {
     ServiceEmplois sp = new ServiceEmplois();
               
  Emplois person = tableemplois.getSelectionModel().getSelectedItem();
  // int idd = Integer.parseInt(idddd.getText());
//person.setId(idd);
      
       // System.out.println(person.getDfin());
       sp.arch(person);
        ObservableList<Emplois> list = FXCollections.observableArrayList(sp.afficher());

    tableemplois.setItems(list);
  //LBshow.setText("aa");
    }
}
