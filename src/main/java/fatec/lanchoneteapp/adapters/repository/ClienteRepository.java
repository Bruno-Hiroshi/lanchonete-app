package fatec.lanchoneteapp.adapters.repository;

import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements RepositoryNoReturn<Cliente> {
    private Connection connection;

    public ClienteRepository(Connection connection){
        this.connection = connection; 
    }

    @Override
    public void salvar(Cliente entidade) throws SQLException {
        String sql = "INSERT INTO Cliente(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getTel());
        ps.setString(3, entidade.getCpf());
        ps.setString(4, entidade.getLogradouro());
        ps.setInt(5, entidade.getNumero());
        ps.setString(6, entidade.getCep());
        ps.setString(7, entidade.getComplemento());
        ps.execute();
        ps.close();
    }

    @Override
    public void atualizar(Cliente entidade) throws SQLException {
        String sql = "UPDATE Cliente SET Nome = ?, Telefone = ?, CPF = ?, Logradouro = ?, " +
                    "Numero = ?, CEP = ?, Complemento = ? WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getTel());
        ps.setString(3, entidade.getCpf());
        ps.setString(3, entidade.getLogradouro());
        ps.setInt(4, entidade.getNumero());
        ps.setString(5, entidade.getCep());
        ps.setString(6, entidade.getComplemento());
        ps.setInt(7, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluir(Cliente entidade) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public Cliente buscarPorID(Cliente entidade) throws SQLException {
        String sql = "SELECT ID, Nome, Telefone, CPF, Logradouro, Numero, CEP, Complemento FROM Cliente WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getId());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            entidade.setId(rs.getInt("ID"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setTel(rs.getString("Telefone"));
            entidade.setCpf(rs.getString("CPF"));
            entidade.setLogradouro(rs.getString("Logradouro"));
            entidade.setNumero(rs.getInt("Numero"));
            entidade.setCep(rs.getString("CEP"));
            entidade.setComplemento(rs.getString("Complemento"));
            
            cont++;
        }

        if(cont == 0){
            entidade = new Cliente();
        }

        rs.close();
        ps.close();
        return entidade;
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        String sql = "SELECT ID, Nome, Telefone, CPF, Logradouro, Numero, CEP, Complemento FROM Cliente";
        PreparedStatement ps = connection.prepareStatement(sql);

        List<Cliente> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Cliente entidade = new Cliente();
            entidade.setId(rs.getInt("ID"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setTel(rs.getString("Telefone"));
            entidade.setCpf(rs.getString("CPF"));
            entidade.setLogradouro(rs.getString("Logradouro"));
            entidade.setNumero(rs.getInt("Numero"));
            entidade.setCep(rs.getString("CEP"));
            entidade.setComplemento(rs.getString("Complemento"));

            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }
    
    @Override
    public Cliente buscarPorChaveSecundaria(Cliente entidade) throws SQLException {
        String sql = "SELECT ID, Nome, Telefone, CPF, Logradouro, Numero, CEP, Complemento FROM Cliente WHERE CPF = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getCpf());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            entidade.setId(rs.getInt("ID"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setTel(rs.getString("Telefone"));
            entidade.setCpf(rs.getString("CPF"));
            entidade.setLogradouro(rs.getString("Logradouro"));
            entidade.setNumero(rs.getInt("Numero"));
            entidade.setCep(rs.getString("CEP"));
            entidade.setComplemento(rs.getString("Complemento"));
            
            cont++;
        }

        if(cont == 0){
            entidade = new Cliente();
        }

        rs.close();
        ps.close();
        return entidade;
    }
}