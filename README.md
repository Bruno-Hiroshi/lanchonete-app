<h1 align="center">Lanchonete App</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-23-orange?style=flat-square" />
  <img src="https://img.shields.io/badge/Clean%20Architecture-✔-brightgreen?style=flat-square" />
  <img src="https://img.shields.io/badge/License-MIT-green?style=flat-square" />
  <img src="https://img.shields.io/github/last-commit/andrelamego/lanchonete-app?color=blue&style=flat-square" />
  <img src="https://img.shields.io/github/commit-activity/m/andrelamego/lanchonete-app?color=yellow&style=flat-square" />
  <img src="https://img.shields.io/badge/status-Fase%20Inicial%20de%20Desenvolvimento-red?style=flat-square" />
</p>

---

Aplicação em Java para gerenciamento de uma lanchonete, incluindo controle de funcionários, cargos, produtos e pedidos.

##  Status do Desenvolvimento

🚧 O projeto encontra-se na fase **inicial de desenvolvimento**.  
Novas funcionalidades, módulos e aprimoramentos arquiteturais ainda estão sendo planejados e implementados.


## Índice

- [Descrição](#descrição)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Padrões de Projeto Utilizados](#padrões-de-projeto-utilizados)
- [Diagramas](#diagramas)
- [Pré-requisitos](#pré-requisitos)
- [Equipe de Desenvolvimento](#equipe-de-desenvolvimento)
- [Licença](#-licença)

---

## Descrição

O **App** tem como objetivo facilitar o controle operacional de uma lanchonete, permitindo o cadastro e gerenciamento de entidades do domínio (como cargos, funcionários, produtos e pedidos), além de apoiar o fluxo de atendimento.

## Funcionalidades

Algumas funcionalidades previstas:

- Cadastro e manutenção de cargos (ex.: atendente, cozinheiro, gerente)
- Definição de salário e função para cada cargo
- Cadastro de funcionários vinculados a cargos
- Cadastro de produtos e categorias
- Registro de pedidos e itens do pedido
- Geração de relatórios básicos

## Tecnologias

- **Linguagem:** Java 23
- **Paradigma:** Programação orientada a objetos
- **Build/Dependência:** Maven
- **Banco de Dados:** SQL Server
- **UI:** JavaFX

## Estrutura do Projeto

Estrutura construída seguindo os princípios da Clean Architecture:

- `domain/entity`: contém as entidades de domínio da aplicação.
- `domain/factory`: contém o padrão criacional Factory para as entidades `Pedido.java` e `Historico.java`.
- `application/facade`: contém o padrão estrutural Façade para centralização dos processos internos da aplicação.
- `application/gateway`: interface para comunicação com APIs externas.
- `application/service`: camada de serviços/regras de negócio.
- `application/usecase`: concentra os casos de uso da aplicação, organizando fluxos específicos que orquestram serviços
- `adapters/gateway`: classes para comunicação com APIs externas.
- `adapters/repository`: classes para conexão/comunicação com o banco de dados.
- `adapters/ui`: controladores para comunicação da interface com o sistema.
- `resources`: arquivos de configuração, scripts, etc.

## Padrões de Projeto Utilizados

- **Clean Architecture** para isolamento de camadas.
- **Factory Pattern** para criação das entidades `Pedido` e `Historico`.
- **Facade Pattern** para centralização das operações de alto nível.
- **Strategy Pattern** para encapsular diferentes regras de negócio.
- **Repository Pattern** para abstração de banco de dados.
- **Use Cases** para organização dos fluxos de negócio.

Esses padrões garantem flexibilidade, testabilidade e baixo acoplamento.

## Diagramas

Abaixo estão os principais diagramas que representam a arquitetura e o fluxo da aplicação:

### 1. Diagrama de Pacotes
> Estrutura geral dos módulos seguindo Clean Architecture.
![Diagrama de Pacotes](docs/diagrams/pkgDiagram.png)

### 2. Diagrama de Classes
![Diagrama de Classes](docs/diagrams/class-diagram.png)

### 3. Fluxo Geral do Sistema
![Fluxo do Sistema](docs/diagrams/fluxo-sistema.png)

### 4. Diagrama de Entidade-Relacionamento
![Diagrama de Entidade-Relacionamento](docs/diagrams/DER.png)

### 5. Diagrama de Caso de Uso
![Diagrama de Caso de Uso](docs/diagrams/casoDeUso.png)

## Pré-requisitos

- **Java 23** instalado e configurado (`JAVA_HOME` e PATH)
- Ferramenta de build:
  - Maven
- IDE recomendada:
  - IntelliJ IDEA, Eclipse, VS Code com extensão Java, ou outra de sua preferência

---
## Equipe de Desenvolvimento

Este projeto está sendo construído com dedicação por desenvolvedores comprometidos com qualidade, boas práticas e arquitetura limpa. Cada membro contribuiu com perspectivas diferentes que elevaram o nível do produto.

### Autores

| Nome                 | Função no Projeto                                                         | GitHub                                                       |
|----------------------|---------------------------------------------------------------------------|--------------------------------------------------------------|
| **André Lamego**     | Organização do projeto, desenvolvimento backend, integrações e otimização | [github.com/andrelamego](https://github.com/andrelamego)     |
| **Bruno Hiroshi**    | Arquitetura, regra de negócio e testes                                    | [github.com/Bruno-Hiroshi](https://github.com/Bruno-Hiroshi) |
| **Gabriel de Negri** | Desenvolvimento frontend, regra de negócio e documentação técnica         | [github.com/Bielnegri](https://github.com/Bielnegri)         |
| **Henrique Brito**   | Implementação de features, revisão e suporte técnico                      | [github.com/W7-Henri](https://github.com/W7-Henri)           |

## 📄 Licença
Este projeto está licenciado sob os termos da **MIT License**.  
Consulte o arquivo [LICENSE](./LICENSE) para mais informações.
