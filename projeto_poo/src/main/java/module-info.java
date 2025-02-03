module grafico {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    
    opens grafico to javafx.fxml;
    exports grafico;
}
