package com.ordermanagermvc;

import com.ordermanagermvc.business.impl.BonecoServiceImpl;
import com.ordermanagermvc.business.impl.EnderecoServiceImpl;
import com.ordermanagermvc.business.service.BonecoService;
import com.ordermanagermvc.business.service.EnderecoService;
import com.ordermanagermvc.controller.BonecoController;
import com.ordermanagermvc.controller.EnderecoController;
import com.ordermanagermvc.model.repository.*;
import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.business.impl.PedidoServiceImpl;
import com.ordermanagermvc.controller.PedidoController;


import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.business.impl.ClienteServiceImpl; 
import com.ordermanagermvc.controller.ClienteController;

import com.ordermanagermvc.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
     

        PedidoRepository pedidoRepository = new MemoryPedidoRepository();
        ClienteRepository clienteRepository = new MemoryClienteRepository();
        EnderecoRepository enderecoRepository = new MemoryEnderecoRepository();
        BonecoRepository bonecoRepository = new MemoryBonecoRepository();

        ClienteService clienteService = new ClienteServiceImpl(clienteRepository);
        PedidoService pedidoService = new PedidoServiceImpl(pedidoRepository);
        EnderecoService enderecoService = new EnderecoServiceImpl(enderecoRepository);
        BonecoService bonecoService = new BonecoServiceImpl(bonecoRepository);


        PedidoController pedidoController = new PedidoController(pedidoService, clienteService);
        ClienteController clienteController = new ClienteController(clienteService);
        EnderecoController enderecoController = new EnderecoController(enderecoService, clienteService);
        BonecoController bonecoController = new BonecoController(bonecoService);
        

        ConsoleView view = new ConsoleView(pedidoController, clienteController, enderecoController, bonecoController);
       
        view.exibirMenu();
    }
}