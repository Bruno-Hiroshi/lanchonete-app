package fatec.lanchoneteapp.config;

import fatec.lanchoneteapp.adapters.repository.*;
import fatec.lanchoneteapp.adapters.repository.db.IDBConnection;
import fatec.lanchoneteapp.adapters.repository.db.SQLServerConnection;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import fatec.lanchoneteapp.application.facade.CadastroFacadeImpl;
import fatec.lanchoneteapp.application.facade.PedidoFacade;
import fatec.lanchoneteapp.application.facade.PedidoFacadeImpl;
import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.application.repository.RepositoryReturn;
import fatec.lanchoneteapp.application.service.*;
import fatec.lanchoneteapp.domain.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AppBuilder {

    private final IDBConnection c;

    // Facades
    private final CadastroFacade cadastroFacade;
    private final PedidoFacade pedidoFacade;

    // Services
    private final ClienteService clienteService;
    private final PedidoService pedidoService;
    private final CargoService cargoService;
    private final CategoriaService categoriaService;
    private final FornecedorService fornecedorService;
    private final FuncionarioService funcionarioService;
    private final ProdutoService produtoService;
    private final ItemPedidoService itemPedidoService;
    private final ProdutoFornecedorService produtoFornecedorService;

    // Repositories
    private final RepositoryNoReturn<Cliente> clienteRepository;
    private final RepositoryReturn<Pedido> pedidoRepository;
    private final RepositoryNoReturn<Cargo> cargoRepository;
    private final RepositoryNoReturn<Categoria> categoriaRepository;
    private final RepositoryNoReturn<Fornecedor> fornecedorRepository;
    private final RepositoryNoReturn<Funcionario> funcionarioRepository;
    private final RepositoryNoReturn<Produto> produtoRepository;
    private final RepositoryNoReturn<ItemPedido> itemPedidoRepository;
    private final RepositoryNoReturn<ProdutoFornecedor> produtoFornecedorRepository;

    public AppBuilder() throws SQLException, ClassNotFoundException {
        c = new SQLServerConnection();

        // instancia repositórios
        clienteRepository = new ClienteRepository(c.getConnection());
        pedidoRepository = new PedidoRepository(c.getConnection());
        cargoRepository = new CargoRepository(c.getConnection());
        categoriaRepository = new CategoriaRepository(c.getConnection());
        fornecedorRepository = new FornecedorRepository(c.getConnection());
        funcionarioRepository = new FuncionarioRepository(c.getConnection());
        produtoRepository = new ProdutoRepository(c.getConnection());
        itemPedidoRepository = new ItemPedidoRepository(c.getConnection());
        produtoFornecedorRepository = new ProdutoFornecedorRepository(c.getConnection());

        // instancia services
        clienteService = new ClienteService(clienteRepository);
        pedidoService = new PedidoService(pedidoRepository);
        cargoService = new CargoService(cargoRepository);
        categoriaService = new CategoriaService(categoriaRepository);
        fornecedorService = new FornecedorService(fornecedorRepository);
        funcionarioService = new FuncionarioService(funcionarioRepository);
        produtoService = new ProdutoService(produtoRepository);
        itemPedidoService = new ItemPedidoService(itemPedidoRepository);
        produtoFornecedorService = new ProdutoFornecedorService(produtoFornecedorRepository);

        // instancia facades
        cadastroFacade = new CadastroFacadeImpl(clienteService, funcionarioService, produtoService, cargoService, categoriaService, fornecedorService);
        pedidoFacade = new PedidoFacadeImpl(pedidoService, itemPedidoService, produtoService, clienteService);
    }

    // getters
    public CadastroFacade getCadastroFacade() {
        return cadastroFacade;
    }

    public PedidoFacade getPedidoFacade() {
        return pedidoFacade;
    }
}
