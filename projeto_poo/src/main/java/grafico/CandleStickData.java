package grafico;

import java.time.LocalDate;

public class CandleStickData {
    private LocalDate data;
    private double abertura;
    private double fechamento;
    private double minimo;
    private double maximo;
    private double volume;

    public CandleStickData(LocalDate data, double abertura, double fechamento, 
                          double minimo, double maximo, double volume) {
        this.data = data;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.minimo = minimo;
        this.maximo = maximo;
        this.volume = volume;
    }

    // Getters
    public LocalDate getData() { return data; }
    public double getAbertura() { return abertura; }
    public double getFechamento() { return fechamento; }
    public double getMinimo() { return minimo; }
    public double getMaximo() { return maximo; }
    public double getVolume() { return volume; }
}