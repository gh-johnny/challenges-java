package main.model.interfaces;

import java.time.LocalDate;

public interface IAtendimento {
    /**
     * Retorna a data do atendimento
     * @return Data do atendimento
     */
    LocalDate getData();
    
    /**
     * Retorna o tipo de atendimento
     * @return Tipo de atendimento
     */
    String getTipo();
    
    /**
     * Retorna o nome do profissional responsável pelo atendimento
     * @return Nome do profissional
     */
    String getProfissionalResponsavel();
}
