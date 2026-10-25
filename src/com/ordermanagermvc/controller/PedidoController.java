package com.ordermanagermvc.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Pedido;

public class PedidoController {
    private final PedidoService service;
    private final ClienteService clienteService;

    public PedidoController(PedidoService service, ClienteService clienteService) {
        this.service = service;
        this.clienteService = clienteService;
    }

    public String cadastrar(String descricao, BigDecimal valor, String cpfCliente) {
        try {
            Optional<Cliente> clienteOptional = clienteService.buscarPorCpf(cpfCliente);

            if(clienteOptional.isPresent()) {
                Cliente clienteEncontrado = clienteOptional.get();

                service.criarPedido(descricao, valor, clienteEncontrado);
                return "Sucesso: Pedido registrado com sucesso!";
            }else{
                return "Nenhum Cliente encontrado!!";
            }
        } catch (IllegalArgumentException e) {
            return "Erro de validação: " + e.getMessage();
        } catch (Exception e) {
            return "Erro inesperado: " + e.getMessage();
        }
    }

    public List<Pedido> listar() {
        return service.obterTodosOsPedidos(); 
    }

    public String exportarRelatorio(String caminhoArquivo) {
        if (caminhoArquivo == null || caminhoArquivo.trim().isEmpty()) {
            return "Erro: O caminho do arquivo não pode ser vazio.";
        }
        try {
            service.exportarPedidoCsv(caminhoArquivo);
            return "Sucesso: Relatório exportado com sucesso em: " + caminhoArquivo;
        } catch (IOException e) {
            return "Erro ao gerar o arquivo CSV: " + e.getMessage();
        }
    }

    public String simularProcessamentoParalelo(){
        service.iniciarProcessamentoAssincrono();
        return "Threads de simulação disparadas com sucesso! Verifique o console enquanto navega.";
    }

}