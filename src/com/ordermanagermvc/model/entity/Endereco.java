package com.ordermanagermvc.model.entity;

public class Endereco {
    private String rua;
    private String numero;
    private  String cidade;
    private  String estado;
    private String cep;
    private Cliente cliente;

    public Endereco (String rua, String numero, String cidade, String estado, String cep, Cliente cliente){
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.cliente = cliente;

        if (cliente != null && !cliente.getEndereco().contains(this)) {
            cliente.getEndereco().add(this);
        }

    }

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


    @Override
    public String toString() {
        return String.format("Rua: %s, Nº: %s | Cidade: %s-%s | CEP: %s",
                rua, numero, cidade, estado, cep);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}



