package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.adapters.repository.CategoriaRepository;
import fatec.lanchoneteapp.application.exception.CargoNaoEncontradoException;
import fatec.lanchoneteapp.application.exception.CategoriaInvalidaException;
import fatec.lanchoneteapp.application.exception.CategoriaNaoEncontradaException;
import fatec.lanchoneteapp.domain.entity.Cargo;
import fatec.lanchoneteapp.domain.entity.Categoria;

import java.sql.SQLException;
import java.util.List;

public class CategoriaService {
    public final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void criarCategoria(Categoria categoria) throws SQLException {
        if(!validarCategoria(categoria.getId()))
            throw new CategoriaInvalidaException("Categoria já cadastrada");
    }

    public Categoria buscarCategoria(int idCategoria) throws SQLException, CategoriaNaoEncontradaException {
        Categoria categoria = categoriaRepository.buscarPorID(new Categoria(idCategoria));

        if(categoria == null)
            throw new CategoriaNaoEncontradaException("Categoria não encontrada");

        return categoria;
    }

    public void atualizarCategoria(Categoria categoria) throws SQLException {
        categoriaRepository.atualizar(categoria);
    }

    public void removerCategoria(Categoria categoria) throws SQLException {
        categoriaRepository.excluir(categoria);
    }

    public List<Categoria> listarCategorias() throws SQLException {
        return categoriaRepository.listar();
    }

    //TODO: IMPLEMENTAR BUSCA POR NOME PARA VALIDAÇÃO
    public boolean validarCategoria(int idCategoria) throws SQLException {
        try {
            buscarCategoria(idCategoria);
            return false;
        } catch(CategoriaNaoEncontradaException e) {
            return true;
        }
    }
}
