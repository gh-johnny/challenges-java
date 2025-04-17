package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.model.ICampeonato;

public class Campeonato implements ICampeonato {
    private String nome;
    private List<Time> times;
    private List<Partida> partidas;

    public Campeonato(String nome) {
        this.nome = nome;
        this.times = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarTime(Time time) {
        // Verificar se já existe um time com o mesmo nome
        for (Time t : times) {
            if (t.getNome().equalsIgnoreCase(time.getNome())) {
                throw new IllegalArgumentException("Já existe um time com esse nome no campeonato.");
            }
        }
        times.add(time);
    }

    public void adicionarPartida(Partida partida) {
        // Verificar se os times da partida estão no campeonato
        if (!times.contains(partida.getTimeCasa()) || !times.contains(partida.getTimeVisitante())) {
            throw new IllegalArgumentException("Os times da partida devem estar cadastrados no campeonato.");
        }
        partidas.add(partida);
    }

    public List<Time> getTimes() {
        return times;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public List<Time> getClassificacao() {
        // Criar uma cópia da lista de times para não alterar a original
        List<Time> classificacao = new ArrayList<>(times);
        
        // Ordenar por pontos (decrescente), depois por saldo de gols (decrescente)
        Collections.sort(classificacao, new Comparator<Time>() {
            @Override
            public int compare(Time t1, Time t2) {
                if (t1.getPontos() != t2.getPontos()) {
                    return t2.getPontos() - t1.getPontos(); // Ordem decrescente de pontos
                }
                if (t1.getSaldoGols() != t2.getSaldoGols()) {
                    return t2.getSaldoGols() - t1.getSaldoGols(); // Ordem decrescente de saldo de gols
                }
                return t2.getGolsMarcados() - t1.getGolsMarcados(); // Ordem decrescente de gols marcados
            }
        });
        
        return classificacao;
    }

    public String getTabelaClassificacao() {
        List<Time> classificacao = getClassificacao();
        StringBuilder tabela = new StringBuilder();
        
        tabela.append("\n===== TABELA DE CLASSIFICAÇÃO =====\n");
        tabela.append(String.format("%-4s %-20s %-3s %-3s %-3s %-3s %-3s %-5s\n", 
                "Pos", "Time", "Pts", "V", "E", "D", "GP", "GS"));
        
        int posicao = 1;
        for (Time time : classificacao) {
            tabela.append(String.format("%-4d %-20s %-3d %-3d %-3d %-3d %-3d %-5d\n", 
                    posicao++, 
                    time.getNome(), 
                    time.getPontos(), 
                    time.getVitorias(), 
                    time.getEmpates(), 
                    time.getDerrotas(),
                    time.getGolsMarcados(),
                    time.getGolsSofridos()));
        }
        
        return tabela.toString();
    }
}
