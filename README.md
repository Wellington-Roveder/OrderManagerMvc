# OrderManager MVC

Aplicação de linha de comando desenvolvida em Java para o gerenciamento de clientes, endereços, catálogo de bonecos, estoque e pedidos. O projeto utiliza uma arquitetura em camadas inspirada em MVC, com controllers, serviços de negócio e repositórios desacoplados por interfaces.

O sistema mantém os dados em memória e tem finalidade educacional. A implementação explora organização de domínio, validações, testes unitários, exportação de relatórios e processamento concorrente.

## Funcionalidades

- Cadastro e listagem de clientes.
- Validação de CPF, nome, telefone e e-mail.
- Cadastro e consulta de endereços vinculados a clientes.
- Cadastro de bonecos com categoria, preço e estoque.
- Entrada de estoque e consulta do catálogo.
- Criação de pedidos com validação de cliente, produto, quantidade e disponibilidade.
- Baixa automática do estoque ao realizar uma venda.
- Consulta de todos os pedidos e dos pedidos de um cliente.
- Exportação de pedidos para arquivo CSV.
- Simulação de processamento paralelo de lotes de pedidos.
- Modelos iniciais para cálculo de frete por PAC, Sedex e retirada local.

## Tecnologias

| Tecnologia | Uso |
| --- | --- |
| Java | Linguagem principal |
| Gradle 9.8.2 | Build, execução e gerenciamento de dependências |
| JUnit 5 | Testes automatizados |
| Mockito | Isolamento de dependências nos testes |
| Java Platform Module System | Descrição modular em `module-info.java` |
| `java.util.concurrent` | Simulação de processamento paralelo |

## Arquitetura

O fluxo principal da aplicação é:

```text
ConsoleView
    |
    v
Controllers
    |
    v
Services
    |
    v
Repositories
    |
    v
Entidades de domínio
```

### Camadas

- `view`: interação com o usuário por meio do console.
- `controller`: coordenação das operações solicitadas pela view.
- `business.service`: contratos dos serviços de negócio.
- `business.impl`: implementação das regras de negócio, validações, exportação e processamento paralelo.
- `business.frete`: estratégias e fábrica para cálculo de taxas de frete.
- `model.entity`: entidades e objetos do domínio, como `Cliente`, `Pedido`, `Boneco`, `ItemPedido` e `Endereco`.
- `model.repository`: contratos e implementações em memória para acesso aos dados.
- `util`: utilitários compartilhados, incluindo o logger.

Os serviços dependem das interfaces de repositório, permitindo substituir a implementação em memória por uma solução persistente sem acoplar as regras de negócio a uma tecnologia específica.

## Estrutura do projeto

```text
OrderManagerMvc/
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
├── src/
│   ├── module-info.java
│   ├── main/java/com/ordermanagermvc/
│   │   ├── business/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── util/
│   │   └── view/
│   └── test/java/com/ordermanagermvc/
└── README.md
```

## Pré-requisitos

- JDK 17 ou superior.
- Git.

Confira as versões instaladas:

```bash
java -version
bash ./gradlew --version
```

No Windows, use `gradlew.bat` no lugar de `bash ./gradlew`.

## Como executar

Clone o repositório:

```bash
git clone https://github.com/Wellington-Roveder/OrderManagerMvc.git
cd OrderManagerMvc
```

Execute a aplicação:

```bash
bash ./gradlew run
```

No Windows:

```powershell
.\gradlew.bat run
```

O menu interativo será exibido no terminal. Os dados criados durante a execução são armazenados apenas em memória e são perdidos ao encerrar o programa.

## Testes

Para executar a suíte de testes:

```bash
bash ./gradlew test
```

Os testes cobrem principalmente serviços de clientes, pedidos, interação da view e regras relacionadas ao status de pedidos.

## Limitações atuais

- Não há persistência entre execuções.
- A aplicação possui apenas interface de console.
- O processamento paralelo disponível é uma simulação de lotes, não um fluxo distribuído de produção.
- O status do pedido e o cálculo de frete ainda não estão integrados a um ciclo completo de checkout.
- O CSV é gerado localmente, sem histórico ou gerenciamento de relatórios.

## Roadmap

- [ ] Implementar persistência com banco de dados relacional, preferencialmente com uma camada de migração e configuração por ambiente.
- [ ] Integrar o ciclo de status do pedido, do recebimento à conclusão ou cancelamento.
- [ ] Integrar frete, transportadora e endereço ao cálculo final do pedido.
- [ ] Ampliar as validações de domínio e padronizar o tratamento de erros na interface.
- [ ] Melhorar a concorrência do processamento de pedidos com controle de ciclo de vida, resultados e falhas das tarefas.
- [ ] Adicionar testes de integração para repositórios e fluxos completos de pedido.
- [ ] Configurar pipeline de integração contínua com compilação, testes e análise estática.
- [ ] Evoluir a interface para uma API web ou aplicação cliente, preservando a camada de negócio.
- [ ] Publicar documentação de arquitetura e diagramas de domínio.

## Contribuição

1. Faça um fork do repositório.
2. Crie uma branch descritiva para a alteração:

   ```bash
   git checkout -b feature/nome-da-feature
   ```

3. Implemente a mudança e adicione ou atualize os testes correspondentes.
4. Execute `bash ./gradlew test`.
5. Abra um Pull Request descrevendo o problema, a solução e os impactos da alteração.

## Autor

**Wellington Roveder**

[GitHub](https://github.com/Wellington-Roveder)
