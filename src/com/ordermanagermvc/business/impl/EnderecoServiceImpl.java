package com.ordermanagermvc.business.impl;

import com.ordermanagermvc.business.service.EnderecoService;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.entity.Endereco;
import com.ordermanagermvc.model.repository.EnderecoRepository;

import java.util.List;

public class EnderecoServiceImpl implements EnderecoService {
    private final EnderecoRepository repository;

    public  EnderecoServiceImpl(EnderecoRepository repository){this.repository = repository;}

    @Override
    public void criarEndereco(String rua, String numero, String cidade, String estado, String cep, Cliente cliente) {
        if (rua == null || rua.trim().isEmpty()){
            throw new IllegalArgumentException("Rua nao pode estar vazia!!");
        }
        if (numero == null || numero.trim().isEmpty()){
            throw new IllegalArgumentException("numero nao pode estar vazio!!");
        }
        if (cidade == null || cidade.trim().isEmpty()){
            throw new IllegalArgumentException("cidade nao pode estar vazia!!");
        }
        if (estado == null || estado.trim().isEmpty()){
            throw new IllegalArgumentException("Estado nao pode estar vazio!!");
        }
        if (cep == null || cep.trim().isEmpty()){
            throw new IllegalArgumentException("CEP Invalido!!");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("O endereço deve estar associado a um cliente válido.");
        }

        Endereco novoEndereco = new Endereco(rua,numero,cidade,estado,cep, cliente);
        repository.salvar(novoEndereco);
    }

    @Override
    public List<Endereco> listarTodos() {
        return repository.listarTodos();
    }

    @Override
    public List<Endereco> listarEnderecoCliente(String cpfCliente) {
        String cpfLimpo = cpfCliente.replaceAll("\\D", "");
        return repository.buscarPorCpfCliente(cpfLimpo);
    }


}
