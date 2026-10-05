package com.ordermanagermvc.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import com.ordermanagermvc.controller.PedidoController;
import com.ordermanagermvc.controller.ClienteController;
import com.ordermanagermvc.model.entity.Pedido;
import com.ordermanagermvc.model.entity.Cliente;


public class ConsoleView {

    private final PedidoController pedidoController;
    private final ClienteController clienteController;
    private final Scanner scanner;

    public ConsoleView(PedidoController pedidoController, ClienteController clienteController) {
        this.pedidoController = pedidoController;
        this.clienteController = clienteController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== GERENCIADOR DE PEDIDOS ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Incluir Novo Pedido");
            System.out.println("4. Listar Todos os Pedidos");
            System.out.println("5: Listar Pedidos de um Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> menuCadastrarCliente();
                    case 2 -> menuListarClientes();
                    case 3 -> menuIncluir();
                    case 4 -> menuListar();
                    case 5 -> menuListarPedidosPorCliente();
                    case 0 -> System.out.println("Saindo do sistema... Até logo!");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }


    private void menuCadastrarCliente() {
        System.out.println("\n--- NOVO CLIENTE ---");
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        String resposta = clienteController.cadastrar(cpf, nome, telefone, email);
        System.out.println(resposta);
    }


    private void menuListarClientes() {
        System.out.println("\n--- LISTAGEM DE CLIENTES ---");
        List<Cliente> clientes = clienteController.listar();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado até o momento.");
        } else {
            clientes.forEach(System.out::println);
        }
    }


    private void menuIncluir() {
        System.out.println("\n--- NOVO PEDIDO ---");

        System.out.print("CPF do Cliente: ");
        String cpfCliente = scanner.nextLine();

        System.out.print("Descrição do item: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor do item (ex: 49.90): ");
        try {
            String valorTexto = scanner.nextLine().replace(",", ".");
            BigDecimal valor = new BigDecimal(valorTexto);

            // Enviando o CPF do cliente junto com os dados do pedido para a controller
            String resposta = pedidoController.cadastrar(descricao, valor, cpfCliente);
            System.out.println(resposta);

        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O valor digitado é inválido.");
        }
    }


    private void menuListar() {
        System.out.println("\n--- LISTAGEM DE PEDIDOS ---");
        List<Pedido> pedidos = pedidoController.listar();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado até o momento.");
        } else {
            pedidos.forEach(System.out::println);
        }
    }

    private void menuListarPedidosPorCliente() {
        System.out.println("\n--- CONSULTAR PEDIDOS POR CLIENTE ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine().replaceAll("\\D", "");

        // Localiza o cliente através do controller de clientes
        Cliente cliente = clienteController.listar().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            System.out.println("❌ Erro: Cliente não encontrado.");
            return;
        }

        System.out.println("\nPedidos de " + cliente.getNome() + ":");
        List<Pedido> pedidosDoCliente = cliente.getPedidos(); // Busca a lista conectada na memória

        if (pedidosDoCliente.isEmpty()) {
            System.out.println("Este cliente ainda não possui pedidos cadastrados.");
        } else {
            pedidosDoCliente.forEach(System.out::println);
        }
    }
}