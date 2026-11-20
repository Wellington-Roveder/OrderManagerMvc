package com.ordermanagermvc;

import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Endereco;
import com.ordermanagermvc.model.repository.MemoryEnderecoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryEnderecoRepositoryTest {

    @Test
    void deveSalvarListarEFiltrarEnderecosPorCpf() {
        MemoryEnderecoRepository repository = new MemoryEnderecoRepository();
        Cliente cliente = new Cliente("529.982.247-25", "João da Silva", "11987654321", "joao@email.com");
        Cliente outroCliente = new Cliente("11111111111", "Maria", "11999999999", "maria@email.com");
        Endereco enderecoCliente = new Endereco(
                "id-1", "Rua A", "100", "São Paulo", "SP", "01001-000", cliente);
        Endereco enderecoOutroCliente = new Endereco(
                "id-2", "Rua B", "200", "Campinas", "SP", "13010-000", outroCliente);

        repository.salvar(enderecoCliente);
        repository.salvar(enderecoOutroCliente);

        assertEquals(List.of(enderecoCliente, enderecoOutroCliente), repository.listarTodos());
        assertEquals(List.of(enderecoCliente), repository.buscarPorCpfCliente("52998224725"));
    }

    @Test
    void deveRetornarCopiaAoListarTodos() {
        MemoryEnderecoRepository repository = new MemoryEnderecoRepository();
        Endereco endereco = new Endereco(
                "id-1", "Rua A", "100", "São Paulo", "SP", "01001-000", null);
        repository.salvar(endereco);

        List<Endereco> resultado = repository.listarTodos();
        resultado.clear();

        assertEquals(List.of(endereco), repository.listarTodos());
    }
}
