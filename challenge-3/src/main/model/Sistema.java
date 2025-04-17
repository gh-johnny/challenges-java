package main.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.interfaces.ISistema;

public class Sistema implements ISistema {
    private List<Veiculo> veiculos;
    private List<Pedagio> pedagios;
    private List<Passagem> passagens;
    
    public Sistema() {
        this.veiculos = new ArrayList<>();
        this.pedagios = new ArrayList<>();
        this.passagens = new ArrayList<>();
    }
    
    public void cadastrarVeiculo(Veiculo veiculo) {
        // Verifica se a placa já existe
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(veiculo.getPlaca())) {
                return; // Placa já existe, não cadastra
            }
        }
        veiculos.add(veiculo);
    }
    
    public void cadastrarPedagio(Pedagio pedagio) {
        pedagios.add(pedagio);
    }
    
    public Veiculo buscarVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }
    
    public Pedagio buscarPedagio(int indice) {
        if (indice >= 0 && indice < pedagios.size()) {
            return pedagios.get(indice);
        }
        return null;
    }
    
    public double calcularValorPedagio(Veiculo veiculo, Pedagio pedagio) {
        double tarifaBase = pedagio.getTarifaBase();
        
        switch (veiculo.getTipo()) {
            case "moto":
                return tarifaBase * 0.5;
            case "caminhao":
                return tarifaBase * veiculo.getEixos();
            case "carro":
            default:
                return tarifaBase;
        }
    }
    
    public void registrarPassagem(String placa, int indicePedagio) {
        Veiculo veiculo = buscarVeiculo(placa);
        Pedagio pedagio = buscarPedagio(indicePedagio);
        
        if (veiculo != null && pedagio != null) {
            double valorCobrado = calcularValorPedagio(veiculo, pedagio);
            Passagem passagem = new Passagem(veiculo, pedagio, valorCobrado);
            passagens.add(passagem);
            pedagio.registrarPassagem(valorCobrado);
        }
    }
    
    public String gerarRelatorioArrecadacao() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO DE ARRECADAÇÃO ===\n");
        
        for (Pedagio pedagio : pedagios) {
            relatorio.append("\nPedágio: ").append(pedagio.getLocalizacao())
                    .append("\nPassagens: ").append(pedagio.getPassagens())
                    .append("\nValor arrecadado: R$ ").append(String.format("%.2f", pedagio.getValorArrecadado()))
                    .append("\n-------------------------------");
        }
        
        return relatorio.toString();
    }
    
    public String gerarRelatorioVeiculosPorPedagio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIO DE VEÍCULOS POR PEDÁGIO ===\n");
        
        // Map para contar veículos por pedágio
        Map<String, Map<String, Integer>> contagem = new HashMap<>();
        
        for (Pedagio pedagio : pedagios) {
            contagem.put(pedagio.getLocalizacao(), new HashMap<>());
        }
        
        for (Passagem passagem : passagens) {
            String localizacao = passagem.getPedagio().getLocalizacao();
            String tipo = passagem.getVeiculo().getTipo();
            
            Map<String, Integer> tiposVeiculo = contagem.get(localizacao);
            tiposVeiculo.put(tipo, tiposVeiculo.getOrDefault(tipo, 0) + 1);
        }
        
        for (Pedagio pedagio : pedagios) {
            String localizacao = pedagio.getLocalizacao();
            relatorio.append("\nPedágio: ").append(localizacao);
            
            Map<String, Integer> tiposVeiculo = contagem.get(localizacao);
            for (Map.Entry<String, Integer> entry : tiposVeiculo.entrySet()) {
                relatorio.append("\n  ").append(entry.getKey()).append(": ").append(entry.getValue());
            }
            
            relatorio.append("\n-------------------------------");
        }
        
        return relatorio.toString();
    }
    
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
    
    public List<Pedagio> getPedagios() {
        return pedagios;
    }
    
    public List<Passagem> getPassagens() {
        return passagens;
    }
}
