package br.com.bank.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {

    private String nome;

    private final float VALOR_CONTA_ALTA_RENDA = 10000;

    public Banco(String nome) {
        this.nome = nome;
    }

    private Map<String, Conta> contasPorCPF = new HashMap();

    public void adicionarConta(Conta conta) {
        contasPorCPF.put(conta.getCpf(), conta);
    }

    public Optional<Conta> pesquisarContaDoCliente(String cpf) {
        return Optional.ofNullable(contasPorCPF.get(cpf));
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= VALOR_CONTA_ALTA_RENDA);
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contasPorCPF.values().stream().filter(filtro).collect(Collectors.toList());
    }
    public Map<String, Conta> getContas() {
        return contasPorCPF;
    }

    public int getTotalContas() {
        return this.contasPorCPF.size();
    }



    
}
