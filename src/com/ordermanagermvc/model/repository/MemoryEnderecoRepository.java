package com.ordermanagermvc.model.repository;

import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryEnderecoRepository implements  EnderecoRepository{
    private final List<Endereco> bancoEmMemoria = new ArrayList<>();

    @Override
    public void salvar(Endereco endereco) {
        bancoEmMemoria.add(endereco);
        System.out.println("[DB] Endereço gravado com sucesso na memória.");
    }

    @Override
    public List<Endereco> listarTodos() {
        return new ArrayList<>(bancoEmMemoria);

    }

    @Override
    public List<Endereco> buscarPorCpfCliente(String cpf) {
        return bancoEmMemoria.stream()
                .filter(endereco -> endereco.getCliente() != null &&
                        endereco.getCliente().getCpf().replaceAll("\\D", "").equals(cpf))
                .collect(Collectors.toList());
    }
}
