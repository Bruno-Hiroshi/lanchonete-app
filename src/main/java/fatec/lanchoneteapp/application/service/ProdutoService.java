package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.adapters.repository.ProdutoRepository;
import fatec.lanchoneteapp.domain.entity.Produto;

import java.sql.SQLException;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    /**
     * Serviço responsável pela manipulação de produtos no sistema.
     *
     * @param produtoRepository Instância do repositório utilizada para realizar operações com a entidade Produto.
     */
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * Registra um novo produto no sistema, persistindo suas informações no banco de dados.
     *
     * @param produto O objeto Produto contendo as informações como nome, quantidade em estoque, valor unitário e categoria.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados durante a operação.
     */
    public void criarProduto(Produto produto) throws SQLException {
        produtoRepository.salvar(produto);
    }

    /**
     * Exclui um produto do sistema com base no objeto fornecido.
     * O produto é identificado pelo seu ID e removido do banco de dados.
     *
     * @param produto o objeto Produto a ser excluído, contendo obrigatoriamente o ID para identificação
     * @throws SQLException caso ocorra um erro ao acessar o banco de dados
     */
    public void excluirProduto(Produto produto) throws SQLException {
        produtoRepository.excluir(produto);
    }

    /**
     * Busca um produto no sistema com base no seu identificador.
     *
     * @param idProduto O identificador único do produto a ser buscado.
     * @return O objeto Produto correspondente ao identificador informado, contendo todas as informações associadas,
     *         ou um produto vazio caso não seja encontrado.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados durante a operação.
     */
    public Produto buscarProduto(int idProduto) throws SQLException {
        return produtoRepository.buscarPorID(new Produto(idProduto));
    }

    /**
     * Atualiza as informações de um produto existente no banco de dados.
     * O produto deve conter os dados atualizados, como nome, quantidade em estoque,
     * valor unitário e categoria, além de um identificador válido.
     *
     * @param produto O objeto Produto contendo as informações atualizadas que serão persistidas no banco de dados.
     * @throws SQLException Se ocorrer um erro de acesso ao banco de dados ao tentar atualizar o produto.
     */
    public void atualizarProduto(Produto produto) throws SQLException {
        produtoRepository.atualizar(produto);
    }
}
