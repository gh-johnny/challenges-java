package main.model;

import java.util.List;

public interface ICampeonato {
    String getNome();
    void adicionarTime(Time time);
    void adicionarPartida(Partida partida);
    List<Time> getTimes();
    List<Partida> getPartidas();
    List<Time> getClassificacao();
    String getTabelaClassificacao();
}
