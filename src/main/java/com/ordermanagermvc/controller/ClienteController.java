package com.ordermanagermvc.controller;

import java.util.List;
import java.util.Optional;

import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.model.entity.Cliente;

public class ClienteController {
	private final ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	public String cadastrar(String cpf, String nome, String telefone, String email) {
		  try {
	            service.criarCliente(cpf,  nome, telefone, email);
	            return "Sucesso: Cliente registrado com sucesso!";
	        } catch (IllegalArgumentException e) {
	            return "Erro de validação: " + e.getMessage();
	        } catch (Exception e) {
	            return "Erro inesperado: " + e.getMessage();
	        }
	    }
	
	public List<Cliente> listar(){
		return service.obterTodosOsClientes();
	}

	public Optional<Cliente> buscarPorCpf(String cpf){return service.buscarPorCpf(cpf);}

	}

