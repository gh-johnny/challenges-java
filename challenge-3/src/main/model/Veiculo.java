package main.model;

import main.model.interfaces.IVeiculo;

public class Veiculo implements IVeiculo {
    private String placa;
    private String tipo; // carro, moto, caminhao
    private int eixos;
    
    public Veiculo(String placa, String tipo, int eixos) {
        this.placa = placa;
        this.tipo = tipo.toLowerCase();
        this.eixos = eixos;
    }
    
    public String getPlaca() {
        return placa;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public int getEixos() {
        return eixos;
    }
    
    @Override
    public String toString() {
        if (tipo.equals("caminhao")) {
            return tipo + " - Placa: " + placa + " - Eixos: " + eixos;
        }
        return tipo + " - Placa: " + placa;
    }
}
