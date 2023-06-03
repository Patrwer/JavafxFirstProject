package projekt.projekt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class controler implements Initializable {

    @FXML
    private Button b_login;
    @FXML
    private Button b_signup;
    @FXML
    private TextField textf_username;
    @FXML
    private TextField textf_password;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtility.logInUser(event, textf_username.getText(), textf_password.getText());
            }
        });

        b_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtility.changeScene(event, "sign-up.fxml", "Sign up!",null);

            }
        });

    }
}
