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
import reservationvisite.entity.Visite;
import reservationvisite.service.MyListener;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLaffichervisiteController implements Initializable {

    @FXML
    private AnchorPane anchore;
    @FXML
    private Label ldate;
    @FXML
    private Label ldescription;
    @FXML
    private Label lsexe;
    @FXML
    private Label lnumcarte;
    @FXML
    private Label lmontant;
    private MyListener myListener;
    private Visite v;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void setData(Visite v,MyListener myListener){
        this.v=v;
        this.myListener=myListener;
        ldate.setText(v.getDate());
        ldescription.setText(v.getDescription());
        lmontant.setText(v.getMontant());
        lnumcarte.setText(v.getNum_carte());
        lsexe.setText(v.getSexe());
        
    }

    @FXML
    private void selectedvisite(MouseEvent event) {
        myListener.onClickListener2(v);
    }
    
}
