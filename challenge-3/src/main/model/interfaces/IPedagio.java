package main.model.interfaces;

public interface IPedagio {
    String getLocalizacao();
    double getTarifaBase();
    double getValorArrecadado();
    int getPassagens();
    void registrarPassagem(double valor);
}
