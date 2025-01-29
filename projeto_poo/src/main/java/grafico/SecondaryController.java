package grafico;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;

public class SecondaryController {

    @FXML
    private XYChart<Number, Number> candlestickChart;

    private double globalMin = Double.MAX_VALUE;
    private double globalMax = Double.MIN_VALUE;

    /** Função responsável por carregar o arquivo CSV */
    public void loadCsv(File file) {
        List<String[]> linhas = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            boolean isFirstLine = true; // Ignorar cabeçalho
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();

                // Ignorar a primeira linha (cabeçalho)
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Divide os campos respeitando aspas e vírgulas
                String[] campos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Remover aspas de todos os campos
                for (int i = 0; i < campos.length; i++) {
                    campos[i] = campos[i].replace("\"", "").trim();
                }

                // Validação de campos mínimos
                if (campos.length < 6) {
                    System.err.println("Linha inválida: " + linha);
                    continue;
                }

                linhas.add(campos);

                try {
                    // Converte os valores para double, substituindo vírgulas por pontos
                    double min = Double.parseDouble(campos[4].replace(",", "."));
                    double max = Double.parseDouble(campos[5].replace(",", "."));

                    // Atualiza os limites globais
                    globalMin = Math.min(globalMin, min);
                    globalMax = Math.max(globalMax, max);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao interpretar valores numéricos: " + linha);
                }
            }

            // Renderiza o gráfico com os dados processados
            renderChart(linhas);
        } catch (Exception e) {
            System.err.println("Erro ao carregar o arquivo CSV: " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    private void renderChart(List<String[]> linhas) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        int time = 0; // Incremental, pois "DATA" não será usada no eixo X
        for (String[] linha : linhas) {
            try {
                // Converte os valores para double, substituindo vírgulas por pontos
                double open = Double.parseDouble(linha[1].replace(",", "."));
                double close = Double.parseDouble(linha[2].replace(",", "."));
                double min = Double.parseDouble(linha[4].replace(",", "."));
                double max = Double.parseDouble(linha[5].replace(",", "."));

                // Adiciona os pontos ao gráfico
                series.getData().add(createDataPoint(time, min, max, open, close));
                time++;
            } catch (NumberFormatException e) {
                System.err.println("Erro ao interpretar linha para gráfico: " + String.join(",", linha));
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