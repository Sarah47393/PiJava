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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Model.Order;
import Model.OrderDetails;
import Model.Product;
import Services.OrderDetailsService;
import Services.OrderService;
import Services.ProductService;
import utils.LoginSession;
import utils.SendMail;
/**
 * FXML Controller class
 *
 * @author weixin
 */
public class StoreFXMLController implements Initializable {

    @FXML
    private TableView<OrderDetails> orderTableView;
    @FXML
    private TableColumn<OrderDetails, String> idProductColumn;
    @FXML
    private TableColumn<OrderDetails, String> quantityColumn;
    @FXML
    private TableColumn<OrderDetails, String> totalColumn;
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
    private TextField quantityTextField;
    @FXML
    private Button addQuabtityButton;
    @FXML
    private Label totalLabel;
    SendMail x =new SendMail();
    ProductService productService = new ProductService();
    OrderDetailsService orderDetailsService = new OrderDetailsService();
    OrderService orderService = new OrderService();
    OrderDetails orderDetails;
    @FXML
    private Button orderButton;
    private TextField descriptionTextView;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private Button logoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        totalLabel.setText("0");
        displayProducts();
        logoutButton.setOnAction((ActionEvent event)->{
        LoginSession.idLoggedUser = 0;
    LoginSession.isLogged = false;
    try {
                    Parent parent = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(StoreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        addQuabtityButton.setOnAction((t) -> {
            int idProduct = productTableView.getSelectionModel().getSelectedItem().getId();
            int quantity = Integer.parseInt(quantityTextField.getText());
            double total = quantity * productTableView.getSelectionModel().getSelectedItem().getPrix();
            orderDetails = new OrderDetails(idProduct, 0, quantity, total);
            displayOrder();
            orderTableView.getItems().add(orderDetails);
//if doesn't work just try setIems
            double totalOrder = Double.parseDouble(totalLabel.getText()) + total;
            totalLabel.setText(totalOrder + "");
            orderTableView.refresh();
            quantityTextField.setText("1");

        });
        orderButton.setOnAction((t) -> {
            try {
                Order order = new Order(LoginSession.idLoggedUser, descriptionTextField.getText(), Double.parseDouble(totalLabel.getText()));
                orderService.addOrder(order);
                saveOrderDetails();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ordering");
                alert.setHeaderText(null);
                alert.setContentText("Your order has been successfully saved!");
                alert.showAndWait();
                //vide panier
                 orderTableView.getItems().clear();
                orderTableView.refresh();
                totalLabel.setText("0");
                descriptionTextField.setText("");
                //email
              SendMail.sendMail();
                
                
               
            } catch (Exception ex) {
                Logger.getLogger(StoreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void displayProducts() {
        //affichage tableView
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        subtitleColumn.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        //
        productTableView.setItems(productService.showProduct());
    }

    public void displayOrder() {
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    public void saveOrderDetails() {
        ObservableList<OrderDetails> orderDetailsObservableList = orderTableView.getItems();
        for (int i = 0; i < orderDetailsObservableList.size(); i++) {
            orderDetailsObservableList.get(i).setIdOrder(orderService.findLastOrder());
            orderDetailsService.addOrderDetails(orderDetailsObservableList.get(i));
        }
    }
    

}
