package com.ordermanagermvc.model.entity;

import java.math.BigDecimal;

public class Boneco {
    private String uuId;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private int quantidadeEstoque;


    public Boneco(String uuId, String nome, BigDecimal preco, String categoria, int quantidadeEstoque){
        this.uuId = uuId;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
