package com.ordermanagermvc;

import com.ordermanagermvc.model.repository.PedidoRepository;
import com.ordermanagermvc.model.repository.MemoryPedidoRepository;
import com.ordermanagermvc.business.service.PedidoService;
import com.ordermanagermvc.business.impl.PedidoServiceImpl;
import com.ordermanagermvc.controller.PedidoController;


import com.ordermanagermvc.model.repository.ClienteRepository;
import com.ordermanagermvc.model.repository.MemoryClienteRepository;
import com.ordermanagermvc.business.service.ClienteService;
import com.ordermanagermvc.business.impl.ClienteServiceImpl; 
import com.ordermanagermvc.controller.ClienteController;

import com.ordermanagermvc.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
     

        PedidoRepository pedidoRepository = new MemoryPedidoRepository();
        ClienteRepository clienteRepository = new MemoryClienteRepository(); 
        

        PedidoService pedidoService = new PedidoServiceImpl(pedidoRepository);
        ClienteService clienteService = new ClienteServiceImpl(clienteRepository); 

        PedidoController pedidoController = new PedidoController(pedidoService, clienteService);
        ClienteController clienteController = new ClienteController(clienteService); 
        

        ConsoleView view = new ConsoleView(pedidoController, clienteController);
       
        view.exibirMenu();
    }
}