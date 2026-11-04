package com.ordermanagermvc;


import com.ordermanagermvc.controller.BonecoController;
import com.ordermanagermvc.controller.ClienteController;
import com.ordermanagermvc.controller.EnderecoController;
import com.ordermanagermvc.controller.PedidoController;
import com.ordermanagermvc.view.ConsoleView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleViewTest {

    @Mock
    private  PedidoController pedidoController;
    @Mock
    private  ClienteController clienteController;
    @Mock
    private  EnderecoController enderecoController;
    @Mock
    private  BonecoController bonecoController;

    private ConsoleView consoleView;

    @BeforeEach
    void setUp(){
        consoleView = new ConsoleView(pedidoController,clienteController,enderecoController,bonecoController);
    }

    @Test
    @DisplayName("Deve lançar NoSuchElementException quando a entrada for apenas vazia")
    void deveLancarNoSuchElementExceptionQuandoEntradaForApenasVazia(){
        InputStream inOriginal = System.in;

        try {
            String entradaInvalida = "\n";

            System.setIn(new ByteArrayInputStream(entradaInvalida.getBytes()));

            consoleView = new ConsoleView(pedidoController, clienteController, enderecoController, bonecoController);

            assertThrows(NoSuchElementException.class, () -> {
                consoleView.lerEntradaValidaString("Nome");
            });

        } finally {
            System.setIn(inOriginal);
        }

    }

    @Test
    @DisplayName("Deve retornar entrada quando ler corretamente")
    void deveRetornarEntradaQuandoLerCorretamente(){
        InputStream inOriginal = System.in;

        try {
            String entradaValida = "joao";

            System.setIn(new ByteArrayInputStream(entradaValida.getBytes()));

            consoleView = new ConsoleView(pedidoController, clienteController, enderecoController, bonecoController);

            String saida = consoleView.lerEntradaValidaString("Nome");
            assertEquals("joao", saida.trim());

        } finally {
            System.setIn(inOriginal);
        }

    }









}
