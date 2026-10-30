package com.ordermanagermvc.model.repository;

import java.util.List;
import com.ordermanagermvc.model.entity.Pedido;

public interface PedidoRepository {
    void salvar(Pedido pedido);
    List<Pedido> listarTodos();
}