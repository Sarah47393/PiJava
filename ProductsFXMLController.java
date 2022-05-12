/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import Model.Product;
import Services.ProductService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author weixin
 */
public class ProductsFXMLController implements Initializable {

    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, String> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> categoryColumn;
    @FXML
    private TableColumn<Product, String> subtitleColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, String> prixColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    ProductService productService = new ProductService();
    @FXML
    private Button refreshButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayProducts();
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (productTableView.getSelectionModel().getSelectedItem() != null) {
                    System.out.println(productTableView.getSelectionModel().getSelectedItem().getId());
                    productService.deleteProduct(productTableView.getSelectionModel().getSelectedItem().getId());
                productTableView.refresh();
                displayProducts();
                 TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Produit supprimé ");
            tr.setMessage("bien supprimé");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
                }
            }
        });
        addButton.setOnAction((ActionEvent event) -> {
            navigate();
           
        });
        editButton.setOnAction((ActionEvent event) -> {
            productService.idProduct = productTableView.getSelectionModel().getSelectedItem().getId();
            productService.selected = true;
            navigate();
             displayProducts();
            
        }); 
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //productTableView.refresh();
                        displayProducts();

            }
        });
    }

    public void displayProducts() {
      
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("idCategory"));
        subtitleColumn.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        productTableView.setItems(productService.showProduct());
    }

    public void navigate() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ProductFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Product");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
