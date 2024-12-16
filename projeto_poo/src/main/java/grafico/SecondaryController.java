package grafico;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class SecondaryController {

    @FXML
    private XYChart<Number, Number> candlestickChart;

    @FXML
    private BarChart<Number, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private double globalMin =  Double.MAX_VALUE;
    private double globalMax =  Double.MIN_VALUE;


    //** Função responsável por carregar o arquivo CSV */
    public void loadCsv(File file) {
        List<String[]> linhas = new ArrayList<>();

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String[] campos = linha.split("\",\"");
                    

                    //validacao de erros nos dados de cada linha
                    if (campos.length < 5) {
                        System.err.println("Erro ao processar a linha: " + linha);
                        continue;
                    }


                    linhas.add(campos);

                    try {
                        double min = Double.parseDouble(campos[1]);
                        double max = Double.parseDouble(campos[2]);

                        globalMin = Math.min(globalMin, min);
                        globalMax = Math.max(globalMax, max);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao interpretar valores numericos: " + linha);
                    }
                }
                
                renderChart(linhas);
                } catch (Exception e) {
                System.err.println("Erro ao carregar o arquivo CSV: " + file.getAbsolutePath());
                e.printStackTrace();
            }
        }
        private void renderChart(List<String[]> linhas) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();

            for (String[] linha : linhas) {
                try {                   
                
                    int time = Integer.parseInt(linha[0]);
                    double min = Double.parseDouble(linha[4].replace(",","."));
                    double max = Double.parseDouble(linha[5].replace(",","."));
                    double open = Double.parseDouble(linha[1].replace(",","."));
                    double close = Double.parseDouble(linha[2].replace(",","."));

                    series.getData().add(createDataPoint(time, min, max, open, close));
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao interpretar linha para grafico: " + linha);
                }
            }
            candlestickChart.getData().clear();
            candlestickChart.getData().add(series);
            }
                
        private XYChart.Data<Number, Number> createDataPoint(int time, double min, double max, double open, double close) {
                XYChart.Data<Number, Number> data = new XYChart.Data<>(time, max);

                data.setNode(new CandlestickNode(min, max, open, close, globalMin, globalMax));

                return data;               
            }
}
