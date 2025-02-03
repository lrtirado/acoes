package grafico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PrimaryController {
    @FXML 
    private TableView<CandleStickData> tabelaDados; // Alterado para tipo específico
    
    private ObservableList<CandleStickData> dadosCSV = FXCollections.observableArrayList();

    @FXML
    private void handleCarregarCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
            carregarDadosCSV(file);
            atualizarTableView();
        }
    }

    @FXML
    private void abrirGrafico() {
        App.mudarParaCenaSecundaria();
}

    @FXML
    private void abrirConfiguracoes() {
        App.mudarParaCenaTerciaria();
}


    private void carregarDadosCSV(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            boolean cabecalho = true;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
            
            while ((linha = reader.readLine()) != null) {
                if (cabecalho) {
                    cabecalho = false;
                    continue;
                }
                
                String[] valores = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                CandleStickData data = new CandleStickData(
                    LocalDate.parse(valores[0].replace("\"", ""), formatter),
                    nf.parse(valores[1].replace("\"", "")).doubleValue(),
                    nf.parse(valores[2].replace("\"", "")).doubleValue(),
                    nf.parse(valores[4].replace("\"", "")).doubleValue(),
                    nf.parse(valores[5].replace("\"", "")).doubleValue(),
                    parseVolume(valores[6].replace("\"", ""))
                );
                dadosCSV.add(data);
            }
            App.getSecondaryController().atualizarGrafico(dadosCSV);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double parseVolume(String volumeStr) {
        volumeStr = volumeStr.replace(",", ".");
        if (volumeStr.endsWith("M")) {
            return Double.parseDouble(volumeStr.replace("M", "")) * 1_000_000;
        } else if (volumeStr.endsWith("B")) {
            return Double.parseDouble(volumeStr.replace("B", "")) * 1_000_000_000;
        }
        return Double.parseDouble(volumeStr);
    }

    private void atualizarTableView() {
        tabelaDados.setItems(dadosCSV);
        // Você precisa configurar as colunas da TableView no FXML ou aqui
    }
}