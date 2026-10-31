package com.ordermanagermvc.model.entity;

public class Transportadora {
        private String uuId;
        private String nome;
        private String cnpj;


        public Transportadora(String uuId, String nome, String cnpj){
            this.uuId = uuId;
            this.nome = nome;
            this.cnpj = cnpj;
        }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
