package test;

import main.model.Atendimento;
import main.model.Pessoa;
import main.model.Sistema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class SistemaTest {
    
    private Sistema sistema;
    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private Pessoa pessoa3;
    
    @Before
    public void setUp() {
        sistema = new Sistema();
        
        pessoa1 = new Pessoa("João Silva", 30, "Física", "moderado", "Rua A, 123");
        pessoa2 = new Pessoa("Maria Oliveira", 25, "Visual", "leve", "Rua B, 456");
        pessoa3 = new Pessoa("Pedro Santos", 40, "Física", "severo", "Rua C, 789");
        
        sistema.cadastrarPessoa(pessoa1);
        sistema.cadastrarPessoa(pessoa2);
        sistema.cadastrarPessoa(pessoa3);
    }
    
    @Test
    public void testCadastrarPessoa() {
        Pessoa pessoa4 = new Pessoa("Ana Lima", 35, "Auditiva", "moderado", "Rua D, 101");
        sistema.cadastrarPessoa(pessoa4);
        
        Assert.assertEquals(4, sistema.getTotalPessoas());
        Assert.assertTrue(sistema.listarPessoas().contains(pessoa4));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCriarPessoaComGrauInvalido() {
        new Pessoa("Teste", 20, "Física", "muito alto", "Rua Teste");
    }
    
    @Test
    public void testListarPessoas() {
        List<Pessoa> pessoas = sistema.listarPessoas();
        
        Assert.assertEquals(3, pessoas.size());
        Assert.assertTrue(pessoas.contains(pessoa1));
        Assert.assertTrue(pessoas.contains(pessoa2));
        Assert.assertTrue(pessoas.contains(pessoa3));
    }
    
    @Test
    public void testFiltrarPorTipoDeficiencia() {
        List<Pessoa> pessoasFisica = sistema.filtrarPorTipoDeficiencia("Física");
        
        Assert.assertEquals(2, pessoasFisica.size());
        Assert.assertTrue(pessoasFisica.contains(pessoa1));
        Assert.assertTrue(pessoasFisica.contains(pessoa3));
        Assert.assertFalse(pessoasFisica.contains(pessoa2));
    }
    
    @Test
    public void testFiltrarPorGrau() {
        List<Pessoa> pessoasModerado = sistema.filtrarPorGrau("moderado");
        
        Assert.assertEquals(1, pessoasModerado.size());
        Assert.assertTrue(pessoasModerado.contains(pessoa1));
        Assert.assertFalse(pessoasModerado.contains(pessoa2));
        Assert.assertFalse(pessoasModerado.contains(pessoa3));
    }
    
    @Test
    public void testAdicionarAtendimento() {
        Atendimento atendimento = new Atendimento(LocalDate.now(), "Fisioterapia", "Dr. Silva");
        pessoa1.adicionarAtendimento(atendimento);
        
        Assert.assertEquals(1, pessoa1.getAtendimentos().size());
        Assert.assertEquals(atendimento, pessoa1.getAtendimentos().get(0));
    }
    
    @Test
    public void testGerarRelatorioSemAtendimentos() {
        String relatorio = sistema.gerarRelatorioAtendimentos(pessoa1);
        
        Assert.assertTrue(relatorio.contains("João Silva"));
        Assert.assertTrue(relatorio.contains("Física"));
        Assert.assertTrue(relatorio.contains("moderado"));
        Assert.assertTrue(relatorio.contains("Nenhum atendimento registrado"));
    }
    
    @Test
    public void testGerarRelatorioComAtendimentos() {
        Atendimento atendimento1 = new Atendimento(LocalDate.of(2025, 4, 10), "Fisioterapia", "Dr. Silva");
        Atendimento atendimento2 = new Atendimento(LocalDate.of(2025, 4, 15), "Terapia Ocupacional", "Dra. Santos");
        
        pessoa2.adicionarAtendimento(atendimento1);
        pessoa2.adicionarAtendimento(atendimento2);
        
        String relatorio = sistema.gerarRelatorioAtendimentos(pessoa2);
        
        Assert.assertTrue(relatorio.contains("Maria Oliveira"));
        Assert.assertTrue(relatorio.contains("Visual"));
        Assert.assertTrue(relatorio.contains("leve"));
        Assert.assertTrue(relatorio.contains("Fisioterapia"));
        Assert.assertTrue(relatorio.contains("Dr. Silva"));
        Assert.assertTrue(relatorio.contains("Terapia Ocupacional"));
        Assert.assertTrue(relatorio.contains("Dra. Santos"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGerarRelatorioPessoaNaoCadastrada() {
        Pessoa pessoaNaoCadastrada = new Pessoa("Não Cadastrado", 50, "Auditiva", "severo", "Rua X");
        sistema.gerarRelatorioAtendimentos(pessoaNaoCadastrada);
    }
}
