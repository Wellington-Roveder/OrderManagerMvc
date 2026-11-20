package com.ordermanagermvc;

import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.business.service.EnderecoService;
import com.ordermanagermvc.controller.EnderecoController;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EnderecoControllerTest {

    private EnderecoService enderecoService;
    private ClienteService clienteService;
    private EnderecoController controller;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        enderecoService = mock(EnderecoService.class);
        clienteService = mock(ClienteService.class);
        controller = new EnderecoController(enderecoService, clienteService);
        cliente = new Cliente("52998224725", "João da Silva", "11987654321", "joao@email.com");
    }

    @Test
    void deveCadastrarEnderecoParaCpfFormatado() {
        when(clienteService.obterTodosOsClientes()).thenReturn(List.of(cliente));

        String resultado = controller.cadastrar(
                "Rua A", "100", "São Paulo", "SP", "01001-000", "529.982.247-25");

        assertEquals("✅ Sucesso: Endereço registrado com sucesso!", resultado);
        verify(enderecoService).criarEndereco(
                "Rua A", "100", "São Paulo", "SP", "01001-000", cliente);
    }

    @Test
    void deveInformarQuandoClienteNaoForEncontrado() {
        when(clienteService.obterTodosOsClientes()).thenReturn(List.of());

        String resultado = controller.cadastrar(
                "Rua A", "100", "São Paulo", "SP", "01001-000", "529.982.247-25");

        assertEquals("Erro: Nenhum cliente encontrado com o CPF: 529.982.247-25", resultado);
        verifyNoInteractions(enderecoService);
    }

    @Test
    void deveRetornarErroDeValidacaoDoServico() {
        when(clienteService.obterTodosOsClientes()).thenReturn(List.of(cliente));
        doThrow(new IllegalArgumentException("CEP Invalido!!"))
                .when(enderecoService)
                .criarEndereco("Rua A", "100", "São Paulo", "SP", "", cliente);

        String resultado = controller.cadastrar(
                "Rua A", "100", "São Paulo", "SP", "", "52998224725");

        assertEquals("Erro de validação: CEP Invalido!!", resultado);
    }

    @Test
    void deveListarEnderecosDoCpfFormatado() {
        List<Endereco> enderecos = List.of(
                new Endereco("id-1", "Rua A", "100", "São Paulo", "SP", "01001-000", cliente));
        when(enderecoService.listarEnderecoCliente("52998224725")).thenReturn(enderecos);

        assertEquals(enderecos, controller.listar("529.982.247-25"));
        verify(enderecoService).listarEnderecoCliente("52998224725");
    }
}
