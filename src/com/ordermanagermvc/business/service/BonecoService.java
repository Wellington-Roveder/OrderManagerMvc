package com.ordermanagermvc.business.service;

import com.ordermanagermvc.model.entity.Boneco;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BonecoService {
    void criarBoneco(String nome, BigDecimal preco, String categoria);
    List<Boneco> obterCatalogo();
    Optional<Boneco> buscarporUuId(String uuId);
    Optional<Integer> quantidadeEstoque(String uuId);
    void adcionarQuantidade(String uuId,int quantidade);
}
