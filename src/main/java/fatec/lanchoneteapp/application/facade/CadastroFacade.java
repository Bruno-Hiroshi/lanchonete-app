package fatec.lanchoneteapp.application.facade;

import fatec.lanchoneteapp.application.dto.*;
import fatec.lanchoneteapp.domain.entity.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface CadastroFacade define os métodos para gerenciar operações
 * relacionadas a clientes, funcionários, cargos, produtos e categorias
 * dentro do sistema. Esta interface abstrai as funcionalidades de criação,
 * leitura, atualização e remoção (CRUD) para cada um desses componentes.
 */
public interface CadastroFacade {

    //CLIENTE
    /**
     * Cria um novo cliente no sistema com base nos dados fornecidos.
     *
     * @param cliente um objeto ClienteDTO contendo as informações do cliente a ser criado
     */

    void novoCliente(ClienteDTO cliente) throws SQLException;

    /**
     * Busca os detalhes de um cliente específico com base no seu ID.
     *
     * @param idCliente o ID do cliente a ser buscado
     * @return os detalhes do cliente encapsulados em um objeto ClienteDTO
     */
    ClienteDTO buscarCliente(int idCliente) throws SQLException;

    /**
     * Atualiza os dados de um cliente existente no sistema.
     *
     * @param cliente um objeto ClienteDTO contendo os dados atualizados do cliente
     */
    void atualizarCliente(ClienteDTO cliente) throws SQLException;

    /**
     * Remove um cliente do sistema com base no seu ID.
     *
     * @param idCliente o ID do cliente a ser removido
     */
    void removerCliente(int idCliente) throws SQLException;

    /**
     * Retorna uma lista de todos os clientes cadastrados no sistema.
     *
     * @return uma lista contendo os objetos ClienteDTO representando os clientes cadastrados
     */
    List<ClienteDTO> listarClientes() throws SQLException;

    //FUNCIONARIO
    /**
     * Registra um novo funcionário no sistema.
     *
     * @param funcionario um objeto FuncionarioDTO representando os dados do novo funcionário a ser registrado
     * @return o objeto FuncionarioDTO atualizado com os detalhes do funcionário registrado, incluindo o ID gerado
     */
    FuncionarioDTO novoFuncionario(FuncionarioDTO funcionario);
    /**
     * Busca os detalhes de um funcionário específico com base no seu ID.
     *
     * @param idFuncionario o ID do funcionário a ser buscado
     * @return os detalhes do funcionário encapsulados em um objeto FuncionarioDTO
     */
    FuncionarioDTO buscarFuncionario(int idFuncionario);
    /**
     * Atualiza as informações de um funcionário existente com base nos dados fornecidos.
     *
     * @param funcionario o objeto FuncionarioDTO contendo os dados atualizados do funcionário
     * @return o objeto FuncionarioDTO atualizado após a modificação
     */
    FuncionarioDTO atualizarFuncionario(FuncionarioDTO funcionario);
    /**
     * Remove um funcionário do sistema com base no seu ID.
     *
     * @param idFuncionario o ID único do funcionário a ser removido
     * @return o funcionário removido encapsulado em um objeto Funcionario, ou null caso não seja encontrado
     */
    Funcionario removerFuncionario(int idFuncionario);

    /**
     * Retorna uma lista de todos os funcionários cadastrados no sistema.
     *
     * @return uma lista de objetos FuncionarioDTO representando os funcionários cadastrados
     */
    List<FuncionarioDTO> listarFuncionarios();

    //CARGO
    /**
     * Cria um novo cargo com base nas informações fornecidas.
     *
     * @param cargo o objeto CargoDTO que contém os dados do cargo a ser criado
     * @return o objeto CargoDTO representando o cargo criado
     */
    CargoDTO novoCargo(CargoDTO cargo);
    /**
     * Busca os detalhes de um cargo específico com base no seu ID.
     *
     * @param idCargo o identificador único do cargo a ser buscado
     * @return os detalhes do cargo encapsulados em um objeto CargoDTO
     */
    CargoDTO buscarCargo(int idCargo);
    /**
     * Atualiza as informações de um cargo existente.
     *
     * @param cargo um objeto CargoDTO contendo os dados atualizados do cargo
     * @return um objeto CargoDTO representando o cargo atualizado
     */
    CargoDTO atualizarCargo(CargoDTO cargo);
    /**
     * Remove o cargo identificado pelo ID especificado do sistema.
     *
     * @param idCargo o ID do cargo a ser removido
     * @return os detalhes do cargo removido encapsulados em um objeto CargoDTO
     */
    CargoDTO removerCargo(int idCargo);

    /**
     * Retorna uma lista de todos os cargos cadastrados no sistema.
     *
     * @return uma lista de objetos CargoDTO representando os cargos cadastrados
     */
    List<CargoDTO> listarCargos();

    //PRODUTO
    /**
     * Registra um novo produto no sistema.
     *
     * @param produto um objeto ProdutoDTO contendo as informações do produto a ser criado
     * @return um objeto ProdutoDTO representando o produto criado, incluindo os dados registrados no sistema
     */
    ProdutoDTO novoProduto(ProdutoDTO produto);
    /**
     * Busca os detalhes de um produto específico com base no seu ID.
     *
     * @param idProduto o ID do produto a ser buscado
     * @return os detalhes do produto encapsulados em um objeto ProdutoDTO
     */
    ProdutoDTO buscarProduto(int idProduto);
    /**
     * Atualiza as informações de um produto existente no sistema.
     *
     * @param produto os dados atualizados do produto encapsulados em um objeto ProdutoDTO
     * @return o objeto ProdutoDTO representando o produto atualizado
     */
    ProdutoDTO atualizarProduto(ProdutoDTO produto);
    /**
     * Remove um produto do sistema com base no seu ID.
     *
     * @param idProduto o ID do produto a ser removido
     * @return um objeto ProdutoDTO representando o produto removido
     */
    ProdutoDTO removerProduto(int idProduto);

    /**
     * Retorna uma lista de todos os produtos cadastrados no sistema.
     *
     * @return uma lista de objetos ProdutoDTO representando os produtos cadastrados
     */
    List<ProdutoDTO> listarProdutos();

    //CATEGORIA
    /**
     * Cria uma nova categoria no sistema com base nas informações fornecidas.
     *
     * @param categoria um objeto CategoriaDTO contendo os detalhes da nova categoria a ser criada
     * @return o objeto CategoriaDTO representando a categoria criada
     */
    CategoriaDTO novaCategoria(CategoriaDTO categoria);

    /**
     * Busca uma categoria específica pelo seu ID.
     *
     * @param idCategoria o identificador único da categoria a ser buscada
     * @return os detalhes da categoria encapsulados em um objeto CategoriaDTO
     */
    CategoriaDTO buscarCategoria(int idCategoria);

    /**
     * Atualiza as informações de uma categoria existente no sistema.
     *
     * @param categoria o objeto CategoriaDTO contendo os dados atualizados da categoria
     * @return o objeto CategoriaDTO contendo as informações atualizadas da categoria
     */
    CategoriaDTO atualizarCategoria(CategoriaDTO categoria);

    /**
     * Remove uma categoria existente com base no seu ID.
     *
     * @param idCategoria o ID da categoria a ser removida
     * @return os detalhes da categoria removida encapsulados em um objeto CategoriaDTO
     */
    CategoriaDTO removerCategoria(int idCategoria);

    /**
     * Retorna uma lista de todas as categorias cadastradas no sistema.
     *
     * @return uma lista de objetos CategoriaDTO representando as categorias cadastradas
     */
    List<CategoriaDTO> listarCategorias();
}
