package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.adapters.repository.FornecedorRepository;
import fatec.lanchoneteapp.application.exception.FornecedorInvalidoException;
import fatec.lanchoneteapp.application.exception.FornecedorNaoEncontradoException;
import fatec.lanchoneteapp.domain.entity.Fornecedor;

import java.sql.SQLException;
import java.util.List;

public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public void criarFornecedor(Fornecedor fornecedor) throws SQLException, FornecedorInvalidoException {
        if(!validarFornecedor(fornecedor))
            throw new FornecedorInvalidoException("Fornecedor já cadastrado");

        fornecedorRepository.salvar(fornecedor);
    }

    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {
        fornecedorRepository.atualizar(fornecedor);
    }

    public void excluirFornecedor(Fornecedor fornecedor) throws SQLException {
        fornecedorRepository.excluir(fornecedor);
    }

    public Fornecedor buscarFornecedor(int idFornecedor) throws SQLException, FornecedorNaoEncontradoException {
        Fornecedor fornecedor = fornecedorRepository.buscarPorID(new Fornecedor(idFornecedor));

        if(fornecedor == null)
            throw new FornecedorNaoEncontradoException("Fornecedor não encontrado");

        return fornecedor;
    }

    public void removerFornecedor(Fornecedor fornecedor) throws SQLException {
        fornecedorRepository.excluir(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() throws SQLException {
        return fornecedorRepository.listar();
    }

    private boolean validarFornecedor(Fornecedor fornecedor) throws SQLException {
        try {
            buscarFornecedorPorCNPJ(fornecedor);
            return false;
        } catch(FornecedorNaoEncontradoException e) {
            return true;
        }
    }

    private void buscarFornecedorPorCNPJ(Fornecedor fornecedor) throws SQLException, FornecedorNaoEncontradoException {
        if(fornecedorRepository.buscarPorCnpj(fornecedor) == null)
            throw new FornecedorNaoEncontradoException("Fornecedor não encontrado");
    }
}
