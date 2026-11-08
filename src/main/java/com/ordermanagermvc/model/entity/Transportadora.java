package com.ordermanagermvc.model.entity;

import com.ordermanagermvc.business.frete.TiposFrete;

import java.util.Objects;

public class Transportadora {
        private String nome;
        private String cnpj;
        private TiposFrete tipoFrete;


        public Transportadora(String nome, String cnpj, TiposFrete tipoFrete){
            this.nome = nome;
            this.cnpj = cnpj;
            this.tipoFrete = tipoFrete;
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

    public TiposFrete getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(TiposFrete tipoFrete){
            this.tipoFrete = tipoFrete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transportadora that = (Transportadora) o;
        return Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public String toString() {
        return "Transportadora{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", tipoFrete=" + tipoFrete +
                '}';
    }


}
