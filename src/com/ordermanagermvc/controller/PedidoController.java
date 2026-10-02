package com.ordermanagermvc.controller;

import java.util.List;
import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.model.entity.Pedido;

public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    public String cadastrar(String descricao, double valor) {
        try {
            service.criarPedido(descricao, valor);
            return "✅ Sucesso: Pedido registrado com sucesso!";
        } catch (IllegalArgumentException e) {
            return "❌ Erro de validação: " + e.getMessage();
        } catch (Exception e) {
            return "❌ Erro inesperado: " + e.getMessage();
        }
    }

    public List<Pedido> listar() {
        return service.obterTodosOsPedidos(); 
    }
}