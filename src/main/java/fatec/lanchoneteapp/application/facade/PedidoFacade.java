package fatec.lanchoneteapp.application.facade;

import fatec.lanchoneteapp.application.dto.ItemPedidoDTO;
import fatec.lanchoneteapp.application.dto.PedidoDTO;

import java.sql.SQLException;
import java.util.List;

public interface PedidoFacade {
    

    // --- PEDIDO ---

    /**
     * Cria um novo pedido para um cliente específico.
     *
     * @param pedidoDTO um objeto PedidoDTO contendo os dados do pedido
     * @return os detalhes do pedido criado encapsulados em um objeto PedidoDTO
     */
    PedidoDTO criarPedido(PedidoDTO pedidoDTO) throws SQLException;

    /**
     * Busca os detalhes de um pedido específico com base no seu ID.
     *
     * @param nPedido o ID do pedido a ser buscado
     * @return os detalhes do pedido encapsulados em um objeto PedidoDTO
     */
    PedidoDTO buscarPedido(int nPedido) throws SQLException;

    /**
     * Atualiza os dados de um pedido existente no sistema.
     *
     * @param pedidoDTO um objeto PedidoDTO contendo os dados atualizados do pedido
     */
    void atualizarPedido(PedidoDTO pedidoDTO) throws SQLException;

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
    PedidoDTO adicionarProduto(ItemPedidoDTO itemPedidoDTO) throws SQLException, IllegalArgumentException;

    /**
     * Remove um produto de um pedido existente com base no ID do pedido e no ID do produto.
     *
     * @param itemPedidoDTO objeto com nPedido e produtoId a ser removido do pedido
     * @return os detalhes atualizados do pedido encapsulados em um objeto PedidoDTO
     */
    PedidoDTO removerProduto(ItemPedidoDTO itemPedidoDTO) throws SQLException;

    /**
     * Atualiza a quantidade de um produto específico em um pedido existente.
     *
     * @param itemPedidoDTO objeto com nPedido, produtoId e qtd a ser atualizada no pedido
     * @return os detalhes atualizados do pedido encapsulados em um objeto PedidoDTO
     */
    PedidoDTO atualizarQuantidadeProduto(ItemPedidoDTO itemPedidoDTO) throws SQLException;

    /**
     * Lista todos os produtos adicionados a um pedido.
     *
     * @return uma lista de objetos ItemPedidoDTO representando os produtos disponíveis
     */
    List<ItemPedidoDTO> listarProdutos() throws SQLException;

    // --- STATUS ---

    /**
     * Atualiza o status de um pedido existente com base no seu ID único.
     *
     * @param nPedido o ID do pedido cujo status será atualizado
     * @param novoStatus o novo status a ser atribuído ao pedido
     * @return os detalhes do pedido atualizado encapsulados em um objeto PedidoDTO
     */
    PedidoDTO atualizarStatus(PedidoDTO pedidoDTO) throws SQLException;
}
