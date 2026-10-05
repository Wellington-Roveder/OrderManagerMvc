package com.ordermanagermvc.business.impl;

import java.util.List;
import java.util.UUID;
import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.repository.ClienteRepository;
import java.util.UUID; 



public class ClienteServiceImpl implements ClienteService {
	private final ClienteRepository repository;

	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public void criarCliente(String cpf, String nome, String telefone, String email) {

		cpf = validarCpf(cpf);
		nome = validarNome(nome);
		telefone = validarTelefone(telefone);
		email = validarEmail(email);

		String uuidRandom = UUID.randomUUID().toString();

		Cliente novoCliente = new Cliente(
				uuidRandom,
				cpf,
				nome,
				telefone,
				email
		);

		repository.salvar(novoCliente);
	}

	private String validarCpf(String cpf) {
		if (cpf == null || cpf.isBlank()) {
			throw new IllegalArgumentException("CPF é obrigatório.");
		}
		cpf = cpf.replaceAll("\\D", "");
		if (cpf.length() != 11) {
			throw new IllegalArgumentException("CPF deve possuir 11 dígitos.");
		}
		if (cpf.matches("(\\d)\\1{10}")){
			throw  new IllegalArgumentException("CPF invalido (digitos repetidos).");
		}

		// --- CÁLCULO DO PRIMEIRO DÍGITO VERIFICADOR ---
		int soma = 0;
		int peso = 10;

		for (int i = 0; i < 9; i++){
			int num = Character.getNumericValue(cpf.charAt(i));
			soma += (num * peso);
			peso --;
		}
		int resto = soma % 11;
		int digito1 = (resto < 2) ? 0: (11 - resto);

		if (Character.getNumericValue(cpf.charAt(9))!= digito1){
			throw new IllegalArgumentException("CPF inválido.");
		}

		// --- CÁLCULO DO SEGUNDO DÍGITO VERIFICADOR ---
		soma = 0;
		peso = 11;

		for (int i = 0; i < 10; i++){
			int num = Character.getNumericValue(cpf.charAt(i));
				soma += (num * peso);
				peso --;

		}
		 resto = soma % 11;
		int digito2 = (resto < 2) ? 0 : (11 - resto);

		if (Character.getNumericValue(cpf.charAt(10)) != digito2) {
			throw new IllegalArgumentException("CPF inválido.");
		}
		return cpf;
	}

	private String validarNome(String nome) {
		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome é obrigatório.");
		}
		nome = nome.trim();
		if (nome.length() < 3) {
			throw new IllegalArgumentException("Nome deve possuir pelo menos 3 caracteres.");
		}
		return nome;
	}

	private String validarTelefone(String telefone) {
		if (telefone == null || telefone.isBlank()) {
			throw new IllegalArgumentException("Telefone é obrigatório.");
		}
		telefone = telefone.replaceAll("\\D", "");
		if (telefone.length() < 10 || telefone.length() > 11) {
			throw new IllegalArgumentException("Telefone deve possuir 10 ou 11 dígitos.");
		}
		return telefone;
	}

	private String validarEmail(String email) {
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("E-mail é obrigatório.");
		}
		email = email.trim().toLowerCase();
		if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			throw new IllegalArgumentException("E-mail inválido.");
		}
		return email;
	}

	@Override
	public List<Cliente> obterTodosOsClientes() {
		return repository.listarTodos();
	}
}
