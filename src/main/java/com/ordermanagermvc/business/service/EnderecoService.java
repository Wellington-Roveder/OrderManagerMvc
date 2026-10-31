package com.ordermanagermvc.business.service;

import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Endereco;

import java.util.List;

public interface EnderecoService {
    void criarEndereco(String rua, String numero, String cidade, String estado, String cep, Cliente cliente);
    List<Endereco> listarEnderecoCliente(String cpfCliente);
    List<Endereco> listarTodos();
}