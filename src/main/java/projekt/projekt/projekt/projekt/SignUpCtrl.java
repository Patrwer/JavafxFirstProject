package projekt.projekt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpCtrl implements Initializable {

    @FXML
    private Button b_register;
    @FXML
    private Button b_memberlogin;
    @FXML
    private TextField textf_username;
    @FXML
    private TextField textf_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!textf_username.getText().trim().isEmpty() && !textf_password.getText().trim().isEmpty()){
                    DBUtility.signUpUser(event, textf_username.getText(), textf_password.getText());
                }else {
                    System.out.println("You cannot leave blank fields.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You cannot leave blank fields.");
                    alert.show();
                }
            }
        });

        b_memberlogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtility.changeScene(event, "mainscene.fxml", "Log in.", null);

            }
        });


    }
}
