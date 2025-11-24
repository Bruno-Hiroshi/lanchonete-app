package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.application.exception.FornecedorInvalidoException;
import fatec.lanchoneteapp.application.exception.FornecedorNaoEncontradoException;
import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Fornecedor;

import java.sql.SQLException;
import java.util.List;

public class FornecedorService {
    private final RepositoryNoReturn<Fornecedor> repository;

    public FornecedorService(RepositoryNoReturn<Fornecedor> repository) {
        this.repository = repository;
    }

    public void criarFornecedor(Fornecedor fornecedor) throws SQLException, FornecedorInvalidoException {
        if(!validarFornecedor(fornecedor))
            throw new FornecedorInvalidoException("Fornecedor já cadastrado");

        repository.salvar(fornecedor);
    }

    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {
        repository.atualizar(fornecedor);
    }

    public void excluirFornecedor(Fornecedor fornecedor) throws SQLException {
        repository.excluir(fornecedor);
    }

    public Fornecedor buscarFornecedor(int idFornecedor) throws SQLException, FornecedorNaoEncontradoException {
        Fornecedor fornecedor = repository.buscarPorID(new Fornecedor(idFornecedor));

        if(fornecedor == null)
            throw new FornecedorNaoEncontradoException("Fornecedor não encontrado");

        return fornecedor;
    }

    public void removerFornecedor(Fornecedor fornecedor) throws SQLException {
        repository.excluir(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() throws SQLException {
        return repository.listar();
    }

    public boolean validarFornecedor(Fornecedor fornecedor) throws SQLException {
        try {
            buscarDuplicata(fornecedor);
            return true;
        } catch(FornecedorInvalidoException e) {
            return false;
        }
    }

    private void buscarDuplicata(Fornecedor fornecedor) throws SQLException, FornecedorInvalidoException {
        if(repository.buscarPorChaveSecundaria(fornecedor) != null)
            throw new FornecedorInvalidoException("Fornecedor inválido");
    }
}
