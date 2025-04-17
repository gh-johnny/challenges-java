package main.ui;

import main.model.Campeonato;
import main.model.Jogadora;
import main.model.Partida;
import main.model.Time;

import java.util.List;
import java.util.Scanner;

import main.ui.IMenu;

public class Menu implements IMenu {
    private Scanner scanner;
    private Campeonato campeonato;

    public Menu(Campeonato campeonato) {
        this.scanner = new Scanner(System.in);
        this.campeonato = campeonato;
    }

    public void exibirMenuPrincipal() {
        int opcao = 0;
        
        do {
            System.out.println("\n===== GERENCIADOR DE CAMPEONATO DE FUTEBOL FEMININO =====");
            System.out.println("1. Cadastrar Time");
            System.out.println("2. Cadastrar Jogadora");
            System.out.println("3. Registrar Partida");
            System.out.println("4. Registrar Resultado de Partida");
            System.out.println("5. Ver Tabela de Classificação");
            System.out.println("6. Listar Times");
            System.out.println("7. Listar Jogadoras de um Time");
            System.out.println("8. Listar Partidas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1:
                        cadastrarTime();
                        break;
                    case 2:
                        cadastrarJogadora();
                        break;
                    case 3:
                        registrarPartida();
                        break;
                    case 4:
                        registrarResultadoPartida();
                        break;
                    case 5:
                        System.out.println(campeonato.getTabelaClassificacao());
                        break;
                    case 6:
                        listarTimes();
                        break;
                    case 7:
                        listarJogadoras();
                        break;
                    case 8:
                        listarPartidas();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                opcao = -1;
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }

    private void cadastrarTime() {
        System.out.println("\n=== CADASTRAR TIME ===");
        System.out.print("Nome do time: ");
        String nome = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        
        try {
            Time time = new Time(nome, cidade);
            campeonato.adicionarTime(time);
            System.out.println("Time cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar time: " + e.getMessage());
        }
    }

    private void cadastrarJogadora() {
        System.out.println("\n=== CADASTRAR JOGADORA ===");
        
        // Verificar se existem times cadastrados
        if (campeonato.getTimes().isEmpty()) {
            System.out.println("Não há times cadastrados. Cadastre um time primeiro.");
            return;
        }
        
        // Listar times disponíveis
        System.out.println("Times disponíveis:");
        for (int i = 0; i < campeonato.getTimes().size(); i++) {
            System.out.println((i + 1) + ". " + campeonato.getTimes().get(i).getNome());
        }
        
        System.out.print("Selecione o número do time: ");
        try {
            int indiceTime = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (indiceTime < 0 || indiceTime >= campeonato.getTimes().size()) {
                System.out.println("Número de time inválido!");
                return;
            }
            
            Time time = campeonato.getTimes().get(indiceTime);
            
            System.out.print("Nome da jogadora: ");
            String nome = scanner.nextLine();
            
            System.out.print("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Posição: ");
            String posicao = scanner.nextLine();
            
            System.out.print("Número da camisa: ");
            int numeroCamisa = Integer.parseInt(scanner.nextLine());
            
            Jogadora jogadora = new Jogadora(nome, idade, posicao, numeroCamisa);
            time.adicionarJogadora(jogadora);
            
            System.out.println("Jogadora cadastrada com sucesso!");
            
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar jogadora: " + e.getMessage());
        }
    }

    private void registrarPartida() {
        System.out.println("\n=== REGISTRAR PARTIDA ===");
        
        // Verificar se existem pelo menos dois times cadastrados
        if (campeonato.getTimes().size() < 2) {
            System.out.println("É necessário ter pelo menos dois times cadastrados.");
            return;
        }
        
        // Listar times disponíveis
        System.out.println("Times disponíveis:");
        for (int i = 0; i < campeonato.getTimes().size(); i++) {
            System.out.println((i + 1) + ". " + campeonato.getTimes().get(i).getNome());
        }
        
        try {
            System.out.print("Selecione o número do time da casa: ");
            int indiceTimeCasa = Integer.parseInt(scanner.nextLine()) - 1;
            
            System.out.print("Selecione o número do time visitante: ");
            int indiceTimeVisitante = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (indiceTimeCasa < 0 || indiceTimeCasa >= campeonato.getTimes().size() ||
                indiceTimeVisitante < 0 || indiceTimeVisitante >= campeonato.getTimes().size()) {
                System.out.println("Número de time inválido!");
                return;
            }
            
            if (indiceTimeCasa == indiceTimeVisitante) {
                System.out.println("Os times da partida não podem ser iguais!");
                return;
            }
            
            Time timeCasa = campeonato.getTimes().get(indiceTimeCasa);
            Time timeVisitante = campeonato.getTimes().get(indiceTimeVisitante);
            
            Partida partida = new Partida(timeCasa, timeVisitante);
            campeonato.adicionarPartida(partida);
            
            System.out.println("Partida registrada com sucesso!");
            
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido.");
        } catch (Exception e) {
            System.out.println("Erro ao registrar partida: " + e.getMessage());
        }
    }

    private void registrarResultadoPartida() {
        System.out.println("\n=== REGISTRAR RESULTADO DE PARTIDA ===");
        
        // Verificar se existem partidas cadastradas
        List<Partida> partidas = campeonato.getPartidas();
        if (partidas.isEmpty()) {
            System.out.println("Não há partidas cadastradas.");
            return;
        }
        
        // Listar partidas não finalizadas
        System.out.println("Partidas disponíveis:");
        int countPartidasNaoFinalizadas = 0;
        
        for (int i = 0; i < partidas.size(); i++) {
            Partida partida = partidas.get(i);
            if (!partida.isFinalizada()) {
                System.out.println((i + 1) + ". " + partida);
                countPartidasNaoFinalizadas++;
            }
        }
        
        if (countPartidasNaoFinalizadas == 0) {
            System.out.println("Todas as partidas já foram finalizadas.");
            return;
        }
        
        try {
            System.out.print("Selecione o número da partida: ");
            int indicePartida = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (indicePartida < 0 || indicePartida >= partidas.size()) {
                System.out.println("Número de partida inválido!");
                return;
            }
            
            Partida partida = partidas.get(indicePartida);
            
            if (partida.isFinalizada()) {
                System.out.println("Esta partida já foi finalizada!");
                return;
            }
            
            System.out.println("Partida: " + partida.getTimeCasa().getNome() + " vs " + partida.getTimeVisitante().getNome());
            
            System.out.print("Gols do time " + partida.getTimeCasa().getNome() + ": ");
            int golsTimeCasa = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Gols do time " + partida.getTimeVisitante().getNome() + ": ");
            int golsTimeVisitante = Integer.parseInt(scanner.nextLine());
            
            partida.finalizarPartida(golsTimeCasa, golsTimeVisitante);
            
            System.out.println("Resultado registrado com sucesso!");
            
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido.");
        } catch (Exception e) {
            System.out.println("Erro ao registrar resultado: " + e.getMessage());
        }
    }

    private void listarTimes() {
        System.out.println("\n=== TIMES CADASTRADOS ===");
        
        List<Time> times = campeonato.getTimes();
        if (times.isEmpty()) {
            System.out.println("Não há times cadastrados.");
            return;
        }
        
        for (int i = 0; i < times.size(); i++) {
            Time time = times.get(i);
            System.out.println((i + 1) + ". " + time.getNome() + " (" + time.getCidade() + ")");
        }
    }

    private void listarJogadoras() {
        System.out.println("\n=== LISTAR JOGADORAS DE UM TIME ===");
        
        List<Time> times = campeonato.getTimes();
        if (times.isEmpty()) {
            System.out.println("Não há times cadastrados.");
            return;
        }
        
        // Listar times disponíveis
        System.out.println("Times disponíveis:");
        for (int i = 0; i < times.size(); i++) {
            System.out.println((i + 1) + ". " + times.get(i).getNome());
        }
        
        try {
            System.out.print("Selecione o número do time: ");
            int indiceTime = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (indiceTime < 0 || indiceTime >= times.size()) {
                System.out.println("Número de time inválido!");
                return;
            }
            
            Time time = times.get(indiceTime);
            List<Jogadora> jogadoras = time.getJogadoras();
            
            System.out.println("\nJogadoras do time " + time.getNome() + ":");
            
            if (jogadoras.isEmpty()) {
                System.out.println("Este time não possui jogadoras cadastradas.");
                return;
            }
            
            for (int i = 0; i < jogadoras.size(); i++) {
                Jogadora jogadora = jogadoras.get(i);
                System.out.println((i + 1) + ". " + jogadora);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido.");
        } catch (Exception e) {
            System.out.println("Erro ao listar jogadoras: " + e.getMessage());
        }
    }

    private void listarPartidas() {
        System.out.println("\n=== PARTIDAS CADASTRADAS ===");
        
        List<Partida> partidas = campeonato.getPartidas();
        if (partidas.isEmpty()) {
            System.out.println("Não há partidas cadastradas.");
            return;
        }
        
        for (int i = 0; i < partidas.size(); i++) {
            Partida partida = partidas.get(i);
            System.out.println((i + 1) + ". " + partida);
        }
    }
}
