/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Model.Product;
import Services.ProductService;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ProductFXMLController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField subtitleTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField prixTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private Button submitButton;

    String name, description, subtitle, image;
    int category;
    double prix;

    ProductService productService = new ProductService();
    int idProduct = productService.idProduct;
    boolean selected = productService.selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (selected) {
            Product product = productService.findById(idProduct);
            nameTextField.setText(product.getName());
            descriptionTextField.setText(product.getDescription());
            subtitleTextField.setText(product.getSubtitle());
            imageTextField.setText(product.getImage());
            categoryTextField.setText("" + product.getIdCategory());
            prixTextField.setText("" + product.getPrix());
        }
        submitButton.setOnAction((ActionEvent event) -> {
            name = nameTextField.getText();
            description = descriptionTextField.getText();
            subtitle = subtitleTextField.getText();
            image = imageTextField.getText();
            category = Integer.parseInt(categoryTextField.getText());
            prix = Double.parseDouble(prixTextField.getText());
            Product product = new Product(name, image, subtitle, description, prix, category);
          if (!selected) {
             /*
               if (tfOcasion.getText().isEmpty()||tfNomProduit.getText().isEmpty()|| tfPrixOrig.getText().isEmpty()|| tfDateDeb.getValue().toString().isEmpty()|| tfDateFin.getValue().toString().isEmpty() || tfPrixRed.getText().isEmpty())
        {
          Alert a = new Alert(Alert.AlertType.ERROR, "veuillez saisir tous les champs", ButtonType.OK);
          a.showAndWait();
        } else {
          Promotion p=new Promotion(ocasion, nomProduit, prixOrig, dateDeb, dateFin, prixRed);
          ServicePromotion S=new ServicePromotion();
          S.ajouter(p);
          Alert a = new Alert(Alert.AlertType.INFORMATION, "Promotion added !", ButtonType.OK);
          a.showAndWait();
        }
              */
                           
                productService.addProduct(product);
                  TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Produit ajouté ");
            tr.setMessage("Ajouté avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            
               
            } else {
                productService.updateProduct(idProduct, product);
                productService.selected = false;
                   TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Produit modifié ");
            tr.setMessage("updated succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            } 
        
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.close();
        });

    }

}
