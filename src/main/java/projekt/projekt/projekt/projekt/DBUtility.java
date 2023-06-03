package projekt.projekt;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;


import java.io.IOException;


public class DBUtility extends LoggedInCtrl {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtility.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInCtrl loggedInCtrl = loader.getController();
                loggedInCtrl.setUserInfo(username);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }else {
            try {
                root = FXMLLoader.load(DBUtility.class.getResource(fxmlFile));
            } catch(IOException e){
                e.printStackTrace();
            }

        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }
    public static void signUpUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckuser = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/k-w", "root", "werner22");
            psCheckuser = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckuser.setString(1, username);
            resultSet = psCheckuser.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username!");
                alert.show();

            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                changeScene(event, "Logged-IN.fxml", "Hello!", username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckuser != null) {
            }
            try {
                psCheckuser.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (psInsert != null) {

                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null){
                try{
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/k-w", "root", "werner22");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User doesn't exists!");
                alert.show();
            }else{
                while(resultSet.next()){
                    String gotpassword = resultSet.getString("password");
                    if (gotpassword.equals(password)){
                        changeScene(event, "Logged-IN.fxml", "Hello!", username);
                    }else{
                        System.out.println("Wrong password!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Something went wrong, Try again.");
                        alert.show();
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (resultSet != null) ;
        }try{
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(preparedStatement != null){

        }try{
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(connection != null){

        }try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
