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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import Model.User;
import Services.ServiceUser;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javafx.util.converter.IntegerStringConverter;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author HP
 */

public class FXMLUserController implements Initializable {
  
    @FXML
    private TextField tfnom;
     @FXML
    private TextField tfid;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfcin;
    @FXML
    private TextField tfpassword;
    @FXML
    private Label LBshow;
    @FXML
    private TextField tfrole;
    @FXML
    private TextField tfaccess;
    @FXML
    private TextField tfimage;
    @FXML
    private DatePicker tfdate;
       @FXML
    private TextField tfrech;
    @FXML
    private TableView<User> tableuser;
    @FXML
private TableColumn<User, String> nomcol;
@FXML
private TableColumn<User, String> prenomcol;
    @FXML
private TableColumn<User, String> cincol;
@FXML
private TableColumn<User, String> passwordcol;
    @FXML
private TableColumn<User, String> rolecol;
@FXML
private TableColumn<User, String> accesscol;
    @FXML
private TableColumn<User, String> imagecol;
@FXML
private TableColumn<User, String> datecol;
@FXML
private TableColumn<User, String> idcol;
@FXML
private ComboBox<String> trinom;
@FXML
private ImageView Image;
private FileInputStream fis;
private File file;

@FXML
private Button ajout;
@FXML
private Button supp;
@FXML
private Button img;
@FXML
private Button nomtri;
    @FXML
    private Button arch;
    @FXML
    private ListView<User> listuser;
    @FXML
    private TextField tfemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // assert tfcin != null : "cin ne doit pas etre vide";
         //   assert tfcin.getText().length()== 8 : "cin doit etre de longeur 8";
  //  assert tfdate != null : "fx:id=\"tfdate\" was not injected: veuilez saisire une date de naissance";
    //  assert tfdate.getValue().isAfter(LocalDate.of(2008, 01, 01)) : "fx:id=\"tfdate\" vous devez avoir au moins 18 ans";
 //   assert tfpassword != null : "password ne peut pas etre null";
   // assert pearImage != null : "fx:id=\"pearImage\" was not injected: check your FXML file 'fruitcombo.fxml'.";
 //   assert selectedFruit != null : "fx:id=\"selectedFruit\" was not injected: check your FXML file 'fruitcombo.fxml'.";

    ServiceUser sp = new ServiceUser();
        String url1 = "http://127.0.0.1/projetpi/public/Uploads/image/";
   ObservableList<User> items =FXCollections.observableArrayList (
                sp.afficher());
        listuser.setItems(items);

        listuser.setCellFactory(param -> new ListCell<User>() {
            private ImageView imageView = new ImageView();
            
            @Override
            public void updateItem(User name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                } else {
                  Image image = new Image(url1+name.getImage());
                        imageView.setImage(image);
                    setText(name.toString());
                    setGraphic(imageView);
                }
            }
        });
    
        nomcol.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
              nomcol.setCellFactory(TextFieldTableCell.<User> forTableColumn());
prenomcol.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
 prenomcol.setCellFactory(TextFieldTableCell.<User> forTableColumn());
 cincol.setCellValueFactory(new PropertyValueFactory<User, String>("cin"));
   //cincol.setCellFactory(TextFieldTableCell.<User> forTableColumn());
passwordcol.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
 rolecol.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
  rolecol.setCellFactory(TextFieldTableCell.<User> forTableColumn());
accesscol.setCellValueFactory(new PropertyValueFactory<User, String>("access"));
 accesscol.setCellFactory(TextFieldTableCell.<User> forTableColumn());
 imagecol.setCellValueFactory(new PropertyValueFactory<User, String>("image"));

datecol.setCellValueFactory(new PropertyValueFactory<User, String>("datenaissance"));
 datecol.setCellFactory(TextFieldTableCell.<User> forTableColumn());
 idcol.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
  tableuser.setEditable(true);
   
          nomcol.setOnEditCommit((CellEditEvent<User, String> event) -> {
            TablePosition<User, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            User person = event.getTableView().getItems().get(row);
            person.setNom(newFullName);
            sp.modifier(person);
        });
            prenomcol.setOnEditCommit((CellEditEvent<User, String> event) -> {
            TablePosition<User, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            User person = event.getTableView().getItems().get(row);
            person.setPrenom(newFullName);
            sp.modifier(person);
        });
    cincol.setOnEditCommit((CellEditEvent<User, String> event) -> {
            TablePosition<User, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();
            int newFullName1 = Integer.parseInt(newFullName);
            int row = pos.getRow();
            User person = event.getTableView().getItems().get(row);
            person.setCin(newFullName1);
            sp.modifier(person);
        });
      rolecol.setOnEditCommit((CellEditEvent<User, String> event) -> {
            TablePosition<User, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            User person = event.getTableView().getItems().get(row);
            person.setRole(newFullName);
            sp.modifier(person);
        });
        accesscol.setOnEditCommit((CellEditEvent<User, String> event) -> {
            TablePosition<User, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            User person = event.getTableView().getItems().get(row);
            person.setAccess(newFullName);
            sp.modifier(person);
        });
          datecol.setOnEditCommit((CellEditEvent<User, String> event) -> {
            TablePosition<User, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            User person = event.getTableView().getItems().get(row);
            person.setDatenaissance(newFullName);
            sp.modifier(person);
        });
  
     ObservableList<User> list = FXCollections.observableArrayList(sp.afficher());
