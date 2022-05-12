/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Model.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.OrderDetails;
import Services.OrderDetailsService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author weixin
 */
public class OrderDetailsFXMLController implements Initializable {
    
    @FXML
    private TableColumn<OrderDetails, String> idColumn;
    @FXML
    private TableColumn<OrderDetails, String> idProductColumn;
    @FXML
    private TableColumn<OrderDetails, String> idOrderColumn;
    @FXML
    private TableColumn<OrderDetails, String> quantityColumn;
    @FXML
    private TableColumn<OrderDetails, String> totalColumn;
    @FXML
    private TableView<OrderDetails> orderDetailsTableView;
    
    
    
    @FXML
    private WebView mapwebview;

    private WebEngine webengine;
    
    OrderDetailsService orderDetailsService = new OrderDetailsService();
    @FXML
    private ImageView QrCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayOrderDetails();
        webengine = mapwebview.getEngine();
        webengine.load("https://www.google.com/maps/@36.8982107,10.189235");
    }    

    public void displayOrderDetails() {
        //
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderDetailsTableView.setItems(orderDetailsService.showOrderDetailsByIdOrder(OrderDetailsService.idOrder));
    }    
    
    
    public void QrCode(String name,String LieuName) {
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = name+" a "+LieuName;
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(GUI.OrderDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         // QrCode; = new ImageView();
         QrCode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
     /*   StackPane root = new StackPane();
        root.getChildren().add( QrCode); 
Scene scene = new Scene(root, 350, 350);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();  */


    }
}
