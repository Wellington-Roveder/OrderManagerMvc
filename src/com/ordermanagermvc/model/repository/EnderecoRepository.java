package com.ordermanagermvc.model.repository;

import com.ordermanagermvc.model.entity.Endereco;

import java.util.List;

public interface EnderecoRepository {
     void salvar(Endereco endereco);
     List<Endereco> listarTodos();
     List<Endereco> buscarPorCpfCliente(String cpf);
}
