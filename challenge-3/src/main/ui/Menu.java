package main.ui;

import main.model.Pedagio;
import main.model.Sistema;
import main.model.Veiculo;

import java.util.Scanner;

import main.model.interfaces.IMenu;

public class Menu implements IMenu {
    private Sistema sistema;
    private Scanner scanner;
    
    public Menu(Sistema sistema) {
        this.sistema = sistema;
        this.scanner = new Scanner(System.in);
    }
    
    public void exibirMenu() {
        int opcao = 0;
        
        do {
            System.out.println("\n=== SISTEMA DE CONTROLE DE PEDÁGIOS ===");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Cadastrar Pedágio");
            System.out.println("3. Registrar Passagem");
            System.out.println("4. Relatório de Arrecadação");
            System.out.println("5. Relatório de Veículos por Pedágio");
            System.out.println("6. Listar Veículos");
            System.out.println("7. Listar Pedágios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1:
                        cadastrarVeiculo();
                        break;
                    case 2:
                        cadastrarPedagio();
                        break;
                    case 3:
                        registrarPassagem();
                        break;
                    case 4:
                        System.out.println(sistema.gerarRelatorioArrecadacao());
                        break;
                    case 5:
                        System.out.println(sistema.gerarRelatorioVeiculosPorPedagio());
                        break;
                    case 6:
                        listarVeiculos();
                        break;
                    case 7:
                        listarPedagios();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
    
    private void cadastrarVeiculo() {
        System.out.println("\n=== CADASTRO DE VEÍCULO ===");
        System.out.print("Placa: ");
        String placa = scanner.nextLine().toUpperCase();
        
        System.out.print("Tipo (carro, moto, caminhao): ");
        String tipo = scanner.nextLine().toLowerCase();
        
        int eixos = 0;
        if (tipo.equals("caminhao")) {
            System.out.print("Número de eixos: ");
            try {
                eixos = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido, usando 2 eixos como padrão.");
                eixos = 2;
            }
        }
        
        sistema.cadastrarVeiculo(new Veiculo(placa, tipo, eixos));
        System.out.println("Veículo cadastrado com sucesso!");
    }
    
    private void cadastrarPedagio() {
        System.out.println("\n=== CADASTRO DE PEDÁGIO ===");
        System.out.print("Localização: ");
        String localizacao = scanner.nextLine();
        
        System.out.print("Tarifa base (R$): ");
        double tarifaBase = 0;
        try {
            tarifaBase = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, usando R$ 10,00 como padrão.");
            tarifaBase = 10.0;
        }
        
        sistema.cadastrarPedagio(new Pedagio(localizacao, tarifaBase));
        System.out.println("Pedágio cadastrado com sucesso!");
    }
    
    private void registrarPassagem() {
        System.out.println("\n=== REGISTRO DE PASSAGEM ===");
        
        // Listar veículos
        listarVeiculos();
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine().toUpperCase();
        
        // Listar pedágios
        listarPedagios();
        System.out.print("Digite o número do pedágio: ");
        int indicePedagio = -1;
        try {
            indicePedagio = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
            return;
        }
        
        sistema.registrarPassagem(placa, indicePedagio);
        System.out.println("Passagem registrada com sucesso!");
    }
    
    private void listarVeiculos() {
        System.out.println("\n=== VEÍCULOS CADASTRADOS ===");
        if (sistema.getVeiculos().isEmpty()) {
            System.out.println("Não há veículos cadastrados.");
            return;
        }
        
        for (Veiculo veiculo : sistema.getVeiculos()) {
            System.out.println(veiculo);
        }
    }
    
    private void listarPedagios() {
        System.out.println("\n=== PEDÁGIOS CADASTRADOS ===");
        if (sistema.getPedagios().isEmpty()) {
            System.out.println("Não há pedágios cadastrados.");
            return;
        }
        
        for (int i = 0; i < sistema.getPedagios().size(); i++) {
            System.out.println((i + 1) + ". " + sistema.getPedagios().get(i));
        }
    }
}
