package com.ordermanagermvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ordermanagermvc.business.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.repository.ClienteRepository;

public class ClienteServiceImplTest {

    private ClienteRepository repository;
    private ClienteServiceImpl service;


    private final String CPF_VALIDO = "52998224725";
    private final String NOME_VALIDO = "João da Silva";
    private final String TELEFONE_VALIDO = "11987654321";
    private final String EMAIL_VALIDO = "joao@email.com";

    @BeforeEach
    void setUp() {

        repository = mock(ClienteRepository.class);
        service = new ClienteServiceImpl(repository);
    }

    @Test
    void deveCriarClienteComSucessoQuandoDadosForemValidos() {

        String cpfMatematicamenteValido = "52998224725";
        when(repository.buscarPorCpf(cpfMatematicamenteValido)).thenReturn(Optional.empty());

        service.criarCliente(cpfMatematicamenteValido, NOME_VALIDO, TELEFONE_VALIDO, EMAIL_VALIDO);

        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);
        verify(repository, times(1)).salvar(clienteCaptor.capture());

        Cliente clienteSalvo = clienteCaptor.getValue();
        assertEquals(cpfMatematicamenteValido, clienteSalvo.getCpf());
        assertEquals(NOME_VALIDO, clienteSalvo.getNome());
        assertEquals(TELEFONE_VALIDO, clienteSalvo.getTelefone());
        assertEquals(EMAIL_VALIDO, clienteSalvo.getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExistir() {
        String cpfMatematicamenteValido = "52998224725";
        Cliente clienteExistente = new Cliente(cpfMatematicamenteValido, NOME_VALIDO, TELEFONE_VALIDO, EMAIL_VALIDO);

        when(repository.buscarPorCpf(cpfMatematicamenteValido)).thenReturn(Optional.of(clienteExistente));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.criarCliente(cpfMatematicamenteValido, NOME_VALIDO, TELEFONE_VALIDO, EMAIL_VALIDO);
        });

        assertEquals("Já existe cliente cadastrado com este CPF.", exception.getMessage());
        verify(repository, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoCpfForInvalido() {
        String cpfComDigitosRepetidos = "11111111111";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.criarCliente(cpfComDigitosRepetidos, NOME_VALIDO, TELEFONE_VALIDO, EMAIL_VALIDO);
        });

        assertEquals("CPF invalido (digitos repetidos).", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForMuitoCurto() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.criarCliente("52998224725", "Ze", TELEFONE_VALIDO, EMAIL_VALIDO);
        });

        assertEquals("Nome deve possuir pelo menos 3 caracteres.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoTelefoneForInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.criarCliente("52998224725", NOME_VALIDO, "123", EMAIL_VALIDO);
        });

        assertEquals("Telefone deve possuir 10 ou 11 dígitos.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoEmailForInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.criarCliente("52998224725", NOME_VALIDO, TELEFONE_VALIDO, "email-invalido");
        });

        assertEquals("E-mail inválido.", exception.getMessage());
    }

    @Test
    void deveObterTodosOsClientes() {
        List<Cliente> clientesMock = Arrays.asList(
                new Cliente("111", "Cliente 1", "11999999999", "a@a.com"),
                new Cliente("222", "Cliente 2", "11888888888", "b@b.com")
        );
        when(repository.listarTodos()).thenReturn(clientesMock);

        List<Cliente> resultado = service.obterTodosOsClientes();

        assertEquals(2, resultado.size());
        verify(repository, times(1)).listarTodos();
    }

    @Test
    void deveBuscarPorCpfComSucesso() {
        String cpfBusca = "52998224725";
        Cliente cliente = new Cliente(cpfBusca, NOME_VALIDO, TELEFONE_VALIDO, EMAIL_VALIDO);

        when(repository.buscarPorCpf(cpfBusca)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = service.buscarPorCpf(cpfBusca);

        assertTrue(resultado.isPresent());
        assertEquals(cpfBusca, resultado.get().getCpf());
    }

    @Test
    void deveRetornarVazioAoBuscarCpfNuloOuEmBranco() {
        Optional<Cliente> resultado = service.buscarPorCpf("   ");

        assertFalse(resultado.isPresent());
        verify(repository, never()).buscarPorCpf(any());
    }
}