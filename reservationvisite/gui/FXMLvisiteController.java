/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationvisite.gui;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import reservationvisite.entity.Visite;
import reservationvisite.service.ServiceVisite;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import reservationvisite.entity.Dons;
import reservationvisite.entity.Reservation;
import reservationvisite.service.MyListener;
/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLvisiteController implements Initializable {

    private ListView<Visite> listeView;
    @FXML
    private DatePicker dpdate;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfnumcarte;
    @FXML
    private TextField tfmontant;
    @FXML
    private TextField tfrecherche;
    ObservableList<Visite> data=FXCollections.observableArrayList();
    ServiceVisite sv=new ServiceVisite();
    @FXML
    private RadioButton rbhomme;
    @FXML
    private ToggleGroup g1;
    @FXML
    private RadioButton rbfemme;
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
    public String controleDeSaisie(){
        
        String erreur="";
        if(tfdescription.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Description\n";
        }
        if(tfmontant.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Montant\n";
        }
        
        if(tfnumcarte.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le num carte Nom\n";
        }
        if(dpdate.getValue()==null){
            erreur+="-Veuillez remplir le champ date\n";
        }
        if(dpdate.getValue().atStartOfDay().isBefore(LocalDateTime.now())){
            erreur+="date doit etre superieur a date d'aujourd'hui";
        }
        
        return erreur;
        
    }
    private void filldata() {
        
            Visite t=sv.getVisiteById(Integer.parseInt(idgetter.getText()));
            tfdescription.setText(t.getDescription());
            tfmontant.setText(t.getMontant());
            tfnumcarte.setText(t.getNum_carte());
            
        
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String sexe;
        if(rbhomme.isSelected()){
            sexe="Homme";
        }
        else{
            sexe="Femme";
        }
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            Visite v=new Visite(dpdate.getValue().toString(), tfdescription.getText(), sexe, tfnumcarte.getText(), tfmontant.getText());
            sv.ajouter(v);
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Ajout visite");
            tray.setMessage("Vous avez bien ajouter une visite");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
            recherche_avance(sv.afficher());
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        String sexe;
        if(rbhomme.isSelected()){
            sexe="Homme";
        }
        else{
            sexe="Femme";
        }
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur modifier");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            Visite v=new Visite(dpdate.getValue().toString(), tfdescription.getText(), sexe, tfnumcarte.getText(), tfmontant.getText());
            
                sv.modifier(v,Integer.parseInt(idgetter.getText()));
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Modifier visite");
                tray.setMessage("Vous avez bien modifier une visite");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1000));
                recherche_avance(sv.afficher());
            
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
            sv.supprimer(Integer.parseInt(idgetter.getText()));
            
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("supprimer visite");
            tray.setMessage("Vous avez bien supprimer une visite");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(1000));
            recherche_avance(sv.afficher());
       
        
    }

    @FXML
    private void logout(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void pdf(ActionEvent event) {
        String filename = "C:\\Users\\mohamedakrem\\Desktop\\Visite.xlsx";
       try   
        {  
             
            //creating an instance of HSSFWorkbook class  
            HSSFWorkbook workbook = new HSSFWorkbook();  
            //invoking creatSheet() method and passing the name of the sheet to be created   
            HSSFSheet sheet = workbook.createSheet("visite");   
            //creating the 0th row using the createRow() method  
            HSSFRow rowhead = sheet.createRow((short)0);  
            //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
            rowhead.createCell((short)0).setCellValue("ID");  
            rowhead.createCell(Short.parseShort("1")).setCellValue("Date");  
            rowhead.createCell(Short.parseShort("2")).setCellValue("Description");  
            rowhead.createCell(Short.parseShort("3")).setCellValue("Montant");  
            rowhead.createCell(Short.parseShort("4")).setCellValue("Num carte");  
            rowhead.createCell(Short.parseShort("5")).setCellValue("Sexe");
            for(int i=0;i<sv.afficher().size();i++){
                HSSFRow row = sheet.createRow((short)i+1);  
                //inserting data in the first row  
                row.createCell(Short.parseShort("0")).setCellValue(sv.afficher().get(i).getId());  
                row.createCell(Short.parseShort("1")).setCellValue(sv.afficher().get(i).getDate());  
                row.createCell(Short.parseShort("2")).setCellValue(sv.afficher().get(i).getDescription());  
                row.createCell(Short.parseShort("3")).setCellValue(sv.afficher().get(i).getMontant());  
                row.createCell(Short.parseShort("4")).setCellValue(sv.afficher().get(i).getNum_carte());  
                row.createCell(Short.parseShort("5")).setCellValue(sv.afficher().get(i).getSexe());
            }
            //creating the 1st row  
            
            
            FileOutputStream fileOut = new FileOutputStream(filename);  
            workbook.write(fileOut);  
            //closing the Stream  
            fileOut.close();  
            //closing the workbook  
              
            //prints the message on the console  
            System.out.println("Excel file has been generated successfully.");  
        }   
        catch (Exception e)   
        {  
            e.printStackTrace();  
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

    @FXML
    private void tri(ActionEvent event) {
        recherche_avance(sv.sortByDescription());
    }
    MyListener myListener;
    public void selectedVisite(Visite v){
        idgetter.setText(String.valueOf(v.getId()));
        filldata();
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
