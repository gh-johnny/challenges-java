package main.model.interfaces;

import main.model.Pedagio;
import main.model.Passagem;
import main.model.Veiculo;

import java.util.List;

public interface ISistema {
    void cadastrarVeiculo(Veiculo veiculo);
    void cadastrarPedagio(Pedagio pedagio);
    Veiculo buscarVeiculo(String placa);
    Pedagio buscarPedagio(int indice);
    double calcularValorPedagio(Veiculo veiculo, Pedagio pedagio);
    void registrarPassagem(String placa, int indicePedagio);
    String gerarRelatorioArrecadacao();
    String gerarRelatorioVeiculosPorPedagio();
    List<Veiculo> getVeiculos();
    List<Pedagio> getPedagios();
    List<Passagem> getPassagens();
}
