package com.ordermanagermvc.model.entity;

public class Cliente {
		private String uuId;
		private String cpf;
		private String nome;
		private String telefone;
		private String email;
		
		
		public Cliente(String uuId, String cpf, String nome, String telefone, String email) {
			 this.uuId = uuId;
			 this.setCpf(cpf);
			 this.setNome(nome);
			 this.setTelefone(telefone);
			 this.setEmail(email);
			 
		}


		public String getUuId() {
			return this.uuId;
		}
		
		public void setUuid(String uuId) {
			this.uuId = uuId;
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
		
		@Override
		public String toString() {
		    return String.format("CPF: %s | Nome: %s | Telefone: %s | Email: %s", 
		            cpf, nome, telefone, email);
		}
		
}
