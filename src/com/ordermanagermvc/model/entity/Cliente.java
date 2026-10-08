package com.ordermanagermvc.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Cliente {
	private final String cpf;
	private String nome;
	private String telefone;
	private String email;
	private List<Pedido> pedidos;
	private List<Endereco> enderecos;


	public Cliente(String cpf, String nome, String telefone, String email) {
		this.cpf = cpf;
		this.setNome(nome);
		this.setTelefone(telefone);
		this.setEmail(email);
		this.pedidos = new ArrayList<>();
		this.enderecos = new ArrayList<>();
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedidos() { return pedidos; }

	public void adicionarPedido(Pedido pedido) {
		this.pedidos.add(pedido);
	}


	public List<Endereco> getEnderecos() { return enderecos; }
	public void adicionarEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cliente cliente = (Cliente) o;
		return Objects.equals(cpf, cliente.cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public String toString() {
		return "Cliente{" +
				" cpf='" + cpf + '\'' +
				" nome='" + nome + '\'' +
				'}';
	}

}
