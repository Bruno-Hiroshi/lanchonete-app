package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.adapters.repository.ProdutoRepository;
import fatec.lanchoneteapp.domain.entity.Produto;

import java.sql.SQLException;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void criarProduto(Produto produto) throws SQLException {
        produtoRepository.salvar(produto);
    }

    public void excluirProduto(Produto produto) throws SQLException {
        produtoRepository.excluir(produto);
    }

    public Produto buscarProduto(int idProduto) throws SQLException {
        return produtoRepository.buscarPorID(new Produto(idProduto));
    }

    public void atualizarProduto(Produto produto) throws SQLException {
        produtoRepository.atualizar(produto);
    }
}
