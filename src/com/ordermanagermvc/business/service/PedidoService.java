package com.ordermanagermvc.business.service;

import java.util.List;

import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Pedido;

public interface PedidoService {
    void criarPedido(String descricao, double valor, Cliente cliente);
    List<Pedido> obterTodosOsPedidos();
}