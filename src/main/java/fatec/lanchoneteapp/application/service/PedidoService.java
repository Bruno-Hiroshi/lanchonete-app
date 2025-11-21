package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.adapters.repository.PedidoRepository;
import fatec.lanchoneteapp.domain.entity.Cliente;
import fatec.lanchoneteapp.domain.entity.ItemPedido;
import fatec.lanchoneteapp.domain.entity.Pedido;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PedidoService {

    private final PedidoRepository pedidoRepository;
    
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    /**
     * Cria um novo pedido no sistema.
     *
     * @param pedido O pedido a ser criado contendo as informações como cliente, itens, valor total e status.
     * @return O número identificador gerado para o novo pedido.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public int criarPedido(Pedido pedido) throws SQLException {
        return pedidoRepository.salvar(pedido);
    }

    /**
     * Busca um pedido específico através de seu número identificador.
     *
     * @param nPedido O número identificador do pedido a ser recuperado.
     * @return O pedido correspondente ao número informado, contendo cliente e demais informações,
     * ou um objeto Pedido vazio caso o pedido não exista.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public Pedido buscarPedido(int nPedido) throws SQLException {
        return pedidoRepository.buscarPorID(new Pedido(nPedido));
    }

    /**
     * Atualiza os detalhes de um pedido existente no sistema.
     * Permite modificar dados como valor total, data, status e cliente associado.
     *
     * @param pedido O pedido com as informações atualizadas a serem salvas.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public void atualizarPedido(Pedido pedido) throws SQLException {
        pedidoRepository.atualizar(pedido);
    }

    /**
     * Recupera uma lista de todos os pedidos cadastrados no sistema.
     * Cada pedido retornado contém todas as informações associadas, incluindo cliente.
     *
     * @return Uma lista de objetos Pedido representando todos os pedidos no sistema.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public List<Pedido> listarPedidos() throws SQLException {
        return pedidoRepository.listar();
    }

    /**
     * Recupera o histórico de pedidos de um cliente específico.
     *
     * @param cliente O cliente cujo histórico de pedidos será recuperado.
     * @return Uma lista de objetos Pedido representando o histórico de pedidos do cliente.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public List<Pedido> listarHistorico(Cliente cliente) throws SQLException {
        return pedidoRepository.historicoPedidos(cliente);
    }
}
