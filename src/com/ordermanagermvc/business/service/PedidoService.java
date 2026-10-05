package com.ordermanagermvc.business.service;

import java.util.List;

import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Pedido;
import java.math.BigDecimal;

public interface PedidoService {
    void criarPedido(String descricao, BigDecimal valor, Cliente cliente);
    List<Pedido> obterTodosOsPedidos();
}