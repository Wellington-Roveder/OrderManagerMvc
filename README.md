# OrderManager MVC

Aplicação backend em Java para gerenciamento de clientes e pedidos, estruturada sob o padrão arquitetural MVC (Model-View-Controller) e operada por meio de uma interface de console.

O projeto tem finalidade educacional e foco em boas práticas de organização de código: separação de responsabilidades em camadas, programação orientada a interfaces, inversão de dependências entre serviços e repositórios e modularização com o sistema de módulos do Java (JPMS).

> **Status do projeto:** em desenvolvimento ativo. O código é evoluído de forma iterativa, acompanhando o avanço do meu roadmap de estudos, e está sujeito a refatorações e expansões.

---

## Sumário

- [Visão geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e execução](#instalação-e-execução)
- [Decisões de projeto](#decisões-de-projeto)
- [Limitações conhecidas](#limitações-conhecidas)
- [Roadmap](#roadmap)
- [Contribuição](#contribuição)
- [Autor](#autor)

---

## Visão geral

O OrderManager MVC permite cadastrar e gerenciar clientes e pedidos a partir do terminal. A aplicação é dividida em quatro camadas com responsabilidades bem definidas, de modo que a interface com o usuário, as regras de negócio e o armazenamento de dados possam evoluir de forma independente.

Atualmente, os dados são mantidos em memória e, portanto, não persistem entre execuções. A arquitetura foi desenhada para que a troca por um mecanismo de persistência real (arquivo, banco de dados relacional) exija alterações apenas na camada de repositório.

## Funcionalidades

- Gerenciamento de clientes (entidade `Cliente`).
- Gerenciamento de pedidos (entidade `Pedido`).
- Interação com o usuário via menu de console.
- Persistência temporária em memória por meio de repositórios dedicados.

## Tecnologias

| Item | Descrição |
| --- | --- |
| Linguagem | Java |
| Padrão arquitetural | MVC com camada de serviços (business) |
| Modularização | Java Platform Module System (`module-info.java`) |
| Persistência | Repositórios em memória |
| Interface | Console (entrada e saída padrão) |

## Arquitetura

O fluxo de uma requisição percorre as camadas na seguinte ordem:

```
Usuário
   |
   v
View (ConsoleView)
   |
   v
Controller (ClienteController, PedidoController)
   |
   v
Business (ClienteService, PedidoService)
   |
   v
Model (MemoryClienteRepository, MemoryPedidoRepository)
```

### Responsabilidade das camadas

| Pacote | Responsabilidade | Classes principais |
| --- | --- | --- |
| `model` | Entidades de domínio e acesso aos dados | `Cliente`, `Pedido`, `MemoryClienteRepository`, `MemoryPedidoRepository` |
| `business` | Regras de negócio, expostas por interfaces e implementadas em classes de serviço | `ClienteService`, `PedidoService` e suas implementações |
| `controller` | Recebe as ações da view e as delega aos serviços | `ClienteController`, `PedidoController` |
| `view` | Entrada e saída de dados no console | `ConsoleView` |

Regra de dependência: cada camada conhece apenas a camada imediatamente abaixo. A view não acessa serviços ou repositórios diretamente, e os serviços não conhecem a view.

## Estrutura do projeto

```
OrderManagerMvc/
├── .gitignore
├── README.md
└── src/
    ├── module-info.java
    └── com/
        └── ordermanagermvc/
            ├── model/
            ├── business/
            ├── controller/
            └── view/
```

## Pré-requisitos

- Java Development Kit (JDK) 11 ou superior (necessário para suporte a módulos). Recomenda-se uma versão LTS recente, como 17 ou 21.
- Git, para clonar o repositório.

Para verificar a instalação:

```bash
java -version
javac -version
```

## Instalação e execução

### 1. Clonar o repositório

```bash
git clone https://github.com/Wellington-Roveder/OrderManagerMvc.git
cd OrderManagerMvc
```

### 2. Compilar

Na raiz do projeto, compile todos os arquivos-fonte para o diretório `out`:

**Linux e macOS**

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
```

**Windows (PowerShell)**

```powershell
New-Item -ItemType Directory -Force -Path out
javac -d out (Get-ChildItem -Recurse -Path src -Filter *.java).FullName
```

### 3. Executar

Execute a classe principal informando o módulo e o nome qualificado da classe:

```bash
java --module-path out --module <nome-do-modulo>/<pacote>.<ClassePrincipal>
```

Substitua `<nome-do-modulo>` pelo nome declarado em `src/module-info.java` e `<pacote>.<ClassePrincipal>` pela classe que contém o método `main`.

### Execução por IDE

Como alternativa, importe o projeto no IntelliJ IDEA, Eclipse ou VS Code, configure o JDK do projeto e execute a classe principal diretamente.

## Decisões de projeto

- **Separação em camadas:** isola a interface, as regras de negócio e o acesso a dados, facilitando testes e manutenção.
- **Interfaces para serviços:** o controller depende de abstrações (`ClienteService`, `PedidoService`) e não de implementações concretas, o que permite substituí-las sem alterar as camadas superiores.
- **Repositórios em memória:** simplificam a fase inicial de desenvolvimento e podem ser trocados por implementações persistentes sem impacto nas regras de negócio.
- **Uso de `module-info.java`:** declara explicitamente o que o módulo expõe, reforçando o encapsulamento entre pacotes.

## Limitações conhecidas

- Os dados são perdidos ao encerrar a aplicação.
- Não há suíte de testes automatizados.
- A interface é restrita ao console.

## Roadmap

- [ ] Introduzir interfaces para os repositórios, desacoplando serviços e implementações.
- [ ] Adicionar testes unitários com JUnit 5 para as camadas de serviço e repositório.
- [ ] Implementar validações e tratamento de exceções de domínio.
- [ ] Persistir dados em arquivo ou banco de dados relacional (JDBC ou JPA).
- [ ] Adotar uma ferramenta de build (Maven ou Gradle).
- [ ] Configurar integração contínua com GitHub Actions.
- [ ] Documentar o diagrama de classes da aplicação.

## Contribuição

Por se tratar de um projeto de estudo, sugestões e revisões de código são bem-vindas. Para contribuir:

1. Faça um fork do repositório.
2. Crie uma branch para a sua alteração: `git checkout -b feature/nome-da-feature`.
3. Faça commits objetivos e descritivos.
4. Envie a branch: `git push origin feature/nome-da-feature`.
5. Abra um Pull Request descrevendo a mudança proposta.

## Autor

**Wellington Roveder**

GitHub: [Wellington-Roveder](https://github.com/Wellington-Roveder)
