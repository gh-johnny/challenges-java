package main.model;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private String cidade;
    private List<Jogadora> jogadoras;
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;

    public Time(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
        this.jogadoras = new ArrayList<>();
        this.pontos = 0;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public List<Jogadora> getJogadoras() {
        return jogadoras;
    }

    public void adicionarJogadora(Jogadora jogadora) {
        // Verificar se o número da camisa já está sendo usado
        for (Jogadora j : jogadoras) {
            if (j.getNumeroCamisa() == jogadora.getNumeroCamisa()) {
                throw new IllegalArgumentException("Número de camisa já está sendo usado por outra jogadora.");
            }
        }
        jogadoras.add(jogadora);
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarVitoria() {
        this.vitorias++;
        this.pontos += 3;
    }

    public void adicionarEmpate() {
        this.empates++;
        this.pontos += 1;
    }

    public void adicionarDerrota() {
        this.derrotas++;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public void adicionarGolsMarcados(int gols) {
        this.golsMarcados += gols;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    public void adicionarGolsSofridos(int gols) {
        this.golsSofridos += gols;
    }

    public int getSaldoGols() {
        return golsMarcados - golsSofridos;
    }

    @Override
    public String toString() {
        return nome + " (" + cidade + ")";
    }
}
