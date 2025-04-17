package main.model.interfaces;

import main.model.Pedagio;
import main.model.Veiculo;

import java.time.LocalDateTime;

public interface IPassagem {
    Veiculo getVeiculo();
    Pedagio getPedagio();
    LocalDateTime getDataHora();
    double getValorCobrado();
}
