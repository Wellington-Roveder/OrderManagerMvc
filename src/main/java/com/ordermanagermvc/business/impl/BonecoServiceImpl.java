package com.ordermanagermvc.business.impl;

import com.ordermanagermvc.business.service.BonecoService;
import com.ordermanagermvc.model.entity.Boneco;
import com.ordermanagermvc.model.repository.BonecoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BonecoServiceImpl implements BonecoService {
    private final BonecoRepository repository;




    public BonecoServiceImpl(BonecoRepository repository){
        this.repository = repository;
    }


    @Override
    public void criarBoneco(String nome, BigDecimal preco, String categoria) {
           String uuidRandom = UUID.randomUUID().toString();
           Boneco novoBoneco = new Boneco(uuidRandom, nome, preco, categoria);

           repository.salvar(novoBoneco);
    }

    @Override
    public List<Boneco> obterCatalogo() {
        return  repository.listarTodos();
    }

    @Override
    public Optional<Boneco> buscarporUuId(String uuId) {
        return Optional.ofNullable(uuId)
                .filter(c -> !c.isBlank())
                .flatMap(repository::buscarPorId);
    }

    @Override
    public Optional<Integer> quantidadeEstoque(String uuId) {
        return Optional.ofNullable(uuId)
                .filter(id -> !id.isBlank())
                .flatMap(repository::quantidadeEstoque);
    }

    @Override
    public void adcionarQuantidade(String uuId,int quantidade) {
        repository.adicionarEstoque(uuId, quantidade);
    }

}
