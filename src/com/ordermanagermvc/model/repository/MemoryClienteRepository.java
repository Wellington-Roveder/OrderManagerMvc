package com.ordermanagermvc.model.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.ordermanagermvc.model.entity.Cliente;



public class MemoryClienteRepository implements ClienteRepository {
    private final Map<String, Cliente> bancoEmMemoria = new LinkedHashMap<>();
    

    @Override
    public void salvar(Cliente cliente) {
        bancoEmMemoria.put(cliente.getCpf(), cliente);
        System.out.println("[DB] Cliente gravado com sucesso na memória.");
    }

    @Override
    public List<Cliente> listarTodos() {
        return List.copyOf(bancoEmMemoria.values());
    }


    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return Optional.ofNullable(bancoEmMemoria.get(cpf));
    }
}

