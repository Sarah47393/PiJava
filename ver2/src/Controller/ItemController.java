package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListener;
import Model.User;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
      String url1 = "http://127.0.0.1/projetpi/public/Uploads/image/";

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(Personne);
    }

    private  User Personne;
    private MyListener myListener;

    public void setData(User fruit, MyListener myListener) {
        this.Personne = fruit;
        this.myListener = myListener;
        nameLabel.setText(fruit.getEmail());
             priceLable.setText(String.valueOf(fruit.getCin()));
     //   priceLable.setText(Main.CURRENCY + fruit.getCin());
       Image image = new Image(url1+fruit.getImage());
       // Image image = new Image(getClass().getResourceAsStream(url1+fruit.getImage()));
        img.setImage(image);
    }
}
