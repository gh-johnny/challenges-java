package main.model;

import java.util.List;

public interface ITime {
    String getNome();
    String getCidade();
    List<Jogadora> getJogadoras();
    void adicionarJogadora(Jogadora jogadora);
    int getPontos();
    void adicionarVitoria();
    void adicionarEmpate();
    void adicionarDerrota();
    int getVitorias();
    int getEmpates();
    int getDerrotas();
    int getGolsMarcados();
    void adicionarGolsMarcados(int gols);
    int getGolsSofridos();
    void adicionarGolsSofridos(int gols);
    int getSaldoGols();
    String toString();
}
