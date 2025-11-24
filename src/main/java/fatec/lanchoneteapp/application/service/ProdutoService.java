package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.application.exception.ProdutoInvalidoException;
import fatec.lanchoneteapp.application.exception.ProdutoNaoEncontradoException;
import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Produto;

import java.sql.SQLException;
import java.util.List;

public class ProdutoService {

    private final RepositoryNoReturn<Produto> repository;

    public ProdutoService(RepositoryNoReturn<Produto> repository) {
        this.repository = repository;
    }

    public void criarProduto(Produto produto) throws SQLException, ProdutoInvalidoException {
        if(!validarProduto(produto))
            throw new ProdutoInvalidoException("Produto já cadastrado");

        repository.salvar(produto);
    }

    public void excluirProduto(Produto produto) throws SQLException {
        repository.excluir(produto);
    }

    public Produto buscarProduto(int idProduto) throws SQLException, ProdutoNaoEncontradoException {
        Produto produto = repository.buscarPorID(new Produto(idProduto));

        if(produto == null)
            throw new ProdutoNaoEncontradoException("Produto não encontrado");

        return produto;
    }

    public void atualizarProduto(Produto produto) throws SQLException {
        repository.atualizar(produto);
    }

    public List<Produto> listarProdutos() throws SQLException {
        return repository.listar();
    }

    public boolean validarProduto(Produto produto) throws SQLException {
        try{
            buscarDuplicata(produto);
            return true;
        } catch(ProdutoInvalidoException e){
            return false;
        }
    }

    private void buscarDuplicata(Produto produto) throws SQLException, ProdutoInvalidoException {
        if(repository.buscarPorChaveSecundaria(produto) != null)
            throw new ProdutoInvalidoException("Produto inválido");
    }
}