//ImageView imagecol=new ImageView(new image(this.getClass().getResourceAsStream(url1)));
    tableuser.setItems(list);
    tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
           ServiceUser sp1 = new ServiceUser();
           User u1 =new User();
        String nom = tfrech.getText();
        u1.setNom(nom);
        u1.setAccess(nom);
        u1.setPrenom(nom);
        u1.setRole(nom);
        u1.setEmail(nom);
        u1.setDatenaissance(nom);
         try{
              int cin1 = Integer.parseInt(nom);
             u1.setCin(cin1);
         }
   catch(Exception e){}
        LBshow.setText(nom);
          ObservableList<User> list1 = FXCollections.observableArrayList(sp1.rechstream(u1));

    listuser.setItems(list1);
    if(tfrech.getText().trim().isEmpty()){    listuser.setItems(items);}
   ;
});
      trinom.getItems().setAll("nom", "prenom", "date");

    // bind the selected fruit label to the selected fruit in the combo box.
  //  LBshow.textProperty().bind(trinom.getSelectionModel().selectedItemProperty());

    // listen for changes to the fruit combo box selection and update the displayed fruit image accordingly.
    trinom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override public void changed(ObservableValue<? extends String> selected, String oldFruit, String newFruit) {
     if(newFruit=="nom"){  ObservableList<User> list2 = FXCollections.observableArrayList(sp.tristreamnom());
       listuser.setItems(list2);}
     if(newFruit=="prenom"){  ObservableList<User> list2 = FXCollections.observableArrayList(sp.tristreamprenom());
       listuser.setItems(list2);}
     if(newFruit=="date"){  ObservableList<User> list2 = FXCollections.observableArrayList(sp.tristreamdate());
       listuser.setItems(list2);}
    }  });  }
     @FXML
    private void ajouterPersonne(ActionEvent event) {
       
             
               
     if ((tfnom.getText().isEmpty())||(tfimage.getText().isEmpty()) ||(tfpassword.getText().isEmpty())||(tfemail.getText().isEmpty()) ||(tfprenom.getText().isEmpty()) || (tfcin.getText().isEmpty())|| (tfrole.getText().isEmpty()) ||(tfaccess.getText().isEmpty()))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("Champ(s) vide(s)");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    }
     else               
     if ((tfcin.getText().length()!=8))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("cin doit etre de taille 8");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    }
     
      else               
     if ((tfdate.getValue().isAfter(LocalDate.of(2008, 01, 01))))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("vous devez etre age au moins de 18 ans ");
                         alert.setContentText("Veuillez remplir tous les champs");
                         alert.show();
                    }
     //someString.matches("stores.*store.*product.*")
      else               
     if (!tfemail.getText().matches(".*@.*..*"))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("vous devez entrer une address mail valide ");
                         alert.setContentText("vous devez entrer une address mail valide ");
                         alert.show();
                    }
     else{  ServiceUser sp = new ServiceUser();
       int pass = Integer.parseInt(tfpassword.getText());
            int cinn = Integer.parseInt(tfcin.getText());
          
        sp.ajouter(new User(tfnom.getText() ,tfprenom.getText(),pass,cinn,tfrole.getText(),tfaccess.getText(),tfimage.getText(),tfdate.getValue().toString(),tfemail.getText()));
          ObservableList<User> list = FXCollections.observableArrayList(sp.afficher());

    listuser.setItems(list);}
  
    }

    @FXML
    private void supprimerPersonnes(ActionEvent event) {
               ServiceUser sp = new ServiceUser();
               
    User person = tableuser.getSelectionModel().getSelectedItem();
    
sp.supprimer(person);
 ObservableList<User> list = FXCollections.observableArrayList(sp.afficher());

    listuser.setItems(list);
    
    }
     @FXML
    private void afficherPersonnes1(ActionEvent event) {
     //   FileInputStream ifp= new FileInputStream(file);
      
             String url1 = "http://127.0.0.1/projetpi/public/Uploads/image/";
     ServiceUser sp = new ServiceUser();
            
      
  User person = listuser.getSelectionModel().getSelectedItem();
     Image image = new Image(url1+person.getImage());
    // String decodedString = new String(base64.decode(person.getImage().getBytes()));
   /*try{  byte[] bytes = person.getImage().getBytes("UTF-8");
     String s2 = new String(bytes, "UTF-8");
   System.out.println(s2);}
   catch(Exception e){}*/
  Image.setImage(image);
  String idd=String.valueOf(person.getId());
  tfid.setText(idd);
    tfnom.setText(person.getNom());
     tfprenom.setText(person.getPrenom());
      tfemail.setText(person.getEmail());
     String ci= String.valueOf(person.getCin());
      tfcin.setText(ci);
      tfrole.setText(person.getRole());
       tfaccess.setText(person.getAccess());
         LocalDate localDate = LocalDate.parse(person.getDatenaissance());
       tfdate.setValue(localDate);
   
    
  //LBshow.setText("aa");
 
    }
      @FXML
    private void modPersonnes1(ActionEvent event) {
     ServiceUser sp = new ServiceUser();
               
  User person = tableuser.getSelectionModel().getSelectedItem();
   int idd = Integer.parseInt(tfid.getText());
 person.setId(idd);
       person.setNom(tfnom.getText());
       person.setPrenom(tfprenom.getText());
          int cci = Integer.parseInt(tfcin.getText());
       person.setCin(cci);
        person.setEmail(tfemail.getText());
       person.setRole(tfrole.getText());
       person.setAccess(tfaccess.getText());
       person.setDatenaissance(tfdate.getValue().toString());
       sp.modifier(person);
        ObservableList<User> list = FXCollections.observableArrayList(sp.afficher());

    listuser.setItems(list);
  //LBshow.setText("aa");
    }
       @FXML
    private void image(ActionEvent event) {
         String url1 = "/xampp/htdocs/projetpi/public/Uploads/image/";
          JFileChooser chooser=new JFileChooser(); 
               chooser.showOpenDialog(null);
               File f=chooser.getSelectedFile();
//               String fileName=f.getAbsolutePath();
                 String fileName1=f.getName();
                // try{ String fileName2=f.getCanonicalPath();
              /*}
               
                 catch(Exception e){}*/
               //   Image image = new Image(tfimage.getText());
                 //  Image.setImage(image);
                  // File file = JFileChooser.showSaveDialog(primaryStage);
               //    File file2 = chooser.showSaveDialog();
             

   /* try {

        f.move(Paths.get(fileName), Paths.get(url1), StandardCopyOption.REPLACE_EXISTING);

    } catch (Exception e) {

     
        e.printStackTrace();
    }*/
       System.out.println(f.getAbsolutePath());
    /* f = new File(fileName);
    f.renameTo(new File(url1));*/
    //f.move(fileName, url1, StandardCopyOption.REPLACE_EXISTING);
   /*try{ f.createNewFile();}
   catch(Exception e){}*/
  /*  Path source = Paths.get(fileName);
  Path target = Paths.get(url1);

  try{

    Files.move(source, target);

  } catch (IOException e) {
    e.printStackTrace();
  }*/
    System.out.println(f.getPath());
    String fileName2 = f.getName();          
String fileExtension = fileName2.substring(fileName2.lastIndexOf(".") + 1, f.getName().length());
System.out.println(fileExtension);
String uuid = UUID.randomUUID().toString()+"."+fileExtension;
//String s=fileExtension;
      Path copied = Paths.get("c:"+url1+uuid);
        Path originalPath = Paths.get(f.getAbsolutePath());
try{Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);

 System.out.println(copied);
 
}
catch(Exception e){}
 tfimage.setText(uuid);
  /*   Path movefrom = FileSystems.getDefault().getPath(f.getPath());
            try{ Path target = FileSystems.getDefault().getPath(url1+f.getName());
            Path targetDir = FileSystems.getDefault().getPath(url1); System.out.println("te66est");
           
                Files.copy(movefrom,target, StandardCopyOption.REPLACE_EXISTING);
                 System.out.println("teest");
            }catch (IOException e){ System.out.println("te55est");}*/
    }
   
      @FXML
      private void tri(ActionEvent event) {
           ServiceUser sp = new ServiceUser();
           User u =new User();
      //  String nom = tfrech.getText();
      //  u.setNom(nom);
        
      //    ObservableList<User> list = FXCollections.observableArrayList(sp.tristream(u));

    //tableuser.setItems(list);
    }
          @FXML
    private void archPersonnes1(ActionEvent event) {
     ServiceUser sp = new ServiceUser();
               
  User person = listuser.getSelectionModel().getSelectedItem();
  // int idd = Integer.parseInt(idddd.getText());
//person.setId(idd);
      
       // System.out.println(person.getDfin());
       sp.arch(person);
        ObservableList<User> list = FXCollections.observableArrayList(sp.afficher());

    listuser.setItems(list);
  //LBshow.setText("aa");
    }

    @FXML
    private void tfemail(ActionEvent event) {
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
    private void emp(ActionEvent event)throws IOException {
            
      Parent root = FXMLLoader.load(getClass().getResource("FXMLEmplois.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
}
