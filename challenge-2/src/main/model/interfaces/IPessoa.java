package main.model.interfaces;

import main.model.Atendimento;
import java.util.List;

public interface IPessoa {
    /**
     * Retorna o nome da pessoa
     * @return Nome da pessoa
     */
    String getNome();
    
    /**
     * Retorna a idade da pessoa
     * @return Idade da pessoa
     */
    int getIdade();
    
    /**
     * Retorna o tipo de deficiência da pessoa
     * @return Tipo de deficiência
     */
    String getTipoDeficiencia();
    
    /**
     * Retorna o grau de deficiência (leve, moderado ou severo)
     * @return Grau de deficiência
     */
    String getGrau();
    
    /**
     * Retorna o endereço da pessoa
     * @return Endereço da pessoa
     */
    String getEndereco();
    
    /**
     * Retorna a lista de atendimentos da pessoa
     * @return Lista de atendimentos
     */
    List<Atendimento> getAtendimentos();
    
    /**
     * Adiciona um atendimento ao histórico da pessoa
     * @param atendimento Atendimento a ser adicionado
     */
    void adicionarAtendimento(Atendimento atendimento);
}
