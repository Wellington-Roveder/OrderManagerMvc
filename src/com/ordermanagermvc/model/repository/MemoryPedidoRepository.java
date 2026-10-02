package com.ordermanagermvc.model.repository;

import java.util.ArrayList;
import java.util.List;
import com.ordermanagermvc.model.entity.Pedido;


public class MemoryPedidoRepository implements PedidoRepository {
    private final List<Pedido> bancoEmMemoria = new ArrayList<>();
    

    @Override
    public void salvar(Pedido pedido) {
        bancoEmMemoria.add(pedido);
        System.out.println("[DB] Pedido gravado com sucesso na memória.");
    }

    @Override
    public List<Pedido> listarTodos() {
        return new ArrayList<>(bancoEmMemoria);
    }
}
