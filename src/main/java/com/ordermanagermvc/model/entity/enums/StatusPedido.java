package com.ordermanagermvc.model.entity.enums;

public enum StatusPedido {
    PENDENTE("Pedido realizado com sucesso"),
    PROCESSANDO("Pedido em Processo"),
    ENVIADO("Pedido Saiu para Entrega"),
    ENTREGUE("Pedido Entregue"),
    CANCELADO("Este pedido foi cancelado");

    private String mensagem;

    StatusPedido(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return  this.mensagem;
    }
}
