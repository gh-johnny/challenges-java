package main.model;

public interface IPartida {
    Time getTimeCasa();
    Time getTimeVisitante();
    boolean isFinalizada();
    void finalizarPartida(int golsTimeCasa, int golsTimeVisitante);
    int getGolsTimeCasa();
    int getGolsTimeVisitante();
    String toString();
}
