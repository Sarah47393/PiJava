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
public class FXMLFRONTEventController implements Initializable {

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
    }
}