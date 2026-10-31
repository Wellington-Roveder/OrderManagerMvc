package com.ordermanagermvc.model.repository;

import com.ordermanagermvc.model.entity.Boneco;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryBonecoRepository implements BonecoRepository{
    private final Map<String, Boneco> bancoEmMemoria = new LinkedHashMap<>();
    private  Boneco boneco;

    @Override
    public void salvar(Boneco boneco) {
        bancoEmMemoria.put(boneco.getUuId(), boneco);
        System.out.println("[DB] Boneco gravado com sucesso na memória.");
    }

    @Override
    public List<Boneco> listarTodos() {
        return List.copyOf(bancoEmMemoria.values());
    }

    @Override
    public Optional<Boneco> buscarPorId(String uuId) {
        return Optional.ofNullable(bancoEmMemoria.get(uuId));
    }

    @Override
    public Optional<Integer> quantidadeEstoque(String uuId) {
        return Optional.ofNullable(bancoEmMemoria.get(uuId))
                .map(Boneco::getQuantidadeEstoque);
    }
    @Override
    public void adicionarEstoque(String uuId, int quantidade) {

        Boneco bonecoEncontrado = bancoEmMemoria.get(uuId);


        if (bonecoEncontrado == null) {
            throw new IllegalArgumentException("Boneco com o ID informado não foi encontrado.");
        }


        bonecoEncontrado.setQuantidadeEstoque(bonecoEncontrado.getQuantidadeEstoque() + quantidade);

        System.out.println("[DB] Estoque atualizado com sucesso na memória.");
    }

}
