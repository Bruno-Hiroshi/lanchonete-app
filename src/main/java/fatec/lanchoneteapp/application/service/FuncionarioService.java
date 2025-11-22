package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.adapters.repository.FuncionarioRepository;
import fatec.lanchoneteapp.application.exception.FuncionarioInvalidoException;
import fatec.lanchoneteapp.application.exception.FuncionarioNaoEncontradoException;
import fatec.lanchoneteapp.domain.entity.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void criarFuncionario(Funcionario funcionario) throws SQLException, FuncionarioInvalidoException {
        if(!validarFuncionario(funcionario))
            throw new FuncionarioInvalidoException("Funcionário já cadastrado");

        funcionarioRepository.salvar(funcionario);
    }

    public void atualizarFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioRepository.atualizar(funcionario);
    }

    public void excluirFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioRepository.excluir(funcionario);
    }

    public Funcionario buscarFuncionario(int idFuncionario) throws SQLException, FuncionarioNaoEncontradoException {
        Funcionario funcionario = funcionarioRepository.buscarPorID(new Funcionario(idFuncionario));

        if(funcionario == null)
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado");

        return funcionario;
    }

    public List<Funcionario> listarFuncionarios() throws SQLException {
        return funcionarioRepository.listar();
    }

    //TODO: criar busca por nome (ou cpf/email) no repository e alterar aqui
    public boolean validarFuncionario(Funcionario funcionario) throws SQLException {
        return funcionarioRepository.buscarPorID(funcionario) == null;
    }
}
