package grafico;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class PrimaryController {

    @FXML
    private Button uploadButton;

    @FXML
    private Label statusLabel;

    private File selectedFile;

    @FXML
    private void uploadAction (ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar arquivo CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if (file != null) {
            selectedFile = file;
            statusLabel.setText("Arquivo selecionado: " + file.getName());
        } else {
            statusLabel.setText("Nenhum arquivo selecionado");
        }
    }
    

    @FXML
    private void nextAction(ActionEvent event) {
        if (selectedFile == null) {
            statusLabel.setText("Por favor, selecione um arquivo CSV válido antes de prosseguir.");
            return;
        }

        try {
            // Carrega o arquivo FXML da cena secundária
            FXMLLoader loader = new FXMLLoader(App.class.getResource("secondary.fxml"));
            Parent root = loader.load(); // Carrega a cena

            // Obtém o controlador associado à cena
            SecondaryController secondaryController = loader.getController();

            if (secondaryController != null) {
                // Carrega o CSV utilizando o controlador
                secondaryController.loadCsv(selectedFile);

                // Troca a cena para a "secondary"
                Scene scene = uploadButton.getScene(); // Obtenha a cena atual
                scene.setRoot(root); // Define o novo root da cena
            } else {
                statusLabel.setText("Erro ao carregar o controlador da tela secundária.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao trocar de cena: " + e.getMessage());
            statusLabel.setText("Erro interno. Por favor, tente novamente.");
        }
    }

}
