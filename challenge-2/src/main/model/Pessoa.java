package main.model;

import java.util.ArrayList;
import java.util.List;

import main.model.interfaces.IPessoa;

public class Pessoa implements IPessoa {
    private String nome;
    private int idade;
    private String tipoDeficiencia;
    private String grau; // leve, moderado, severo
    private String endereco;
    private List<Atendimento> atendimentos;

    public Pessoa(String nome, int idade, String tipoDeficiencia, String grau, String endereco) {
        validarGrau(grau);
        
        this.nome = nome;
        this.idade = idade;
        this.tipoDeficiencia = tipoDeficiencia;
        this.grau = grau;
        this.endereco = endereco;
        this.atendimentos = new ArrayList<>();
    }

    private void validarGrau(String grau) {
        if (!grau.equalsIgnoreCase("leve") && 
            !grau.equalsIgnoreCase("moderado") && 
            !grau.equalsIgnoreCase("severo")) {
            throw new IllegalArgumentException("Grau deve ser: leve, moderado ou severo");
        }
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getTipoDeficiencia() {
        return tipoDeficiencia;
    }

    public String getGrau() {
        return grau;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void adicionarAtendimento(Atendimento atendimento) {
        atendimentos.add(atendimento);
    }

    @Override
    public String toString() {
        return nome + " (" + idade + " anos) - " + tipoDeficiencia + " - Grau: " + grau;
    }
}
