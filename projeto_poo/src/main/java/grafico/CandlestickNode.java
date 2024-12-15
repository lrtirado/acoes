package grafico;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CandlestickNode extends Pane {

    //definicao do grafico
    public CandlestickNode (double min, double max, double open, double close, double globalMin, double globalMax) {
        
        //tamanho do grafico e verificacao de erro
        double chartHeight = 400;
        if (globalMin >= globalMax) {
            throw new IllegalArgumentException("Minimo global deve ser menor que o maximo global");
        }

        double scale = chartHeight / (globalMax - globalMin); //escala do grafico

        //Convertando os valores para coordenadas do grafico
        double minY = chartHeight - (min - globalMin) * scale;
        double maxY = chartHeight - (max - globalMin) * scale;
        double openY = chartHeight - (open - globalMin) * scale;
        double closeY = chartHeight - (close - globalMin) * scale;
        
        //verificacao de erro
        minY = Math.max(0, minY);
        maxY = Math.min(chartHeight, maxY);
        openY = Math.max(0, openY);
        closeY = Math.min(chartHeight, closeY);


        //linha vertical (max e min)
        Line line = new Line(0, minY, 0, maxY);
        line.setStroke(Color.BLACK);

        //corpo do grafico candlestick
        double bodyHeight = Math.abs(openY - closeY);
        Rectangle body = new Rectangle (-5, Math.min(openY, closeY), 10, bodyHeight);

        //adicionar metodo para mudanca de cor de acordo com preferencias do usuario
        body.setFill(open > close ? Color.RED : Color.GREEN);
        body.setStroke(Color.BLACK);

        getChildren().addAll(line, body);
    }

}
