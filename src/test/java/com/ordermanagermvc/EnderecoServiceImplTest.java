package com.ordermanagermvc;

import com.ordermanagermvc.business.impl.EnderecoServiceImpl;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Endereco;
import com.ordermanagermvc.model.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EnderecoServiceImplTest {

    private EnderecoRepository repository;
    private EnderecoServiceImpl service;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        repository = mock(EnderecoRepository.class);
        service = new EnderecoServiceImpl(repository);
        cliente = new Cliente("52998224725", "João da Silva", "11987654321", "joao@email.com");
    }

    @Test
    void deveCriarEnderecoAssociarAoClienteESalvarNoRepositorio() {
        service.criarEndereco("Rua A", "100", "São Paulo", "SP", "01001-000", cliente);

        ArgumentCaptor<Endereco> captor = ArgumentCaptor.forClass(Endereco.class);
        verify(repository).salvar(captor.capture());

        Endereco endereco = captor.getValue();
        assertNotNull(endereco.getUuId());
        assertEquals("Rua A", endereco.getRua());
        assertEquals("100", endereco.getNumero());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getEstado());
        assertEquals("01001-000", endereco.getCep());
        assertEquals(cliente, endereco.getCliente());
        assertEquals(List.of(endereco), cliente.getEnderecos());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveRejeitarRuaVazia(String rua) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.criarEndereco(rua, "100", "São Paulo", "SP", "01001-000", cliente));

        assertEquals("Rua nao pode estar vazia!!", exception.getMessage());
        verifyNoInteractions(repository);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveRejeitarNumeroVazio(String numero) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.criarEndereco("Rua A", numero, "São Paulo", "SP", "01001-000", cliente));

        assertEquals("numero nao pode estar vazio!!", exception.getMessage());
        verifyNoInteractions(repository);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveRejeitarCidadeVazia(String cidade) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.criarEndereco("Rua A", "100", cidade, "SP", "01001-000", cliente));

        assertEquals("cidade nao pode estar vazia!!", exception.getMessage());
        verifyNoInteractions(repository);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveRejeitarEstadoVazio(String estado) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.criarEndereco("Rua A", "100", "São Paulo", estado, "01001-000", cliente));

        assertEquals("Estado nao pode estar vazio!!", exception.getMessage());
        verifyNoInteractions(repository);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void deveRejeitarCepVazio(String cep) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.criarEndereco("Rua A", "100", "São Paulo", "SP", cep, cliente));

        assertEquals("CEP Invalido!!", exception.getMessage());
        verifyNoInteractions(repository);
    }

    @Test
    void deveRejeitarEnderecoSemCliente() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.criarEndereco("Rua A", "100", "São Paulo", "SP", "01001-000", null));

        assertEquals("O endereço deve estar associado a um cliente válido.", exception.getMessage());
        verifyNoInteractions(repository);
    }

    @Test
    void deveListarTodosOsEnderecos() {
        List<Endereco> enderecos = List.of(
                new Endereco("id-1", "Rua A", "100", "São Paulo", "SP", "01001-000", cliente));
        when(repository.listarTodos()).thenReturn(enderecos);

        assertEquals(enderecos, service.listarTodos());
        verify(repository).listarTodos();
    }

    @Test
    void deveListarEnderecosDoClienteComCpfFormatado() {
        List<Endereco> enderecos = List.of(
                new Endereco("id-1", "Rua A", "100", "São Paulo", "SP", "01001-000", cliente));
        when(repository.buscarPorCpfCliente("52998224725")).thenReturn(enderecos);

        assertEquals(enderecos, service.listarEnderecoCliente("529.982.247-25"));
        verify(repository).buscarPorCpfCliente("52998224725");
    }
}
