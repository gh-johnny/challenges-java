package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sistema {
    private List<Pessoa> pessoas;

    public Sistema() {
        this.pessoas = new ArrayList<>();
    }

    public void cadastrarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public List<Pessoa> listarPessoas() {
        return pessoas;
    }

    public List<Pessoa> filtrarPorTipoDeficiencia(String tipoDeficiencia) {
        return pessoas.stream()
                .filter(p -> p.getTipoDeficiencia().equalsIgnoreCase(tipoDeficiencia))
                .collect(Collectors.toList());
    }

    public List<Pessoa> filtrarPorGrau(String grau) {
        return pessoas.stream()
                .filter(p -> p.getGrau().equalsIgnoreCase(grau))
                .collect(Collectors.toList());
    }

    public String gerarRelatorioAtendimentos(Pessoa pessoa) {
        if (!pessoas.contains(pessoa)) {
            throw new IllegalArgumentException("Pessoa não cadastrada no sistema");
        }

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Atendimentos\n");
        relatorio.append("Nome: ").append(pessoa.getNome()).append("\n");
        relatorio.append("Deficiência: ").append(pessoa.getTipoDeficiencia())
                 .append(" (").append(pessoa.getGrau()).append(")\n\n");
        
        List<Atendimento> atendimentos = pessoa.getAtendimentos();
        
        if (atendimentos.isEmpty()) {
            relatorio.append("Nenhum atendimento registrado.");
        } else {
            relatorio.append("Atendimentos:\n");
            for (int i = 0; i < atendimentos.size(); i++) {
                relatorio.append(i + 1).append(". ").append(atendimentos.get(i)).append("\n");
            }
        }
        
        return relatorio.toString();
    }

    public int getTotalPessoas() {
        return pessoas.size();
    }
}
