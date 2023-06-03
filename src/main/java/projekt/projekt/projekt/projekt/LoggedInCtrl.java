package projekt.projekt;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInCtrl implements Initializable {

    @FXML
    private Button b_logout;
    @FXML
    private Button b_sum;
    @FXML
    private Button b_spend;
    @FXML
    private Button b_inc;
    @FXML
    private ImageView iv_logo;
    @FXML
    private Label l_welcome;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_logout.setOnAction(event -> DBUtility.changeScene(event, "mainscene.fxml", "Log In!", null));

    }

    public void setUserInfo(String username)
    {
        l_welcome.setText("Welcome " + username +"!");
    }
}
