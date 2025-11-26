package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.application.repository.RepositoryListById;
import fatec.lanchoneteapp.domain.entity.ItemPedido;
import fatec.lanchoneteapp.domain.entity.Produto;

import java.sql.SQLException;
import java.util.List;

public class ItemPedidoService {
    private final RepositoryListById<ItemPedido> repository;

    public ItemPedidoService(RepositoryListById<ItemPedido> repository) {
        this.repository = repository;
    }

    /**
     * Adiciona um item ao pedido e salva no banco de dados. O item deve conter informações como número do pedido,
     * ID do produto, quantidade, valor unitário e valor total.
     *
     * @param item o objeto ItemPedido contendo as informações do item a ser adicionado
     * @throws SQLException caso ocorra erro ao salvar no banco de dados
     */
    public void adicionarItem(ItemPedido item) throws SQLException {
        repository.salvar(item);
    }

    /**
     * Remove um item do pedido com base no número do pedido e ID do produto.
     * O item será excluído permanentemente do banco de dados.
     *
     * @param item o objeto ItemPedido contendo as informações do item a ser removido
     * @throws SQLException caso ocorra erro ao excluir o item do banco de dados
     */
    public void removerItem(ItemPedido item) throws SQLException {
        repository.excluir(item);
    }

    /**
     * Atualiza a quantidade e recalcula o valor total de um item específico no pedido.
     * As alterações são persistidas no banco de dados.
     *
     * @param item o objeto ItemPedido contendo as informações atualizadas do item
     * @throws SQLException caso ocorra erro ao atualizar o item no banco de dados
     */
    public void atualizarQuantidade(ItemPedido item) throws SQLException {
        repository.atualizar(item);
    }

    /**
     * Busca um item específico de um pedido pelo número do pedido e ID do produto.
     * Se o item não for encontrado, retorna um ItemPedido vazio.
     *
     * @param nPedido   o número do pedido a ser consultado
     * @param produto o produto a ser buscado no pedido
     * @return o objeto ItemPedido encontrado ou um novo ItemPedido se não encontrado
     * @throws SQLException caso ocorra erro ao consultar o banco de dados
     */
    public ItemPedido buscarItem(int nPedido, Produto produto) throws SQLException {
        return repository.buscarPorID(new ItemPedido(nPedido, produto));
    }

    /**
     * Lista todos os itens de pedidos.
     *
     * @return uma lisa de ItemPedido 
     * @throws SQLException caso ocorra erro ao consultar o banco de dados
     */
    public List<ItemPedido> listarItens() throws SQLException {
        return repository.listar();
    }

     /**
     * Lista todos os itens de um pedido específico.
     *
     * @param nPedido número do pedido para busca dos itens
     * @return uma lisa de ItemPedido 
     * @throws SQLException caso ocorra erro ao consultar o banco de dados
     */
    public List<ItemPedido> listarItensPorNumPedido(int nPedido) throws SQLException{
        return repository.listarPorId(nPedido);
    }
}
