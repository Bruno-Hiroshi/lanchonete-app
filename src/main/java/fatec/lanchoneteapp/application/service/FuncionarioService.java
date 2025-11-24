package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.application.exception.FuncionarioInvalidoException;
import fatec.lanchoneteapp.application.exception.FuncionarioNaoEncontradoException;
import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioService {
    private final RepositoryNoReturn<Funcionario> repository;

    public FuncionarioService(RepositoryNoReturn<Funcionario> repository) {
        this.repository = repository;
    }

    public void criarFuncionario(Funcionario funcionario) throws SQLException, FuncionarioInvalidoException {
        if(!validarFuncionario(funcionario))
            throw new FuncionarioInvalidoException("Funcionário já cadastrado");

        repository.salvar(funcionario);
    }

    public void atualizarFuncionario(Funcionario funcionario) throws SQLException {
        repository.atualizar(funcionario);
    }

    public void excluirFuncionario(Funcionario funcionario) throws SQLException {
        repository.excluir(funcionario);
    }

    public Funcionario buscarFuncionario(int idFuncionario) throws SQLException, FuncionarioNaoEncontradoException {
        Funcionario funcionario = repository.buscarPorID(new Funcionario(idFuncionario));

        if(funcionario == null)
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado");

        return funcionario;
    }

    public List<Funcionario> listarFuncionarios() throws SQLException {
        return repository.listar();
    }

    public boolean validarFuncionario(Funcionario funcionario) throws SQLException {
        try{
            buscarDuplicata(funcionario);
            return true;
        } catch(FuncionarioInvalidoException e){
            return false;
        }
    }

    private void buscarDuplicata(Funcionario funcionario) throws SQLException, FuncionarioInvalidoException {
        if(repository.buscarPorChaveSecundaria(funcionario) != null)
            throw new FuncionarioInvalidoException("Funcionário inválido");
    }
}
