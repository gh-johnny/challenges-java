package main;

import main.model.Pedagio;
import main.model.Sistema;
import main.model.Veiculo;
import main.ui.Menu;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        
        // Inicialização com alguns dados de exemplo
        sistema.cadastrarPedagio(new Pedagio("Bandeirantes km 32", 10.50));
        sistema.cadastrarPedagio(new Pedagio("Anhanguera km 26", 9.80));
        sistema.cadastrarPedagio(new Pedagio("Castello Branco km 45", 11.20));
        
        sistema.cadastrarVeiculo(new Veiculo("ABC1234", "carro", 0));
        sistema.cadastrarVeiculo(new Veiculo("XYZ5678", "moto", 0));
        sistema.cadastrarVeiculo(new Veiculo("DEF9012", "caminhao", 3));
        sistema.cadastrarVeiculo(new Veiculo("GHI3456", "caminhao", 5));
        
        Menu menu = new Menu(sistema);
        menu.exibirMenu();
    }
}
