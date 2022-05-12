/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationvisite.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import reservationvisite.entity.Dons;
import reservationvisite.service.MyListener;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLafficherdonsController implements Initializable {

    @FXML
    private AnchorPane anchore;
    @FXML
    private Label lnumcarte;
    @FXML
    private Label lmontant;
    private Dons d;
    private MyListener myListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Dons d,MyListener myListener){
        this.d=d;
        this.myListener=myListener;
        
        lmontant.setText(d.getMontant());
        lnumcarte.setText(d.getNum_carte());

        
    }
    @FXML
    private void selecteddons(MouseEvent event) {
        myListener.onClickListener(d);
    }
    

    
    
}
