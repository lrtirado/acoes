module grafico {
    requires javafx.controls;
    requires javafx.fxml;

    opens grafico to javafx.fxml;
    exports grafico;
}
