# 📦 OrderManager

Aplicação backend desenvolvida em **Java** estruturada sob o padrão arquitetural **MVC (Model-View-Controller)**, focada no gerenciamento de clientes e pedidos via interface de console.
> ⚠️ **Nota de Desenvolvimento:** Este projeto está sendo desenvolvido ativamente enquanto avanço no meu roadmap de estudos. Ele é iterativo e vai sendo aprimorado, refatorado e expandido a cada nova etapa de aprendizado!
## 🚀 Tecnologias Utilizadas

* **Java** (Estrutura modularizada com `module-info.java`)
* **Padrão MVC** (Model, View, Controller)
* **Repositório em Memória** para persistência temporária de dados

## 📁 Arquitetura do Projeto

O projeto está organizado nos seguintes pacotes dentro de `src/com/ordermanagermvc`:

* **`model/`**: Contém as entidades de domínio (`Cliente`, `Pedido`) e a camada de persistência/repositórios (`MemoryClienteRepository`, `MemoryPedidoRepository`).
* **`business/`**: Camada de regras de negócio e serviços (`ClienteService`, `PedidoService` e suas respectivas implementações).
* **`controller/`**: Controladores responsáveis por intermediar as requisições entre a visão e os serviços (`ClienteController`, `PedidoController`).
* **`view/`**: Interface de interação com o usuário via console (`ConsoleView`).

## ⚙️ Como Executar

1. Certifique-se de ter o **Java Development Kit (JDK)** instalado em sua máquina.
2. Clone este repositório:
   ```bash
   git clone [https://github.com/seu-usuario/OrderManager.git](https://github.com/seu-usuario/OrderManager.git)
