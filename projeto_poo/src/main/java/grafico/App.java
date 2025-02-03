package grafico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static PrimaryController primaryController;
    private static SecondaryController secondaryController;
    private static TertiaryController tertiaryController;
    
    private static Parent primaryRoot;
    private static Parent secondaryRoot;
    private static Parent tertiaryRoot;
    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        
        // Carrega a interface principal
        FXMLLoader primaryLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
        primaryRoot = primaryLoader.load();
        primaryController = primaryLoader.getController();
        
        // Carrega a interface secundária
        FXMLLoader secondaryLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        secondaryRoot = secondaryLoader.load();
        secondaryController = secondaryLoader.getController();
        
        // Carrega a interface de configuração
        FXMLLoader tertiaryLoader = new FXMLLoader(getClass().getResource("tertiary.fxml"));
        tertiaryRoot = tertiaryLoader.load();
        tertiaryController = tertiaryLoader.getController();
        
        // Configura a cena inicial
        primaryStage.setScene(new Scene(primaryRoot, 1000, 700));
        primaryStage.setTitle("Painel de Ações - Watchlist");
        primaryStage.show();
    }

    // Métodos para navegação entre telas
    public static void mudarParaCenaPrincipal() {
        mainStage.setScene(new Scene(primaryRoot, 1000, 700));
        mainStage.setTitle("Painel de Ações - Watchlist");
    }

    public static void mudarParaCenaSecundaria() {
        mainStage.setScene(new Scene(secondaryRoot, 1200, 800));
        mainStage.setTitle("Gráfico Candlestick");
    }

    public static void mudarParaCenaTerciaria() {
        mainStage.setScene(new Scene(tertiaryRoot, 600, 400));
        mainStage.setTitle("Configurações");
    }

    // Getters para os controllers
    public static SecondaryController getSecondaryController() {
        return secondaryController;
    }

    public static TertiaryController getTertiaryController() {
        return tertiaryController;
    }

    public static PrimaryController getPrimaryController() {
        return primaryController;
    }

    public static void main(String[] args) {
        System.setProperty("glass.accessible.force", "false");
        launch(args);
    }
}