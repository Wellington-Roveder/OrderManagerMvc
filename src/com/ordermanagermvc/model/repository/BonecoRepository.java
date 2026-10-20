package com.ordermanagermvc.model.repository;

import com.ordermanagermvc.model.entity.Boneco;

import java.util.List;
import java.util.Optional;

public interface BonecoRepository {
    void salvar(Boneco boneco);
    List<Boneco> listarTodos();
    Optional<Boneco> buscarPorId(String uuId);
    Optional<Integer> quantidadeEstoque(String uuId);

}
