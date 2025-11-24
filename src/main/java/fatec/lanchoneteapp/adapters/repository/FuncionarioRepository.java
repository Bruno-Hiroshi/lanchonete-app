package fatec.lanchoneteapp.adapters.repository;

import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Cargo;
import fatec.lanchoneteapp.domain.entity.Funcionario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository implements RepositoryNoReturn<Funcionario> {
    private Connection connection;

    public FuncionarioRepository(Connection connection){
        this.connection = connection; 
    }

    @Override
    public void salvar(Funcionario entidade) throws SQLException {
        String sql = "INSERT INTO Funcionario(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getTel());
        ps.setString(3, entidade.getEmail());
        ps.setDate(4, Date.valueOf(entidade.getDataContrato()));
        ps.setInt(5, entidade.getCargo().getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void atualizar(Funcionario entidade) throws SQLException {
        String sql = "UPDATE Funcionario SET Nome = ?, Telefone = ?, Email = ?, DataContrato = ?, ID_Cargo = ? WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getTel());
        ps.setString(3, entidade.getEmail());
        ps.setDate(4, Date.valueOf(entidade.getDataContrato()));
        ps.setInt(5, entidade.getCargo().getId());
        ps.setInt(6, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluir(Funcionario entidade) throws SQLException {
        String sql = "DELETE FROM Funcionario WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public Funcionario buscarPorID(Funcionario entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT f.ID AS ID_Func, f.Nome AS Nome_Func, f.Telefone, f.Email, f.DataContrato, ");
        sql.append("c.ID AS ID_Cargo, c.Nome AS Nome_Cargo, c.Salario, c.Descricao ");
        sql.append("FROM Funcionario f INNER JOIN Cargo c ");
        sql.append("ON f.ID_Cargo = c.ID ");
        sql.append("WHERE f.ID = ?");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setInt(1, entidade.getId());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Cargo cargo = new Cargo();
            cargo.setId(rs.getInt("ID_Cargo"));
            cargo.setNome(rs.getString("Nome_Cargo"));
            cargo.setSalario(rs.getDouble("Salario"));
            cargo.setDescricao(rs.getString("Descricao"));

            entidade.setId(rs.getInt("ID_Func"));
            entidade.setNome(rs.getString("Nome_Func"));
            entidade.setTel(rs.getString("Telefone"));
            entidade.setEmail(rs.getString("Email"));
            entidade.setDataContrato(rs.getDate("DataContrato").toLocalDate());
            entidade.setCargo(cargo);
            
            cont++;
        }

        if(cont == 0){
            entidade = new Funcionario();
            Cargo cargo = new Cargo();
            entidade.setCargo(cargo);
        }

        rs.close();
        ps.close();
        return entidade;
    }

    @Override
    public List<Funcionario> listar() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT f.ID AS ID_Func, f.Nome AS Nome_Func, f.Telefone, f.Email, f.DataContrato, ");
        sql.append("c.ID AS ID_Cargo, c.Nome AS Nome_Cargo, c.Salario, c.Descricao ");
        sql.append("FROM Funcionario f INNER JOIN Cargo c ");
        sql.append("ON f.ID_Cargo = c.ID");
        PreparedStatement ps = connection.prepareStatement(sql.toString());

        List<Funcionario> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Cargo cargo = new Cargo();
            cargo.setId(rs.getInt("ID_Cargo"));
            cargo.setNome(rs.getString("Nome_Cargo"));
            cargo.setSalario(rs.getDouble("Salario"));
            cargo.setDescricao(rs.getString("Descricao"));

            Funcionario entidade = new Funcionario();
            entidade.setId(rs.getInt("ID_Func"));
            entidade.setNome(rs.getString("Nome_Func"));
            entidade.setTel(rs.getString("Telefone"));
            entidade.setEmail(rs.getString("Email"));
            entidade.setDataContrato(rs.getDate("DataContrato").toLocalDate());
            entidade.setCargo(cargo);

            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }

    @Override
    public Funcionario buscarPorChaveSecundaria(Funcionario entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT f.ID AS ID_Func, f.Nome AS Nome_Func, f.Telefone, f.Email, f.DataContrato, ");
        sql.append("c.ID AS ID_Cargo, c.Nome AS Nome_Cargo, c.Salario, c.Descricao ");
        sql.append("FROM Funcionario f INNER JOIN Cargo c ");
        sql.append("ON f.ID_Cargo = c.ID ");
        sql.append("WHERE f.Email = ?");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setString(1, entidade.getEmail());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Cargo cargo = new Cargo();
            cargo.setId(rs.getInt("ID_Cargo"));
            cargo.setNome(rs.getString("Nome_Cargo"));
            cargo.setSalario(rs.getDouble("Salario"));
            cargo.setDescricao(rs.getString("Descricao"));

            entidade.setId(rs.getInt("ID_Func"));
            entidade.setNome(rs.getString("Nome_Func"));
            entidade.setTel(rs.getString("Telefone"));
            entidade.setEmail(rs.getString("Email"));
            entidade.setDataContrato(rs.getDate("DataContrato").toLocalDate());
            entidade.setCargo(cargo);
            
            cont++;
        }

        if(cont == 0){
            entidade = new Funcionario();
            Cargo cargo = new Cargo();
            entidade.setCargo(cargo);
        }

        rs.close();
        ps.close();
        return entidade;
    }
}
