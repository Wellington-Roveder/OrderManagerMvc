package com.ordermanagermvc.controller;

import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.business.service.EnderecoService;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Endereco;
import java.util.List;

public class EnderecoController {

    private final EnderecoService service;
    private final ClienteService clienteService;

    public EnderecoController(EnderecoService service, ClienteService clienteService){
        this.service = service;
        this.clienteService = clienteService;
    }

    public String cadastrar(String rua, String numero, String cidade, String estado, String cep, String cpfCliente){
        try {

            String cpfLimpo = cpfCliente.replaceAll("\\D", "");


            Cliente clienteEncontrado = clienteService.obterTodosOsClientes().stream()
                    .filter(c -> c.getCpf().equals(cpfLimpo))
                    .findFirst()
                    .orElse(null);

            if (clienteEncontrado == null){
                return "Erro: Nenhum cliente encontrado com o CPF: " + cpfCliente;
            }

            service.criarEndereco(rua, numero, cidade, estado, cep, clienteEncontrado);
            return "✅ Sucesso: Endereço registrado com sucesso!";

        } catch (IllegalArgumentException e) {
            return "❌ Erro de validação: " + e.getMessage();
        } catch (Exception e) {
            return "❌ Erro inesperado: " + e.getMessage();
        }
    }
    public List<Endereco> listarTodos(){
        return  service.listarTodos();
    }

    public List<Endereco> listar(String cpfCliente){
        String cpfLimpo = cpfCliente.replaceAll("\\D", ""); // Garante que busca pelo CPF limpo
        return service.listarEnderecoCliente(cpfLimpo);
    }
}
