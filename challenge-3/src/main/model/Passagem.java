package main.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.model.interfaces.IPassagem;

public class Passagem implements IPassagem {
    private Veiculo veiculo;
    private Pedagio pedagio;
    private LocalDateTime dataHora;
    private double valorCobrado;
    
    public Passagem(Veiculo veiculo, Pedagio pedagio, double valorCobrado) {
        this.veiculo = veiculo;
        this.pedagio = pedagio;
        this.dataHora = LocalDateTime.now();
        this.valorCobrado = valorCobrado;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public Pedagio getPedagio() {
        return pedagio;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public double getValorCobrado() {
        return valorCobrado;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Passagem: " + veiculo.getPlaca() + " em " + pedagio.getLocalizacao() + 
               " - Data/Hora: " + dataHora.format(formatter) + 
               " - Valor: R$ " + String.format("%.2f", valorCobrado);
    }
}
