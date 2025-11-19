# Lanchonete App

Aplicação em Java para gerenciamento de uma lanchonete, incluindo controle de funcionários, cargos, produtos e pedidos.


## Índice

- [Descrição](#descrição)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)

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

Estrutura contruída seguindo os princípios da Clean Architecture:

- `domain/entity`: contém as entidades de domínio da aplicação.
- `domain/factory`: contém o padrão criacional Factory para as entidades `Pedido.java` e `Historico.java`.
- `application/facade`: contém o padrão estrutural Façade para centralização dos processos internos da aplicação.
- `application/gateway`: interface para comunicação com APIs externas.
- `application/service`: camada de serviços/regras de negócio.
- `application/usecase`: concentra os casos de uso da aplicação, organizando fluxos específicos que orquestram serviços
- `adapters/gateway`: classes para comunição com APIs externas.
- `adapters/repository`: classes para conexão/comunicação com o banco de dados.
- `adapters/ui`: controladores para comunicação da interface com o sistema.
- `resources`: arquivos de configuração, scripts, etc.

## Pré-requisitos

- **Java 23** instalado e configurado (`JAVA_HOME` e PATH)
- Ferramenta de build:
  - Maven
- IDE recomendada:
  - IntelliJ IDEA, Eclipse, VS Code com extensão Java, ou outra de sua preferência

---
## 👨‍💻 Equipe de Desenvolvimento

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
