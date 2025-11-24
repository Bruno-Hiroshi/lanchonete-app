package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.application.exception.ClienteInvalidoException;
import fatec.lanchoneteapp.application.exception.ClienteNaoEncontradoException;
import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {

    private final RepositoryNoReturn<Cliente> repository;

    public ClienteService(RepositoryNoReturn<Cliente> repository) {
        this.repository = repository;
    }

    public void criarCliente(Cliente cliente) throws SQLException, ClienteInvalidoException {
        if(!validarCliente(cliente))
            throw new ClienteInvalidoException("Cliente já cadastrado");

        repository.salvar(cliente);
    }

    public void atualizarCliente(Cliente cliente) throws SQLException {
        repository.atualizar(cliente);
    }

    public void excluirCliente(Cliente cliente) throws SQLException {
        repository.excluir(cliente);
    }

    public Cliente buscarCliente(int clienteId) throws SQLException,ClienteNaoEncontradoException {
        Cliente cliente = repository.buscarPorID(new Cliente(clienteId));

        if(cliente == null)
            throw new ClienteNaoEncontradoException("Cliente não encontrado");

        return cliente;
    }

    public List<Cliente> listarClientes() throws SQLException {
        return repository.listar();
    }

    public boolean validarCliente(Cliente cliente) throws SQLException {
        try{
            buscarDuplicata(cliente);
            return true;
        } catch(ClienteInvalidoException e){
            return false;
        }
    }

    private void buscarDuplicata(Cliente cliente) throws SQLException, ClienteInvalidoException {
        if(repository.buscarPorChaveSecundaria(cliente) != null)
            throw new ClienteInvalidoException("Cliente inválido");
    }
}
