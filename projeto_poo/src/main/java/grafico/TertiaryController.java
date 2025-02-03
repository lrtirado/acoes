package grafico;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;

public class TertiaryController {
    @FXML private ColorPicker corFundo;
    @FXML private ColorPicker corCandle;
    
    @FXML
    private void aplicarConfiguracoes() {
        Color novaCorFundo = corFundo.getValue();
        Color novaCorCandle = corCandle.getValue();
        
        // Aplicar ao gráfico
        App.getSecondaryController().aplicarEstilos(novaCorFundo, novaCorCandle);
    }
    @FXML
    private void voltarParaPrincipal() {
        App.mudarParaCenaPrincipal();
    }
}

// Adicione no SecondaryController.java:
public void aplicarEstilos(Color corFundo, Color corCandle) {
    chartPane.setStyle("-fx-background-color: " + toHexString(corFundo) + ";");
    // Implemente a lógica para alterar cores dos candles
}

private static String toHexString(Color color) {
    return String.format("#%02X%02X%02X",
        (int)(color.getRed() * 255),
        (int)(color.getGreen() * 255),
        (int)(color.getBlue() * 255));
}
