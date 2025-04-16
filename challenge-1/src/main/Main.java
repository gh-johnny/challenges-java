package main;

import main.model.Campeonato;
import main.ui.Menu;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o Sistema de Gestão de Campeonato de Futebol Feminino");
        
        // Criar o campeonato
        Campeonato campeonato = new Campeonato("Campeonato Brasileiro de Futebol Feminino");
        
        // Iniciar o menu
        Menu menu = new Menu(campeonato);
        menu.exibirMenuPrincipal();
    }
}
