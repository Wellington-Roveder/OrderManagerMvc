package com.ordermanagermvc.business.service;

import java.io.IOException;
import java.util.List;

import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Pedido;
import java.math.BigDecimal;

public interface PedidoService {
    Pedido realizarVendaBoneco(String cpfCliente, String bonecoId, int quantidade);
    List<Pedido> obterTodosOsPedidos();
    void exportarPedidoCsv(String caminhoArquivo) throws IOException;
    void iniciarProcessamentoAssincrono();

}