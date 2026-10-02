package com.ordermanagermvc.model.repository;

import java.util.ArrayList;
import java.util.List;
import com.ordermanagermvc.model.entity.Cliente;


public class MemoryClienteRepository implements ClienteRepository {
    private final List<Cliente> bancoEmMemoria = new ArrayList<>();
    

    @Override
    public void salvar(Cliente cliente) {
        bancoEmMemoria.add(cliente);
        System.out.println("[DB] Cliente gravado com sucesso na memória.");
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(bancoEmMemoria);
    }
}
