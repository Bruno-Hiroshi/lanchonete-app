package fatec.lanchoneteapp.application.facade;

import fatec.lanchoneteapp.application.dto.*;
import fatec.lanchoneteapp.application.exception.*;
import fatec.lanchoneteapp.application.mapper.*;
import fatec.lanchoneteapp.application.service.*;
import fatec.lanchoneteapp.domain.entity.*;

import java.sql.SQLException;
import java.util.List;

public class CadastroFacadeImpl implements CadastroFacade{

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper = new ClienteMapper();

    private final FuncionarioService funcionarioService;
    private final FuncionarioMapper funcionarioMapper = new FuncionarioMapper();

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper = new ProdutoMapper();

    private final CargoService cargoService;
    private final CargoMapper cargoMapper = new CargoMapper();

    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper = new CategoriaMapper();

    public CadastroFacadeImpl(ClienteService clienteService,
                              FuncionarioService funcionarioService,
                              ProdutoService produtoService,
                              CargoService cargoService, CategoriaService categoriaService) {
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
        this.produtoService = produtoService;
        this.cargoService = cargoService;
        this.categoriaService = categoriaService;
    }

    @Override
    public void novoCliente(ClienteDTO clienteDTO) throws SQLException, ClienteInvalidoException {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        clienteService.criarCliente(cliente);
    }

    @Override
    public ClienteDTO buscarCliente(int idCliente) throws SQLException, ClienteNaoEncontradoException {
        return clienteMapper.toDTO(clienteService.buscarCliente(idCliente));
    }

    @Override
    public void atualizarCliente(ClienteDTO clienteDTO) throws SQLException {
        clienteService.atualizarCliente(clienteMapper.toEntity(clienteDTO));
    }

    @Override
    public void removerCliente(int idCliente) throws SQLException, ClienteNaoEncontradoException {
        Cliente cliente = clienteService.buscarCliente(idCliente);
        clienteService.excluirCliente(cliente);
    }

    @Override
    public List<ClienteDTO> listarClientes() throws SQLException {
        return clienteService.listarClientes().stream()
                .map(clienteMapper::toDTO)
                .toList();
    }

    //==================================================================================

    @Override
    public void novoFuncionario(FuncionarioDTO funcionarioDTO) throws SQLException, FuncionarioInvalidoException {
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        funcionarioService.criarFuncionario(funcionario);
    }

    @Override
    public FuncionarioDTO buscarFuncionario(int idFuncionario) throws SQLException, FuncionarioNaoEncontradoException {
        return funcionarioMapper.toDTO(funcionarioService.buscarFuncionario(idFuncionario));
    }

    @Override
    public void atualizarFuncionario(FuncionarioDTO funcionarioDTO) throws SQLException {
        funcionarioService.atualizarFuncionario(funcionarioMapper.toEntity(funcionarioDTO));
    }

    @Override
    public void removerFuncionario(int idFuncionario) throws SQLException, FuncionarioNaoEncontradoException {
        Funcionario funcionario = funcionarioService.buscarFuncionario(idFuncionario);
        funcionarioService.excluirFuncionario(funcionario);
    }

    @Override
    public List<FuncionarioDTO> listarFuncionarios() throws SQLException {
        return funcionarioService.listarFuncionarios().stream()
                .map(funcionarioMapper::toDTO)
                .toList();
    }

    //==================================================================================

    @Override
    public void novoCargo(CargoDTO cargoDTO) throws SQLException, CargoInvalidoException {
        Cargo cargo = cargoMapper.toEntity(cargoDTO);
        cargoService.criarCargo(cargo);
    }

    @Override
    public CargoDTO buscarCargo(int idCargo) throws SQLException, CargoNaoEncontradoException {
        return cargoMapper.toDTO(cargoService.buscarCargo(idCargo));
    }

    @Override
    public void atualizarCargo(CargoDTO cargoDTO) throws SQLException {
        cargoService.atualizarCargo(cargoMapper.toEntity(cargoDTO));
    }

    @Override
    public void removerCargo(int idCargo) throws SQLException, CargoNaoEncontradoException {
        Cargo cargo = cargoService.buscarCargo(idCargo);
        cargoService.excluirCargo(cargo);
    }

    @Override
    public List<CargoDTO> listarCargos() throws SQLException {
        return cargoService.listarCargos().stream()
                .map(cargoMapper::toDTO)
                .toList();
    }

    //==================================================================================

    @Override
    public void novoProduto(ProdutoDTO produtoDTO) throws SQLException {
        produtoService.criarProduto(produtoMapper.toEntity(produtoDTO));
    }

    @Override
    public ProdutoDTO buscarProduto(int idProduto) throws SQLException, ProdutoNaoEncontradoException {
        return produtoMapper.toDTO(produtoService.buscarProduto(idProduto));
    }

    @Override
    public void atualizarProduto(ProdutoDTO produtoDTO) throws SQLException {
        produtoService.atualizarProduto(produtoMapper.toEntity(produtoDTO));
    }

    @Override
    public void removerProduto(int idProduto) throws SQLException {
        Produto produto = produtoService.buscarProduto(idProduto);
        produtoService.excluirProduto(produto);
    }

    @Override
    public List<ProdutoDTO> listarProdutos() throws SQLException {
        return produtoService.listarProdutos()
                .stream()
                .map(produtoMapper::toDTO)
                .toList();
    }

    //==================================================================================

    @Override
    public void novaCategoria(CategoriaDTO categoriaDTO) throws SQLException, CategoriaInvalidaException {
        categoriaService.criarCategoria(categoriaMapper.toEntity(categoriaDTO));
    }

    @Override
    public CategoriaDTO buscarCategoria(int idCategoria) throws SQLException, CategoriaNaoEncontradaException {
        return categoriaMapper.toDTO(categoriaService.buscarCategoria(idCategoria));
    }

    @Override
    public void atualizarCategoria(CategoriaDTO categoriaDTO) throws SQLException {
        categoriaService.atualizarCategoria(categoriaMapper.toEntity(categoriaDTO));
    }

    @Override
    public void removerCategoria(int idCategoria) throws SQLException {
        Categoria categoria = categoriaService.buscarCategoria(idCategoria);
        categoriaService.removerCategoria(categoria);
    }

    @Override
    public List<CategoriaDTO> listarCategorias() throws SQLException {
        return categoriaService.listarCategorias().stream()
                .map(categoriaMapper::toDTO)
                .toList();
    }
}
