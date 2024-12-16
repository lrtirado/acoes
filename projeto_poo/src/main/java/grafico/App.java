package grafico;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static final Map<String, Object> controllers = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Carrega o FXML inicial
        scene = new Scene(loadFXML("primary"), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Visualizador de Gráficos Candlestick");
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);  // Muda para a nova cena
    }
    

    public static Object getController(String fxml) {
        // Retorna o controlador associado ao FXML
        return controllers.get(fxml);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // Método para carregar o arquivo FXML
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return loader.load();
    }

    public static void main(String[] args) {
        launch(args); // Inicia o aplicativo JavaFX
    }
}
