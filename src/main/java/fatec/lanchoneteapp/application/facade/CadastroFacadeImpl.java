package fatec.lanchoneteapp.application.facade;

import fatec.lanchoneteapp.application.dto.*;
import fatec.lanchoneteapp.application.exception.ClienteInvalidoException;
import fatec.lanchoneteapp.application.mapper.ClienteMapper;
import fatec.lanchoneteapp.application.service.ClienteService;
import fatec.lanchoneteapp.domain.entity.Cliente;
import fatec.lanchoneteapp.domain.entity.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class CadastroFacadeImpl implements CadastroFacade{

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper = new ClienteMapper();

    public CadastroFacadeImpl(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public void novoCliente(ClienteDTO clienteDTO) throws SQLException, ClienteInvalidoException {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        clienteService.criarCliente(cliente);
    }

    @Override
    public ClienteDTO buscarCliente(int idCliente) throws SQLException {
        return clienteMapper.toDTO(clienteService.buscarCliente(idCliente));
    }

    @Override
    public void atualizarCliente(ClienteDTO clienteDTO) throws SQLException {
        clienteService.atualizarCliente(clienteMapper.toEntity(clienteDTO));
    }

    @Override
    public void removerCliente(int idCliente) throws SQLException {
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
    public FuncionarioDTO novoFuncionario(FuncionarioDTO funcionario) {
        return null;
    }

    @Override
    public FuncionarioDTO buscarFuncionario(int idFuncionario) {
        return null;
    }

    @Override
    public FuncionarioDTO atualizarFuncionario(FuncionarioDTO funcionario) {
        return null;
    }

    @Override
    public Funcionario removerFuncionario(int idFuncionario) {
        return null;
    }

    @Override
    public List<FuncionarioDTO> listarFuncionarios() {
        return List.of();
    }

    //==================================================================================

    @Override
    public CargoDTO novoCargo(CargoDTO cargo) {
        return null;
    }

    @Override
    public CargoDTO buscarCargo(int idCargo) {
        return null;
    }

    @Override
    public CargoDTO atualizarCargo(CargoDTO cargo) {
        return null;
    }

    @Override
    public CargoDTO removerCargo(int idCargo) {
        return null;
    }

    @Override
    public List<CargoDTO> listarCargos() {
        return List.of();
    }

    //==================================================================================

    @Override
    public ProdutoDTO novoProduto(ProdutoDTO produto) {
        return null;
    }

    @Override
    public ProdutoDTO buscarProduto(int idProduto) {
        return null;
    }

    @Override
    public ProdutoDTO atualizarProduto(ProdutoDTO produto) {
        return null;
    }

    @Override
    public ProdutoDTO removerProduto(int idProduto) {
        return null;
    }

    @Override
    public List<ProdutoDTO> listarProdutos() {
        return List.of();
    }

    //==================================================================================

    @Override
    public CategoriaDTO novaCategoria(CategoriaDTO categoria) {
        return null;
    }

    @Override
    public CategoriaDTO buscarCategoria(int idCategoria) {
        return null;
    }

    @Override
    public CategoriaDTO atualizarCategoria(CategoriaDTO categoria) {
        return null;
    }

    @Override
    public CategoriaDTO removerCategoria(int idCategoria) {
        return null;
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        return List.of();
    }
}
