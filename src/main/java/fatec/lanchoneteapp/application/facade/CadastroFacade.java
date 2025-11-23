package fatec.lanchoneteapp.application.facade;

import fatec.lanchoneteapp.application.dto.*;

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
     */
    void novoFuncionario(FuncionarioDTO funcionario) throws SQLException;
    /**
     * Busca os detalhes de um funcionário específico com base no seu ID.
     *
     * @param idFuncionario o ID do funcionário a ser buscado
     * @return os detalhes do funcionário encapsulados em um objeto FuncionarioDTO
     */
    FuncionarioDTO buscarFuncionario(int idFuncionario) throws SQLException;
    /**
     * Atualiza as informações de um funcionário existente com base nos dados fornecidos.
     *
     * @param funcionario o objeto FuncionarioDTO contendo os dados atualizados do funcionário
     */
    void atualizarFuncionario(FuncionarioDTO funcionario) throws SQLException;
    /**
     * Remove um funcionário do sistema com base no seu ID.
     *
     * @param idFuncionario o ID único do funcionário a ser removido
     */
    void removerFuncionario(int idFuncionario) throws SQLException;

    /**
     * Retorna uma lista de todos os funcionários cadastrados no sistema.
     *
     * @return uma lista de objetos FuncionarioDTO representando os funcionários cadastrados
     */
    List<FuncionarioDTO> listarFuncionarios() throws SQLException;

    //CARGO
    /**
     * Cria um novo cargo com base nas informações fornecidas.
     *
     * @param cargo o objeto CargoDTO que contém os dados do cargo a ser criado
     */
    void novoCargo(CargoDTO cargo) throws SQLException;
    /**
     * Busca os detalhes de um cargo específico com base no seu ID.
     *
     * @param idCargo o identificador único do cargo a ser buscado
     * @return os detalhes do cargo encapsulados em um objeto CargoDTO
     */
    CargoDTO buscarCargo(int idCargo) throws SQLException;
    /**
     * Atualiza as informações de um cargo existente.
     *
     * @param cargo um objeto CargoDTO contendo os dados atualizados do cargo
     */
    void atualizarCargo(CargoDTO cargo) throws SQLException;
    /**
     * Remove o cargo identificado pelo ID especificado do sistema.
     *
     * @param idCargo o ID do cargo a ser removido
     */
    void removerCargo(int idCargo) throws SQLException;

    /**
     * Retorna uma lista de todos os cargos cadastrados no sistema.
     *
     * @return uma lista de objetos CargoDTO representando os cargos cadastrados
     */
    List<CargoDTO> listarCargos() throws SQLException;

    //PRODUTO
    /**
     * Registra um novo produto no sistema.
     *
     * @param produto um objeto ProdutoDTO contendo as informações do produto a ser criado
     */
    void novoProduto(ProdutoDTO produto) throws SQLException;
    /**
     * Busca os detalhes de um produto específico com base no seu ID.
     *
     * @param idProduto o ID do produto a ser buscado
     * @return os detalhes do produto encapsulados em um objeto ProdutoDTO
     */
    ProdutoDTO buscarProduto(int idProduto) throws SQLException;
    /**
     * Atualiza as informações de um produto existente no sistema.
     *
     * @param produto os dados atualizados do produto encapsulados em um objeto ProdutoDTO
     */
    void atualizarProduto(ProdutoDTO produto) throws SQLException;
    /**
     * Remove um produto do sistema com base no seu ID.
     *
     * @param idProduto o ID do produto a ser removido
     */
    void removerProduto(int idProduto) throws SQLException;

    /**
     * Retorna uma lista de todos os produtos cadastrados no sistema.
     *
     * @return uma lista de objetos ProdutoDTO representando os produtos cadastrados
     */
    List<ProdutoDTO> listarProdutos() throws SQLException;

    //CATEGORIA
    /**
     * Cria uma nova categoria no sistema com base nas informações fornecidas.
     *
     * @param categoria um objeto CategoriaDTO contendo os detalhes da nova categoria a ser criada
     */
    void novaCategoria(CategoriaDTO categoria) throws SQLException;

    /**
     * Busca uma categoria específica pelo seu ID.
     *
     * @param idCategoria o identificador único da categoria a ser buscada
     * @return os detalhes da categoria encapsulados em um objeto CategoriaDTO
     */
    CategoriaDTO buscarCategoria(int idCategoria) throws SQLException;

    /**
     * Atualiza as informações de uma categoria existente no sistema.
     *
     * @param categoria o objeto CategoriaDTO contendo os dados atualizados da categoria
     */
    void atualizarCategoria(CategoriaDTO categoria) throws SQLException;

    /**
     * Remove uma categoria existente com base no seu ID.
     *
     * @param idCategoria o ID da categoria a ser removida
     */
    void removerCategoria(int idCategoria) throws SQLException;

    /**
     * Retorna uma lista de todas as categorias cadastradas no sistema.
     *
     * @return uma lista de objetos CategoriaDTO representando as categorias cadastradas
     */
    List<CategoriaDTO> listarCategorias() throws SQLException;
}
