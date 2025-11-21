package fatec.lanchoneteapp.application.facade;

import fatec.lanchoneteapp.application.dto.HistoricoDTO;
import fatec.lanchoneteapp.application.dto.PedidoDTO;
import fatec.lanchoneteapp.application.mapper.PedidoMapper;
import fatec.lanchoneteapp.application.service.ClienteService;
import fatec.lanchoneteapp.application.service.ItemPedidoService;
import fatec.lanchoneteapp.application.service.PedidoService;
import fatec.lanchoneteapp.application.service.ProdutoService;
import fatec.lanchoneteapp.application.usecase.pedido.ManterPedidoUseCase;
import fatec.lanchoneteapp.domain.entity.Cliente;
import fatec.lanchoneteapp.domain.entity.ItemPedido;
import fatec.lanchoneteapp.domain.entity.Pedido;
import fatec.lanchoneteapp.domain.entity.Produto;

import java.sql.SQLException;
import java.util.List;

public class PedidoFacadeImpl implements PedidoFacade{

    private final PedidoService pedidoService;
    private final ItemPedidoService itemPedidoService;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final ManterPedidoUseCase manterPedidoUC;
    private final PedidoMapper mapper;

    public PedidoFacadeImpl(ManterPedidoUseCase manterPedidoUC,
                            PedidoService pedidoService,
                            ItemPedidoService itemPedidoService,
                            ProdutoService produtoService,
                            ClienteService clienteService,
                            PedidoMapper mapper) {
        this.manterPedidoUC = manterPedidoUC;
        this.pedidoService = pedidoService;
        this.itemPedidoService = itemPedidoService;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.mapper = mapper;
    }

    @Override
    public PedidoDTO criarPedido(int clienteId, List<ItemPedido> itensPedido) throws SQLException {
        Pedido pedido = new Pedido();
        Cliente cliente = clienteService.buscarCliente(clienteId);

        manterPedidoUC.criarPedido(pedido, cliente, itensPedido);

        pedido.setnPedido(pedidoService.criarPedido(pedido));
        return mapper.toDTO(pedido);
    }

    @Override
    public PedidoDTO buscarPedido(int nPedido) throws SQLException {
        Pedido pedido = pedidoService.buscarPedido(nPedido);
        return mapper.toDTO(pedido);
    }

    @Override
    public List<PedidoDTO> listarPedidos() throws SQLException {
        return pedidoService.listarPedidos().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public PedidoDTO cancelarPedido(int nPedido) throws SQLException {
        Pedido pedido = pedidoService.buscarPedido(nPedido);

        manterPedidoUC.atualizarStatus(pedido, "Cancelado");

        pedidoService.atualizarPedido(pedido);
        return mapper.toDTO(pedido);
    }

    @Override
    public PedidoDTO adicionarProduto(int nPedido, int idProduto, int qtd) throws SQLException, IllegalArgumentException {
        Pedido pedido = pedidoService.buscarPedido(nPedido);
        Produto produto = produtoService.buscarProduto(idProduto);
        ItemPedido item = new ItemPedido(nPedido, idProduto, qtd);

        manterPedidoUC.adicionarItem(pedido, produto, item);

        produtoService.atualizarProduto(produto);
        itemPedidoService.adicionarItem(item);
        pedidoService.atualizarPedido(pedido);
        return mapper.toDTO(pedido);
    }

    @Override
    public PedidoDTO removerProduto(int nPedido, int idProduto) throws SQLException {
        Pedido pedido = pedidoService.buscarPedido(nPedido);
        ItemPedido item = itemPedidoService.buscarItem(nPedido, idProduto);

        manterPedidoUC.removerItem(pedido, item);

        itemPedidoService.removerItem(item);
        pedidoService.atualizarPedido(pedido);
        return mapper.toDTO(pedido);
    }

    @Override
    public PedidoDTO atualizarQuantidadeProduto(int nPedido, int idProduto, int novaQtd) throws SQLException {
        ItemPedido item = itemPedidoService.buscarItem(nPedido, idProduto);
        Pedido pedido = pedidoService.buscarPedido(nPedido);

        manterPedidoUC.atualizarQuantidadeItem(pedido, item, novaQtd);

        itemPedidoService.atualizarQuantidade(item);
        pedidoService.atualizarPedido(pedido);
        return mapper.toDTO(pedido);
    }

    @Override
    public PedidoDTO atualizarStatus(int nPedido, String novoStatus) throws SQLException {
        Pedido pedido = pedidoService.buscarPedido(nPedido);

        manterPedidoUC.atualizarStatus(pedido, novoStatus);

        pedidoService.atualizarPedido(pedido);
        return mapper.toDTO(pedido);
    }

    @Override
    public List<HistoricoDTO> listarHistorico(int idCliente) throws SQLException {
        return pedidoService.listarHistorico(new Cliente(idCliente)).stream()
                .map(mapper::toHistoricoDTO)
                .toList();
    }
}
