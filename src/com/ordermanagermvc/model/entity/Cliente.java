package com.ordermanagermvc.model.entity;

import java.util.ArrayList;
import java.util.List;


public class Cliente {
		private String cpf;
		private String nome;
		private String telefone;
		private String email;
		private List<Pedido> pedidos;
		private List<Endereco> enderecos;
		
		
		public Cliente(String cpf, String nome, String telefone, String email) {
			 this.setCpf(cpf);
			 this.setNome(nome);
			 this.setTelefone(telefone);
			 this.setEmail(email);
			 this.pedidos = new ArrayList<>();
			 this.enderecos = new ArrayList<>();
		}

		public String getCpf() {
			return cpf;
		}


		public void setCpf(String cpf) {
			this.cpf = cpf;
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
		public String toString() {
		    return String.format("CPF: %s | Nome: %s | Telefone: %s | Email: %s", 
		            cpf, nome, telefone, email);
		}
		
}
