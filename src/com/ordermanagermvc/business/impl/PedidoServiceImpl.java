package com.ordermanagermvc.business.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Pedido;
import com.ordermanagermvc.model.repository.PedidoRepository;
import java.math.BigDecimal;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;


    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }


    @Override
    public void criarPedido(String descricao, BigDecimal valor,  Cliente cliente) {
        


        // Validações dos dados de entrada
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do pedido não pode ser vazia.");
        }
        if(valor == null){
            throw  new IllegalArgumentException("Valor nao pode estar vazio");
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do pedido deve ser maior que zero.");
        }


        String uuidRandom = UUID.randomUUID().toString();


        Pedido novoPedido = new Pedido(uuidRandom, descricao, valor, cliente);

        repository.salvar(novoPedido);
    }


    @Override
    public List<Pedido> obterTodosOsPedidos() {
        return repository.listarTodos();
    }

    @Override
    public void exportarPedidoCsv(String caminhoArquivo) throws IOException {
            List<Pedido> pedidos = repository.listarTodos();

            Path path = Paths.get(caminhoArquivo);

            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path)){
                writer.write("ID;ClienteID;Total;Status");
                writer.newLine();


                for (Pedido p : pedidos){
                    String linha = String.format("%s;%s;%.2f;%s",
                            p.getUuId(),
                            p.getDescricao(),
                            p.getValor(),
                            p.getCliente() != null ? p.getCliente().getCpf() : "N/A"
                    );

                    writer.write(linha);
                    writer.newLine();
                }
            }

    }

    @Override
    public void iniciarProcessamentoAssincrono() {
        PedidoProcessor tarefa1 = new PedidoProcessor("Lote de Pedidos #01 (Sudeste)");
        PedidoProcessor tarefa2 = new PedidoProcessor("Lote de Pedidos #02 (Nordeste)");
        PedidoProcessor tarefa3 = new PedidoProcessor("Lote de Pedidos #03 (Centro-Oeste)");

        Thread t1 = new Thread(tarefa1);
        Thread t2 = new Thread(tarefa2);
        Thread t3 = new Thread(tarefa3);

        t1.start();
        t2.start();
        t3.start();
    }
}
