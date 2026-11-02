package com.ordermanagermvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ordermanagermvc.business.impl.PedidoServiceImpl;
import com.ordermanagermvc.business.service.BonecoService;
import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.model.entity.Boneco;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;


public class PedidoServiceImplTest {
      private PedidoService pedidoService;
      private PedidoRepository pedidoRepository;
      private ClienteService clienteService;
      private BonecoService bonecoService;

      private  String cpfCliente = "52998224725";
      private final String NOME_VALIDO = "João da Silva";
      private final String TELEFONE_VALIDO = "11987654321";
      private final String EMAIL_VALIDO = "joao@email.com";
      private final String NOME_BONECO = "batman";
      private final String CATEGORIA_BONECO = "BonecosHq";
      private final BigDecimal PRECO = new BigDecimal("1200.50");
      private final int QUANTIDADE_INICIAL = 10;





      @BeforeEach
      void setUp(){
          pedidoRepository = mock(PedidoRepository.class);
          clienteService  = mock(ClienteService.class);
          bonecoService = mock(BonecoService.class);
          pedidoService  = new PedidoServiceImpl(pedidoRepository, clienteService, bonecoService);

      }

      @Test
      void deveRejeitarVendaQuandoQuantidadeNegativa(){
            String bonecoId = "uuid-boneco-123";
            int quantidadeInvalida = -3;

            assertThrows(IllegalArgumentException.class, () -> {
                  pedidoService.realizarVendaBoneco(cpfCliente, bonecoId, quantidadeInvalida);
            });


            verifyNoInteractions(pedidoRepository);

      }

}
