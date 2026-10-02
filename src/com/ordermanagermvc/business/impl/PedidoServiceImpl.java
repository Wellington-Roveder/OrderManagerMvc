package com.ordermanagermvc.business.impl;

import java.util.List;
import java.util.UUID;
import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.model.entity.Cliente; // Importação adicionada
import com.ordermanagermvc.model.entity.Pedido;
import com.ordermanagermvc.model.repository.PedidoRepository;

public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }


    @Override
    public void criarPedido(String descricao, double valor, Cliente cliente) {

        // Validações dos dados de entrada
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do pedido não pode ser vazia.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do pedido deve ser maior que zero.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("O pedido deve estar associado a um cliente válido.");
        }

        String uuidRandom = UUID.randomUUID().toString();


        Pedido novoPedido = new Pedido(uuidRandom, descricao, valor, cliente);

        repository.salvar(novoPedido);
    }

    @Override
    public List<Pedido> obterTodosOsPedidos() {
        return repository.listarTodos();
    }
}
