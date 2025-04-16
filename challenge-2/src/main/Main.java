package main;

import main.model.Sistema;
import main.ui.Menu;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando o Sistema de Cadastro e Acompanhamento de Pessoas com Necessidades Especiais");
        
        Sistema sistema = new Sistema();
        Menu menu = new Menu(sistema);
        menu.exibirMenuPrincipal();
    }
}
