package grafico;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import java.util.List;

public class SecondaryController {
    @FXML private Pane chartPane;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    @FXML
    private void voltarParaPrincipal() {
    App.mudarParaCenaPrincipal();
    }
    
    public void atualizarGrafico(List<CandleStickData> dados) {
        chartPane.getChildren().clear();
        
        // Calcular escala
        double maxVal = dados.stream().mapToDouble(CandleStickData::getMaximo).max().orElse(1);
        double yScale = chartPane.getHeight() / maxVal;
        
        // Posicionar candles
        double xPos = 50;
        for (CandleStickData dado : dados) {
            CandleStickNode candle = new CandleStickNode(
                dado.getAbertura(),
                dado.getFechamento(),
                dado.getMaximo(),
                dado.getMinimo(),
                yScale
            );
            candle.setLayoutX(xPos);
            candle.setLayoutY(chartPane.getHeight() - (dado.getMaximo() * yScale));
            chartPane.getChildren().add(candle);
            xPos += 15;
        }
    }
}