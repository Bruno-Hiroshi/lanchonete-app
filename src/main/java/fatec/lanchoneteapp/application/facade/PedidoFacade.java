package fatec.lanchoneteapp.application.facade;

import fatec.lanchoneteapp.application.dto.PedidoDTO;
import fatec.lanchoneteapp.domain.entity.ItemPedido;
import fatec.lanchoneteapp.domain.entity.Produto;

import java.sql.SQLException;
import java.util.List;

public interface PedidoFacade {
    

    // --- PEDIDO ---

    /**
     * Cria um novo pedido para um cliente específico com uma lista de produtos.
     *
     * @param clienteId o ID do cliente para quem o pedido está sendo criado
     * @param itensPedido  a lista de itens a serem incluídos no pedido
     * @return os detalhes do pedido criado encapsulados em um objeto PedidoDTO
     */
    PedidoDTO criarPedido(int clienteId, List<ItemPedido> itensPedido) throws SQLException;

    /**
     * Busca os detalhes de um pedido específico com base no seu ID.
     *
     * @param nPedido o ID do pedido a ser buscado
     * @return os detalhes do pedido encapsulados em um objeto PedidoDTO
     */
    PedidoDTO buscarPedido(int nPedido) throws SQLException;

    /**
     * Lista todos os pedidos cadastrados no sistema.
     *
     * @return uma lista de objetos PedidoDTO representando os pedidos existentes
     */
    List<PedidoDTO> listarPedidos() throws SQLException;


    // Cancelar
    /**
     * Cancela um pedido existente com base no seu ID único.
     *
     * @param nPedido o ID do pedido a ser cancelado
     * @return
     */
    PedidoDTO cancelarPedido(int nPedido) throws SQLException;


    // --- ITENS DO PEDIDO ---

    /**
     * Adiciona um produto a um pedido existente com a quantidade especificada.
     *
     * @param nPedido   o ID do pedido ao qual o produto será adicionado
     * @param produtoId  o ID do produto que será adicionado ao pedido
     * @param qtdEstoque a quantidade do produto a ser adicionada ao pedido
     * @return os detalhes atualizados do pedido encapsulados em um objeto PedidoDTO
     */
    PedidoDTO adicionarProduto(int nPedido, Produto produto, int qtdEstoque) throws SQLException, IllegalArgumentException;

    /**
     * Remove um produto de um pedido existente com base no ID do pedido e no ID do produto.
     *
     * @param nPedido o ID do pedido do qual o produto será removido
     * @param produtoId o ID do produto a ser removido do pedido
     * @return os detalhes atualizados do pedido encapsulados em um objeto PedidoDTO
     */
    PedidoDTO removerProduto(int nPedido, Produto produto) throws SQLException;

    /**
     * Atualiza a quantidade de um produto específico em um pedido existente.
     *
     * @param nPedido        o ID do pedido no qual a quantidade do produto será atualizada
     * @param produtoId      o ID do produto cuja quantidade será alterada
     * @param novaQuantidade a nova quantidade a ser definida para o produto no pedido
     * @return os detalhes atualizados do pedido encapsulados em um objeto PedidoDTO
     */
    PedidoDTO atualizarQuantidadeProduto(int nPedido, Produto produto, int novaQuantidade) throws SQLException;

    // --- STATUS ---

    /**
     * Atualiza o status de um pedido existente com base no seu ID único.
     *
     * @param nPedido o ID do pedido cujo status será atualizado
     * @param novoStatus o novo status a ser atribuído ao pedido
     * @return os detalhes do pedido atualizado encapsulados em um objeto PedidoDTO
     */
    PedidoDTO atualizarStatus(int nPedido, String novoStatus) throws SQLException;
}
