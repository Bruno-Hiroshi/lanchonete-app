package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.application.repository.RepositoryReturn;
import fatec.lanchoneteapp.domain.entity.Cliente;
import fatec.lanchoneteapp.domain.entity.Pedido;

import java.sql.SQLException;
import java.util.List;

public class PedidoService {

    private final RepositoryReturn<Pedido> repository;
    
    public PedidoService(RepositoryReturn<Pedido> repository) {
        this.repository = repository;
    }

    /**
     * Cria um novo pedido no sistema.
     *
     * @param pedido O pedido a ser criado contendo as informações como cliente, itens, valor total e status.
     * @return O número identificador gerado para o novo pedido.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public int criarPedido(Pedido pedido) throws SQLException {
        return repository.salvar(pedido);
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
        return repository.buscarPorID(new Pedido(nPedido));
    }

    /**
     * Atualiza os detalhes de um pedido existente no sistema.
     * Permite modificar dados como valor total, data, status e cliente associado.
     *
     * @param pedido O pedido com as informações atualizadas a serem salvas.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public void atualizarPedido(Pedido pedido) throws SQLException {
        repository.atualizar(pedido);
    }

    /**
     * Recupera uma lista de todos os pedidos cadastrados no sistema.
     * Cada pedido retornado contém todas as informações associadas, incluindo cliente.
     *
     * @return Uma lista de objetos Pedido representando todos os pedidos no sistema.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados.
     */
    public List<Pedido> listarPedidos() throws SQLException {
        return repository.listar();
    }
}
