module projekt.projekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens projekt.projekt to javafx.fxml;
    exports projekt.projekt;
}