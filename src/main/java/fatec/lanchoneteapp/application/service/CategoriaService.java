package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.application.exception.CategoriaInvalidaException;
import fatec.lanchoneteapp.application.exception.CategoriaNaoEncontradaException;
import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Categoria;

import java.sql.SQLException;
import java.util.List;

public class CategoriaService {
    public final RepositoryNoReturn<Categoria> repository;

    public CategoriaService(RepositoryNoReturn<Categoria> repository) {
        this.repository = repository;
    }

    public void criarCategoria(Categoria categoria) throws SQLException {
        if(!validarCategoria(categoria))
            throw new CategoriaInvalidaException("Categoria já cadastrada");
    }

    public Categoria buscarCategoria(int idCategoria) throws SQLException, CategoriaNaoEncontradaException {
        Categoria categoria = repository.buscarPorID(new Categoria(idCategoria));

        if(categoria == null)
            throw new CategoriaNaoEncontradaException("Categoria não encontrada");

        return categoria;
    }

    public void atualizarCategoria(Categoria categoria) throws SQLException {
        repository.atualizar(categoria);
    }

    public void removerCategoria(Categoria categoria) throws SQLException {
        repository.excluir(categoria);
    }

    public List<Categoria> listarCategorias() throws SQLException {
        return repository.listar();
    }

    public boolean validarCategoria(Categoria categoria) throws SQLException {
        try {
            buscarDuplicata(categoria);
            return true;
        } catch(CategoriaInvalidaException e) {
            return false;
        }
    }

    private void buscarDuplicata(Categoria categoria) throws SQLException, CategoriaInvalidaException{
        if(repository.buscarPorChaveSecundaria(categoria) != null)
            throw new CategoriaInvalidaException("Categoria inválida");
    }
}
