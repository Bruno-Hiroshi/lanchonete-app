package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.adapters.repository.ItemPedidoRepository;
import fatec.lanchoneteapp.domain.entity.ItemPedido;
import fatec.lanchoneteapp.domain.entity.Produto;

import java.sql.SQLException;

public class ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoService produtoService;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, ProdutoService produtoService) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoService = produtoService;
    }

    /**
     * Adiciona um item ao pedido e salva no banco de dados. O item deve conter informações como número do pedido,
     * ID do produto, quantidade, valor unitário e valor total.
     *
     * @param item o objeto ItemPedido contendo as informações do item a ser adicionado
     * @throws SQLException caso ocorra erro ao salvar no banco de dados
     */
    public void adicionarItem(ItemPedido item) throws SQLException {
        itemPedidoRepository.salvar(item);
    }

    /**
     * Remove um item do pedido com base no número do pedido e ID do produto.
     * O item será excluído permanentemente do banco de dados.
     *
     * @param item o objeto ItemPedido contendo as informações do item a ser removido
     * @throws SQLException caso ocorra erro ao excluir o item do banco de dados
     */
    public void removerItem(ItemPedido item) throws SQLException {
        itemPedidoRepository.excluir(item);
    }

    /**
     * Atualiza a quantidade e recalcula o valor total de um item específico no pedido.
     * As alterações são persistidas no banco de dados.
     *
     * @param item o objeto ItemPedido contendo as informações atualizadas do item
     * @throws SQLException caso ocorra erro ao atualizar o item no banco de dados
     */
    public void atualizarQuantidade(ItemPedido item) throws SQLException {
        itemPedidoRepository.atualizar(item);
    }

    /**
     * Busca um item específico de um pedido pelo número do pedido e ID do produto.
     * Se o item não for encontrado, retorna um ItemPedido vazio.
     *
     * @param nPedido   o número do pedido a ser consultado
     * @param idProduto o ID do produto a ser buscado no pedido
     * @return o objeto ItemPedido encontrado ou um novo ItemPedido se não encontrado
     * @throws SQLException caso ocorra erro ao consultar o banco de dados
     */
    public ItemPedido buscarItem(int nPedido, int idProduto) throws SQLException {
        return itemPedidoRepository.buscarPorID(new ItemPedido(nPedido, idProduto));
    }
}
