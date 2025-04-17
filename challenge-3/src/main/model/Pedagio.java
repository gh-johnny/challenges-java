package main.model;

import main.model.interfaces.IPedagio;

public class Pedagio implements IPedagio {
    private String localizacao;
    private double tarifaBase;
    private double valorArrecadado;
    private int passagens;
    
    public Pedagio(String localizacao, double tarifaBase) {
        this.localizacao = localizacao;
        this.tarifaBase = tarifaBase;
        this.valorArrecadado = 0.0;
        this.passagens = 0;
    }
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    public double getTarifaBase() {
        return tarifaBase;
    }
    
    public double getValorArrecadado() {
        return valorArrecadado;
    }
    
    public int getPassagens() {
        return passagens;
    }
    
    public void registrarPassagem(double valor) {
        this.valorArrecadado += valor;
        this.passagens++;
    }
    
    @Override
    public String toString() {
        return localizacao + " - Tarifa base: R$ " + String.format("%.2f", tarifaBase);
    }
}
