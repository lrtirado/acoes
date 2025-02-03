package grafico;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class CandleStickNode extends Pane {
    private static final double WIDTH = 8;
    
    public CandleStickNode(double open, double close, double high, double low, double yScale) {
        double altura = Math.abs(open - close) * yScale;
        double y = Math.min(open, close) * yScale;
        
        Rectangle corpo = new Rectangle(WIDTH, altura);
        corpo.setFill(close > open ? Color.GREEN : Color.RED);
        corpo.setY(y);
        
        Line linhaSuperior = new Line(WIDTH/2, high * yScale, WIDTH/2, y + altura);
        Line linhaInferior = new Line(WIDTH/2, y, WIDTH/2, low * yScale);
        
        getChildren().addAll(linhaSuperior, linhaInferior, corpo);
    }
}