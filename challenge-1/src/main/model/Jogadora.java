package main.model;

public class Jogadora {
    private String nome;
    private int idade;
    private String posicao;
    private int numeroCamisa;

    public Jogadora(String nome, int idade, String posicao, int numeroCamisa) {
        if (idade < 15) {
            throw new IllegalArgumentException("Jogadora deve ter pelo menos 15 anos.");
        }
        if (numeroCamisa < 1 || numeroCamisa > 99) {
            throw new IllegalArgumentException("Número da camisa deve estar entre 1 e 99.");
        }
        
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.numeroCamisa = numeroCamisa;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getPosicao() {
        return posicao;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }

    @Override
    public String toString() {
        return nome + " (#" + numeroCamisa + ", " + posicao + ", " + idade + " anos)";
    }
}
