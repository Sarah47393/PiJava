/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Model.Order;
import Services.OrderDetailsService;
import Services.OrderService;
import static utils.Connection.connection;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class OrdersFXMLController implements Initializable {

    @FXML
    private TableView<Order> ordersTableView;
    @FXML
    private TableColumn<Order, String> idColumn;
    @FXML
    private TableColumn<Order, String> idUserColumn;
    @FXML
    private TableColumn<Order, String> descriptionColumn;
    @FXML
    private TableColumn<Order, String> totalColumn;

    @FXML
    private TableColumn<Order, String> statusColumn;
    @FXML
    private Button updateStatusButton;
    @FXML
    private Button showDetailsButton;

    OrderService orderService = new OrderService();
    @FXML
    private PieChart piechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayOrders();
        updateStatusButton.setOnAction((t) -> {
            orderService.updateOrderStatus(ordersTableView.getSelectionModel().getSelectedItem().getId());
            ordersTableView.refresh();
        });
        showDetailsButton.setOnAction((t) -> {
            OrderDetailsService.idOrder = ordersTableView.getSelectionModel().getSelectedItem().getId();
            navigate();
        });
          setPieChart();
         // getData();
        
     
    }

    public void displayOrders() {
        //
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ordersTableView.setItems(orderService.showOrders());
        
    }

    public void navigate() {
       /* try {
            Parent parent = FXMLLoader.load(getClass().getResource("OrderDetailsFXML.fxml"));
            //FXMLLoader loader=new FXMLLoader(getClass().getResource("OrderDetailsFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            //FXMLLoader loader=new FXMLLoader(getClass().getResource("OrderDetailsFXML.fxml"));
            stage.setScene(scene);
            //OrderDetailsFXMLController controller = loader.getController();
            Order order= ordersTableView.getSelectionModel().getSelectedItem();
            //controller.QrCode(order.getDescription(), order.getStatus() );
            stage.setTitle("Update Product");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        //} catch (IOException ex) {
            Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       
Stage stage= new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderDetailsFXML.fxml"));

          Parent root=null;
            try {
                root = (Parent)loader.load();
            } catch (IOException ex) {
                Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
          OrderDetailsFXMLController controller = loader.getController();

          Order order= ordersTableView.getSelectionModel().getSelectedItem();
          stage.setScene(new Scene(root));
          stage.setTitle("Gestion Promotion");
          stage.show();
controller.QrCode(order.getDescription(), order.getStatus() );
    }

    public void setPieChart() {
        PieChart.Data delivered = new PieChart.Data("delivered", 3);
        PieChart.Data pending = new PieChart.Data("pending", 1);

        piechart.getData().add(delivered);
        piechart.getData().add(pending);

    }

    public void getData() {
        try {
            String query = "SELECT COUNT(*) FROM commande WHERE status = 'delivered'";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(""+ resultSet.getInt(1));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(OrdersFXMLController.class.getName()).log(Level.SEVERE, null, ex);
          
        }
    }
}
