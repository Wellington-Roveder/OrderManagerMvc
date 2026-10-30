package com.ordermanagermvc.business.service;


import java.util.List;
import java.util.Optional;

import com.ordermanagermvc.model.entity.Cliente;

public interface ClienteService {

    void criarCliente(String cpf, String nome, String telefone, String email);
    List<Cliente> obterTodosOsClientes();
    Optional<Cliente> buscarPorCpf(String cpf);
}