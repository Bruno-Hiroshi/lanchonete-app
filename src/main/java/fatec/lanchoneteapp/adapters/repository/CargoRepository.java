package fatec.lanchoneteapp.adapters.repository;

import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoRepository implements RepositoryNoReturn<Cargo> {
    private Connection connection;

    public CargoRepository(Connection connection){
        this.connection = connection; 
    }

    @Override
    public void salvar(Cargo entidade) throws SQLException{
        String sql = "INSERT INTO Cargo(Nome, Salario, Descricao) VALUES(?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setDouble(2, entidade.getSalario());
        ps.setString(3, entidade.getDescricao());
        ps.execute();
        ps.close();
    }

    @Override
    public void atualizar(Cargo entidade) throws SQLException{
        String sql = "UPDATE Cargo SET Nome = ?, Salario = ?, Descricao = ? WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setDouble(2, entidade.getSalario());
        ps.setString(3, entidade.getDescricao());
        ps.setInt(4, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluir(Cargo entidade) throws SQLException{
        String sql = "DELETE FROM Cargo WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public Cargo buscarPorID(Cargo entidade) throws SQLException{
        String sql = "SELECT ID, Nome, Salario, Descricao FROM Cargo WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getId());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            entidade.setId(rs.getInt("ID"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setSalario(rs.getDouble("Salario"));
            entidade.setDescricao(rs.getString("Descricao"));
            
            cont++;
        }

        if(cont == 0){
            entidade = null;
        }

        rs.close();
        ps.close();
        return entidade;
    }

    @Override
    public List<Cargo> listar() throws SQLException{
        String sql = "SELECT ID, Nome, Salario, Descricao FROM Cargo";
        PreparedStatement ps = connection.prepareStatement(sql);

        List<Cargo> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Cargo entidade = new Cargo();
            entidade.setId(rs.getInt("ID"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setSalario(rs.getDouble("Salario"));
            entidade.setDescricao(rs.getString("Descricao"));

            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }

    @Override
    public Cargo buscarPorChaveSecundaria(Cargo entidade) throws SQLException {
        String sql = "SELECT ID, Nome, Salario, Descricao FROM Cargo WHERE Nome LIKE ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            entidade.setId(rs.getInt("ID"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setSalario(rs.getDouble("Salario"));
            entidade.setDescricao(rs.getString("Descricao"));
            
            cont++;
        }

        if(cont == 0){
            entidade = null;
        }

        rs.close();
        ps.close();
        return entidade;
    }
}
