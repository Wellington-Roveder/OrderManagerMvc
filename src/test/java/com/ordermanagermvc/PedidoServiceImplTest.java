package com.ordermanagermvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ordermanagermvc.business.impl.PedidoServiceImpl;
import com.ordermanagermvc.business.service.BonecoService;
import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.model.entity.Boneco;
import com.ordermanagermvc.model.entity.Cliente;
import com.ordermanagermvc.model.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



public class PedidoServiceImplTest {
      private PedidoService pedidoService;
      private PedidoRepository pedidoRepository;
      private ClienteService clienteService;
      private BonecoService bonecoService;

      

      @BeforeEach
      void setUp(){
          pedidoRepository = mock(PedidoRepository.class);
          clienteService  = mock(ClienteService.class);
          bonecoService = mock(BonecoService.class);
          pedidoService  = new PedidoServiceImpl(pedidoRepository, clienteService, bonecoService);

      }

      @ParameterizedTest
      @ValueSource(ints = {-3,-1,0})
      void deveRejeitarVendaQuandoQuantidadeNegativa(int quantidadeInvalida){
            String cpfCliente = "52998224725";
            String bonecoId = "uuid-boneco-123";
            

            IllegalArgumentException vx = assertThrows(IllegalArgumentException.class, () -> {
                  pedidoService.realizarVendaBoneco(cpfCliente, bonecoId, quantidadeInvalida);
            });
            
            assertEquals("Erro: quantidade deve ser maior que zero!!", vx.getMessage());
            verifyNoInteractions(pedidoRepository);

      }

}
