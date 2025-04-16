package main.model;

public class Partida {
    private Time timeCasa;
    private Time timeVisitante;
    private int golsTimeCasa;
    private int golsTimeVisitante;
    private boolean finalizada;

    public Partida(Time timeCasa, Time timeVisitante) {
        if (timeCasa == timeVisitante) {
            throw new IllegalArgumentException("Os times da partida não podem ser iguais");
        }
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
        this.golsTimeCasa = 0;
        this.golsTimeVisitante = 0;
        this.finalizada = false;
    }

    public Time getTimeCasa() {
        return timeCasa;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public int getGolsTimeCasa() {
        return golsTimeCasa;
    }

    public int getGolsTimeVisitante() {
        return golsTimeVisitante;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void finalizarPartida(int golsTimeCasa, int golsTimeVisitante) {
        if (finalizada) {
            throw new IllegalStateException("Partida já foi finalizada");
        }
        
        this.golsTimeCasa = golsTimeCasa;
        this.golsTimeVisitante = golsTimeVisitante;
        this.finalizada = true;
        
        // Atualizar estatísticas dos times
        timeCasa.adicionarGolsMarcados(golsTimeCasa);
        timeCasa.adicionarGolsSofridos(golsTimeVisitante);
        
        timeVisitante.adicionarGolsMarcados(golsTimeVisitante);
        timeVisitante.adicionarGolsSofridos(golsTimeCasa);
        
        // Atualizar pontuação
        if (golsTimeCasa > golsTimeVisitante) {
            timeCasa.adicionarVitoria();
            timeVisitante.adicionarDerrota();
        } else if (golsTimeCasa < golsTimeVisitante) {
            timeCasa.adicionarDerrota();
            timeVisitante.adicionarVitoria();
        } else {
            timeCasa.adicionarEmpate();
            timeVisitante.adicionarEmpate();
        }
    }

    @Override
    public String toString() {
        String resultado = timeCasa.getNome() + " vs " + timeVisitante.getNome();
        if (finalizada) {
            resultado += " - Placar: " + golsTimeCasa + " x " + golsTimeVisitante;
        } else {
            resultado += " (não realizada)";
        }
        return resultado;
    }
}
