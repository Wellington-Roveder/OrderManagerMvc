package com.ordermanagermvc;

import com.ordermanagermvc.model.entity.enums.StatusPedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;





public class TestStatusPedido {


    @Test
    void deveTestarValorEnumRetornaMensagem(){
        StatusPedido status = StatusPedido.PENDENTE;
        String validacao = status.getMensagem();
        String mensagem = "Pedido realizado com sucesso";
        assertEquals(mensagem, validacao);
    }

    @Test
    void deveValidarConversaoDeStringParaEnum(){
        StatusPedido status = StatusPedido.valueOf("ENVIADO");
        assertEquals(StatusPedido.ENVIADO, status);
    }

}


