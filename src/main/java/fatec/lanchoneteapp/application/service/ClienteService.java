package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.adapters.repository.ClienteRepository;
import fatec.lanchoneteapp.application.exception.ClienteInvalidoException;
import fatec.lanchoneteapp.application.exception.ClienteNaoEncontradoException;
import fatec.lanchoneteapp.application.mapper.ClienteMapper;
import fatec.lanchoneteapp.domain.entity.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void criarCliente(Cliente cliente) throws SQLException, ClienteInvalidoException {
        if(!validarCliente(cliente))
            throw new ClienteInvalidoException("Cliente já cadastrado");

        clienteRepository.salvar(cliente);
    }

    public void atualizarCliente(Cliente cliente) throws SQLException {
        clienteRepository.atualizar(cliente);
    }

    public void excluirCliente(Cliente cliente) throws SQLException {
        clienteRepository.excluir(cliente);
    }

    public Cliente buscarCliente(int clienteId) throws SQLException,ClienteNaoEncontradoException {
        Cliente cliente = clienteRepository.buscarPorID(new Cliente(clienteId));

        if(cliente == null)
            throw new ClienteNaoEncontradoException("Cliente não encontrado");

        return cliente;
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteRepository.listar();
    }

    //TODO: criar busca por nome (ou cpf/email) no repository e alterar aqui
    public boolean validarCliente(Cliente cliente) throws SQLException {
        return clienteRepository.buscarPorID(cliente) == null;
    }
}
