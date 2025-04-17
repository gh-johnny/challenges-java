package main.model.interfaces;

import main.model.Pessoa;
import java.util.List;

public interface ISistema {
    /**
     * Cadastra uma nova pessoa no sistema
     * @param pessoa Pessoa a ser cadastrada
     */
    void cadastrarPessoa(Pessoa pessoa);
    
    /**
     * Lista todas as pessoas cadastradas no sistema
     * @return Lista de pessoas
     */
    List<Pessoa> listarPessoas();
    
    /**
     * Filtra pessoas por tipo de deficiência
     * @param tipoDeficiencia Tipo de deficiência para filtragem
     * @return Lista de pessoas com o tipo de deficiência especificado
     */
    List<Pessoa> filtrarPorTipoDeficiencia(String tipoDeficiencia);
    
    /**
     * Filtra pessoas por grau de deficiência
     * @param grau Grau de deficiência para filtragem (leve, moderado, severo)
     * @return Lista de pessoas com o grau de deficiência especificado
     */
    List<Pessoa> filtrarPorGrau(String grau);
    
    /**
     * Gera um relatório de atendimentos para uma pessoa específica
     * @param pessoa Pessoa para gerar o relatório
     * @return Relatório formatado como String
     * @throws IllegalArgumentException Se a pessoa não estiver cadastrada no sistema
     */
    String gerarRelatorioAtendimentos(Pessoa pessoa);
    
    /**
     * Retorna o número total de pessoas cadastradas no sistema
     * @return Total de pessoas
     */
    int getTotalPessoas();
}
