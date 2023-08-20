package br.com.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bank.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;


@ExtendWith(MockitoExtension.class)
class SistemaBancarioTest {

@Mock
private Bacen bacen;

private SistemaBancario sistemaBancarioFake;
private SistemaBancario sistemaBancarioStub;

@InjectMocks
private SistemaBancario sistemaBancario;



@BeforeEach
public void setUp(){
        sistemaBancarioFake = new SistemaBancario(new BacenFake());
        sistemaBancarioStub = new SistemaBancario(new BacenStub());
        
}

@Test
public void deveRetornarId_quandoBancoCadastradoFake(){

    long id = sistemaBancarioFake.registrarBanco(new Banco("Bradesco"));

    assertEquals(1, id);

}

@Test
public void deveRetornarId_quandoBancoCadastradoStub(){

    long id = sistemaBancarioStub.registrarBanco(new Banco("Bradesco"));

    assertEquals(1, id);

}

//fazendo teste com mock

@Test
public void deveRetornarId_quandoBancoCadastradoMock(){
    Banco bb = new Banco("BB");

   
    Mockito.doReturn(1L).when(bacen).cadastrarBanco(bb); 
    Long id = sistemaBancario.registrarBanco(bb);
  
    verify(bacen, times(1)).cadastrarBanco(bb);
    assertEquals(1L, id);


}

@Test
public void deveRetornarErro_quandoCadastroBacenNaoForRealizado(){

    Banco bb = new Banco("BB");

    Mockito.doThrow(BancoNaoCadastradoException.class)
            .when(bacen).cadastrarBanco(bb);

    assertThrows(BancoNaoCadastradoException.class, () -> sistemaBancario.registrarBanco(bb));


}



}