package com.ordermanagermvc.model.repository;


import java.util.List;
import com.ordermanagermvc.model.entity.Cliente;

public interface ClienteRepository {
		void salvar(Cliente cliente);
		List<Cliente> listarTodos();
}
