package com.ordermanagermvc.model.entity;

import java.util.Objects;
import java.util.UUID;

public class Endereco {
    private final String uuId;
    private String rua;
    private String numero;
    private  String cidade;
    private  String estado;
    private String cep;
    private final Cliente cliente;

    public Endereco (String uuId, String rua, String numero, String cidade, String estado, String cep, Cliente cliente){
        this.uuId = uuId;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.cliente = cliente;

        if (cliente != null && !cliente.getEnderecos().contains(this)) {
            cliente.getEnderecos().add(this);
        }

    }
    public String getUuId() { return uuId; }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Cliente getCliente() {
        return cliente;
    }




        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Endereco endereco = (Endereco) o;
            return Objects.equals(uuId, endereco.uuId);
        }


        @Override
        public int hashCode() {
            return Objects.hash(uuId);
        }


        @Override
        public String toString() {
            String clienteCpf = (cliente != null) ? cliente.getCpf() : "Sem cliente";
            return String.format("ID: %s | Rua: %s, Nº: %s | Cidade: %s-%s | CEP: %s | Cliente CPF: %s",
                    uuId, rua, numero, cidade, estado, cep, clienteCpf);
        }


}



