/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.User;
import Services.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLUsernewController implements Initializable {

    @FXML
    private Button ajout;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfcin;
    @FXML
    private TextField tfrole;
    @FXML
    private DatePicker tfdate;
    
    @FXML
    private TextField tfimage;
    @FXML
    private Button img;
    @FXML
    private Button nomtri;
    @FXML
    private TextField tfemail;
    @FXML
    private ComboBox<String> acess;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfrole.setText("Membre");
           acess.getItems().setAll("Membre", "VIP");
    }    
 @FXML
    private void ajouterPersonne(ActionEvent event) throws IOException{
       ServiceUser sp778 = new ServiceUser();
             
               
     if ((tfnom.getText().isEmpty())||(tfimage.getText().isEmpty()) ||(tfpassword.getText().isEmpty())||(tfemail.getText().isEmpty()) ||(tfprenom.getText().isEmpty()) || (tfcin.getText().isEmpty())|| (tfrole.getText().isEmpty()) )
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
     else if(sp778.existeemail(tfemail.getText())){ Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("address mail deja utiliser ");
                         alert.setContentText("address mail deja utiliser ");
                         alert.show();}
     else{  ServiceUser sp = new ServiceUser();
       int pass = Integer.parseInt(tfpassword.getText());
            int cinn = Integer.parseInt(tfcin.getText());
        String acces=  acess.getSelectionModel().getSelectedItem();
        sp.ajouter(new User(tfnom.getText() ,tfprenom.getText(),pass,cinn,tfrole.getText(),acces,tfimage.getText(),tfdate.getValue().toString(),tfemail.getText()));
               User a=new User(tfnom.getText() ,tfprenom.getText(),pass,cinn,tfrole.getText(),acces,tfimage.getText(),tfdate.getValue().toString(),tfemail.getText());
     
        System.out.println(a);
        System.out.println(sp.existe(a));
        a.setMyVariable(sp.existe2(a));
             Parent root = FXMLLoader.load(getClass().getResource("FXMLUserfront.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
              

    }
  
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
    private void deco(ActionEvent event)throws IOException {
            
      Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
}
