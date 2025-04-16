package test;

import main.model.Campeonato;
import main.model.Jogadora;
import main.model.Partida;
import main.model.Time;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CampeonatoTest {
    
    private Campeonato campeonato;
    private Time time1;
    private Time time2;
    private Time time3;
    
    @Before
    public void setUp() {
        campeonato = new Campeonato("Campeonato de Teste");
        
        time1 = new Time("Corinthians", "São Paulo");
        time2 = new Time("Palmeiras", "São Paulo");
        time3 = new Time("Santos", "Santos");
        
        campeonato.adicionarTime(time1);
        campeonato.adicionarTime(time2);
        campeonato.adicionarTime(time3);
    }
    
    @Test
    public void testAdicionarTime() {
        Time time4 = new Time("São Paulo", "São Paulo");
        campeonato.adicionarTime(time4);
        
        List<Time> times = campeonato.getTimes();
        Assert.assertEquals(4, times.size());
        Assert.assertTrue(times.contains(time4));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAdicionarTimeComMesmoNome() {
        Time timeRepetido = new Time("Corinthians", "Rio de Janeiro");
        campeonato.adicionarTime(timeRepetido);
    }
    
    @Test
    public void testCadastroJogadora() {
        Jogadora jogadora = new Jogadora("Marta", 36, "Atacante", 10);
        time1.adicionarJogadora(jogadora);
        
        Assert.assertEquals(1, time1.getJogadoras().size());
        Assert.assertEquals("Marta", time1.getJogadoras().get(0).getNome());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCadastroJogadoraComMesmoNumero() {
        Jogadora jogadora1 = new Jogadora("Marta", 36, "Atacante", 10);
        Jogadora jogadora2 = new Jogadora("Cristiane", 34, "Atacante", 10);
        
        time1.adicionarJogadora(jogadora1);
        time1.adicionarJogadora(jogadora2); // Deve lançar exceção
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCadastroJogadoraComIdadeInvalida() {
        Jogadora jogadora = new Jogadora("Jogadora Jovem", 14, "Goleira", 1);
    }
    
    @Test
    public void testRegistrarPartida() {
        Partida partida = new Partida(time1, time2);
        campeonato.adicionarPartida(partida);
        
        Assert.assertEquals(1, campeonato.getPartidas().size());
        Assert.assertFalse(partida.isFinalizada());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRegistrarPartidaComTimesIguais() {
        Partida partida = new Partida(time1, time1);
    }
    
    @Test
    public void testFinalizarPartida() {
        Partida partida = new Partida(time1, time2);
        partida.finalizarPartida(2, 1);
        
        Assert.assertTrue(partida.isFinalizada());
        Assert.assertEquals(2, partida.getGolsTimeCasa());
        Assert.assertEquals(1, partida.getGolsTimeVisitante());
    }
    
    @Test
    public void testCalculoPontuacao() {
        // Time1 vence Time2
        Partida partida1 = new Partida(time1, time2);
        partida1.finalizarPartida(2, 1);
        
        // Time1 empata com Time3
        Partida partida2 = new Partida(time1, time3);
        partida2.finalizarPartida(1, 1);
        
        // Time2 vence Time3
        Partida partida3 = new Partida(time2, time3);
        partida3.finalizarPartida(3, 0);
        
        Assert.assertEquals(4, time1.getPontos()); // 1 vitória (3 pontos) + 1 empate (1 ponto)
        Assert.assertEquals(3, time2.getPontos()); // 1 vitória (3 pontos) + 1 derrota (0 pontos)
        Assert.assertEquals(1, time3.getPontos()); // 1 empate (1 ponto) + 1 derrota (0 pontos)
    }
    
    @Test
    public void testClassificacao() {
        // Time1 vence Time2
        Partida partida1 = new Partida(time1, time2);
        partida1.finalizarPartida(2, 1);
        
        // Time3 vence Time1
        Partida partida2 = new Partida(time3, time1);
        partida2.finalizarPartida(2, 0);
        
        // Time2 empata com Time3
        Partida partida3 = new Partida(time2, time3);
        partida3.finalizarPartida(1, 1);
        
        List<Time> classificacao = campeonato.getClassificacao();
        
        // Time3: 4 pontos (1 vitória + 1 empate), saldo de gols +2
        // Time1: 3 pontos (1 vitória + 1 derrota), saldo de gols -1
        // Time2: 1 ponto (1 empate + 1 derrota), saldo de gols -1
        
        Assert.assertEquals(time3, classificacao.get(0));
        Assert.assertEquals(time1, classificacao.get(1));
        Assert.assertEquals(time2, classificacao.get(2));
    }
    
    @Test(expected = IllegalStateException.class)
    public void testFinalizarPartidaJaFinalizada() {
        Partida partida = new Partida(time1, time2);
        partida.finalizarPartida(2, 1);
        partida.finalizarPartida(3, 3); // Deve lançar exceção
    }
    
    @Test
    public void testSaldoGols() {
        Partida partida1 = new Partida(time1, time2);
        partida1.finalizarPartida(3, 1);
        
        Partida partida2 = new Partida(time3, time1);
        partida2.finalizarPartida(2, 1);
        
        Assert.assertEquals(1, time1.getSaldoGols()); // 4 gols marcados - 3 gols sofridos
        Assert.assertEquals(-2, time2.getSaldoGols()); // 1 gol marcado - 3 gols sofridos
        Assert.assertEquals(2, time3.getSaldoGols()); // 2 gols marcados - 0 gols sofridos
    }
}
