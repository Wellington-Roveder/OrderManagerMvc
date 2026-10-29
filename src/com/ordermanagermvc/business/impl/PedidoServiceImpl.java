package com.ordermanagermvc.business.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.ordermanagermvc.business.service.BonecoService;
import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.model.entity.Boneco;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.ItemPedido;
import com.ordermanagermvc.model.entity.Pedido;
import com.ordermanagermvc.model.repository.PedidoRepository;
import java.math.BigDecimal;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final BonecoService bonecoService;


    ExecutorService pool = Executors.newFixedThreadPool(2);


    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteService clienteService, BonecoService bonecoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.bonecoService = bonecoService;
    }

    @Override
    public Pedido realizarVendaBoneco(String cpfCliente, String bonecoId, int quantidade){
        Cliente cliente = clienteService.buscarPorCpf(cpfCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o CPF: " + cpfCliente));

        Boneco boneco = bonecoService.buscarporUuId(bonecoId)
                .orElseThrow(() -> new IllegalArgumentException("Boneco não encontrado com o Id " + bonecoId));

        if(boneco.getQuantidadeEstoque() < quantidade){
            throw new IllegalArgumentException("Estoque insuficiente. Disponível: " + boneco.getQuantidadeEstoque());
        }

        boneco.setQuantidadeEstoque(boneco.getQuantidadeEstoque() - quantidade);


        ItemPedido itemPedido = new ItemPedido(quantidade, boneco.getPreco(), boneco);

        String uuidRandom = UUID.randomUUID().toString();
        Pedido novoPedido = new Pedido(uuidRandom, cliente);

        novoPedido.adicionarItem(itemPedido);

        pedidoRepository.salvar(novoPedido);
        return novoPedido;

    }



    @Override
    public List<Pedido> obterTodosOsPedidos() {
        return pedidoRepository.listarTodos();
    }

    @Override
    public void exportarPedidoCsv(String caminhoArquivo) throws IOException {
            List<Pedido> pedidos = pedidoRepository.listarTodos();

            Path path = Paths.get(caminhoArquivo);

            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path)){
                writer.write("ID;ClienteID;Total;Status");
                writer.newLine();


                for (Pedido p : pedidos){
                    String linha = String.format("%s;%s",
                            p.getUuId(),
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
        PedidoProcessor tarefa4 = new PedidoProcessor("Lote de Pedidos #04 (Sul)");

        pool.submit(tarefa1);
        pool.submit(tarefa2);
        pool.submit(tarefa3);
        pool.submit(tarefa4);

        pool.shutdown();


    }
}
