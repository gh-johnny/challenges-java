package main.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import main.model.interfaces.IAtendimento;

public class Atendimento implements IAtendimento {
    private LocalDate data;
    private String tipo;
    private String profissionalResponsavel;

    public Atendimento(LocalDate data, String tipo, String profissionalResponsavel) {
        this.data = data;
        this.tipo = tipo;
        this.profissionalResponsavel = profissionalResponsavel;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public String getProfissionalResponsavel() {
        return profissionalResponsavel;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter) + " - " + tipo + " - Prof.: " + profissionalResponsavel;
    }
}
