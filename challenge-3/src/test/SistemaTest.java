package test;

import main.model.Pedagio;
import main.model.Sistema;
import main.model.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaTest {
    private Sistema sistema;
    private Pedagio pedagio1;
    private Pedagio pedagio2;
    private Veiculo carro;
    private Veiculo moto;
    private Veiculo caminhao;
    
    @BeforeEach
    public void setUp() {
        sistema = new Sistema();
        
        // Cadastrar pedágios
        pedagio1 = new Pedagio("Bandeirantes km 32", 10.50);
        pedagio2 = new Pedagio("Anhanguera km 26", 9.80);
        sistema.cadastrarPedagio(pedagio1);
        sistema.cadastrarPedagio(pedagio2);
        
        // Cadastrar veículos
        carro = new Veiculo("ABC1234", "carro", 0);
        moto = new Veiculo("XYZ5678", "moto", 0);
        caminhao = new Veiculo("DEF9012", "caminhao", 3);
        sistema.cadastrarVeiculo(carro);
        sistema.cadastrarVeiculo(moto);
        sistema.cadastrarVeiculo(caminhao);
    }
    
    @Test
    public void testCalculoTarifaCarro() {
        double valor = sistema.calcularValorPedagio(carro, pedagio1);
        assertEquals(10.50, valor, 0.01);
    }
    
    @Test
    public void testCalculoTarifaMoto() {
        double valor = sistema.calcularValorPedagio(moto, pedagio1);
        assertEquals(5.25, valor, 0.01);
    }
    
    @Test
    public void testCalculoTarifaCaminhao() {
        double valor = sistema.calcularValorPedagio(caminhao, pedagio1);
        assertEquals(31.50, valor, 0.01);
    }
    
    @Test
    public void testRegistroPassagem() {
        sistema.registrarPassagem("ABC1234", 0);
        sistema.registrarPassagem("XYZ5678", 0);
        sistema.registrarPassagem("DEF9012", 1);
        
        assertEquals(2, sistema.getPassagens().size());
        assertEquals(2, pedagio1.getPassagens());
        assertEquals(1, pedagio2.getPassagens());
        
        assertEquals(15.75, pedagio1.getValorArrecadado(), 0.01);
        assertEquals(29.40, pedagio2.getValorArrecadado(), 0.01);
    }
    
    @Test
    public void testBuscaVeiculo() {
        Veiculo encontrado = sistema.buscarVeiculo("ABC1234");
        assertNotNull(encontrado);
        assertEquals("carro", encontrado.getTipo());
        
        Veiculo naoEncontrado = sistema.buscarVeiculo("NAO9876");
        assertNull(naoEncontrado);
    }
    
    @Test
    public void testRelatorioArrecadacao() {
        // Registrar algumas passagens
        sistema.registrarPassagem("ABC1234", 0);
        sistema.registrarPassagem("XYZ5678", 0);
        sistema.registrarPassagem("DEF9012", 1);
        
        String relatorio = sistema.gerarRelatorioArrecadacao();
        
        assertTrue(relatorio.contains("Bandeirantes km 32"));
        assertTrue(relatorio.contains("Anhanguera km 26"));
        assertTrue(relatorio.contains("15,75"));
        assertTrue(relatorio.contains("29,40"));
    }
    
    @Test
    public void testRelatorioVeiculosPorPedagio() {
        // Registrar algumas passagens
        sistema.registrarPassagem("ABC1234", 0);
        sistema.registrarPassagem("XYZ5678", 0);
        sistema.registrarPassagem("DEF9012", 1);
        
        String relatorio = sistema.gerarRelatorioVeiculosPorPedagio();
        
        assertTrue(relatorio.contains("carro: 1"));
        assertTrue(relatorio.contains("moto: 1"));
        assertTrue(relatorio.contains("caminhao: 1"));
    }
}
