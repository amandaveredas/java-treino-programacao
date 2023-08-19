package br.com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BancoTest {

    Banco banco;

    @BeforeEach
    void setUp() {

        banco = new Banco("Bradesco");
    }


    @Test
    public void deveAdicionarConta (){
        
        banco.adicionarConta(new Conta("12345678900"));
        banco.adicionarConta(new Conta("11111111111"));

        assertEquals(2,banco.getTotalContas());
    }

    @Test
    public void deveRetornarNulo_quandoClienteNaoExistir (){

        Optional<Conta> conta = banco.pesquisarContaDoCliente("12345678900");
        
        assertFalse(conta.isPresent());
        
    }

    @Test
    public void deveRetornarConta_quandoClienteExistir(){
        banco.adicionarConta(new Conta("12345678900"));
        
        Optional<Conta> conta = banco.pesquisarContaDoCliente("12345678900");
        
        assertTrue(conta.isPresent());
        assertEquals("12345678900" ,conta.get().getCpf());        
    }

    @Test
    public void deveRetornarListaNula_quandoNaoHouverClientesDeAltaRenda(){

        List<Conta> contas = banco.listarContasAltaRenda();

        assertEquals(0, contas.size());

    }

    @Test
    public void deveRetornarLista_quandoHouverClientesAltaRenda(){
        banco.adicionarConta(new Conta("123",12000));
        banco.adicionarConta(new Conta("234",14000));

        List<Conta> contas = banco.listarContasAltaRenda();

        assertEquals(2, contas.size());
        assertEquals("123", contas.get(0).getCpf());
    }

}
