package com.ordermanagermvc.model.repository;


import java.util.List;
import java.util.Optional;

import com.ordermanagermvc.model.entity.Cliente;

public interface ClienteRepository {
		void salvar(Cliente cliente);
		List<Cliente> listarTodos();
		Optional<Cliente> buscarPorCpf(String cpf);
}
