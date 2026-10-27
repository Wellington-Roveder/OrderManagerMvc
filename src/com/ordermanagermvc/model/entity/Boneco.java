package com.ordermanagermvc.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Boneco {
    private String uuId;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private int quantidadeEstoque;


    public Boneco(String uuId, String nome, BigDecimal preco, String categoria){
        this.uuId = uuId;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidadeEstoque = 0;
    }

    public Boneco(String uuId, String nome, BigDecimal preco, String categoria, int quantidadeEstoque){
        this(uuId, nome, preco, categoria);

        if(quantidadeEstoque < 0){
            throw new IllegalArgumentException("O estoque não pode ser negativo.");
        }
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

    @Override
    public String toString() {
        return "Boneco {" +
                " id= " + uuId + '\'' +
                " Nome= " + nome + '\'' +
                " Categoria= " + categoria + '\'' +
                " Preco= " + preco + '\'' +
                " Quantidade no Estoque= " + quantidadeEstoque +'\'' +
                '}';
    }
}

