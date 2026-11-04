package com.ordermanagermvc.controller;

import java.awt.geom.IllegalPathStateException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;
import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.model.entity.Pedido;

public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    public String cadastrar(int quantidade, String cpfCliente, String bonecoId) {
        try {
            service.realizarVendaBoneco(cpfCliente, bonecoId, quantidade);
            return "Sucesso: Pedido registrado com sucesso!";
        } catch (IllegalArgumentException e) {
            return "Erro de validação: " + e.getMessage();
        }
        catch (Exception e) {
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
        } catch (InvalidPathException e){
            return "O caminho informado é inválido. Detalhes: " + e.getReason();
        }
    }

    public String simularProcessamentoParalelo(){
        service.iniciarProcessamentoAssincrono();
        return "Threads de simulação disparadas com sucesso! Verifique o console enquanto navega.";
    }
}
