package com.ordermanagermvc.controller;

import com.ordermanagermvc.business.service.BonecoService;
import com.ordermanagermvc.model.entity.Boneco;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class BonecoController {
    private final BonecoService service;

    public BonecoController(BonecoService service){this.service = service;}

    public String cadastrarBoneco(String nome, BigDecimal preco, String categoria){
        try{
            service.criarBoneco(nome, preco, categoria);
            return "Boneco  Catalogado";

        }catch (IllegalArgumentException e){
            return "Erro na criaçao" + e.getMessage();
        }catch (Exception e){
            return  "Erro inesperado" + e.getMessage();
        }

    }

    public List<Boneco> listarTodos(){return service.obterCatalogo();}

    public Optional<Boneco> buscarPorId(String uuId){
        return service.buscarporUuId(uuId);
    }

    public Optional<Integer> quantidadeEstoque(String uuId){
        return service.quantidadeEstoque(uuId);
    }
}
