package fatec.lanchoneteapp.adapters.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Categoria;

public class CategoriaRepository implements RepositoryNoReturn<Categoria> {
    private Connection connection;

    public CategoriaRepository(Connection connection){
        this.connection = connection; 
    }

    @Override
    public void salvar(Categoria entidade) throws SQLException {
        String sql = "INSERT INTO Categoria(?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getDescricao());
        ps.execute();
        ps.close();
    }

    @Override
    public void atualizar(Categoria entidade) throws SQLException {
        String sql = "UPDATE Categoria SET Nome = ?, Descricao = ? WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getDescricao());
        ps.setInt(3, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluir(Categoria entidade) throws SQLException {
        String sql = "DELETE FROM Categoria WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public Categoria buscarPorID(Categoria entidade) throws SQLException {
        String sql = "SELECT ID, Nome, Descricao FROM Categoria WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getId());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            entidade.setId(rs.getInt("ID"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setDescricao(rs.getString("Descricao"));
            
            cont++;
        }

        if(cont == 0){
            entidade = new Categoria();
        }

        rs.close();
        ps.close();
        return entidade;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        String sql = "SELECT ID, Nome, Descricao FROM Categoria";
        PreparedStatement ps = connection.prepareStatement(sql);

        List<Categoria> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Categoria entidade = new Categoria();
            entidade.setId(rs.getInt("ID"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setDescricao(rs.getString("Descricao"));
            
            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }

    @Override
    public Categoria buscarPorChaveSecundaria(Categoria entidade) throws SQLException {
       String sql = "SELECT ID, Nome, Descricao FROM Categoria WHERE Nome LIKE ?%";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            entidade.setId(rs.getInt("ID"));
            entidade.setNome(rs.getString("Nome"));
            entidade.setDescricao(rs.getString("Descricao"));
            
            cont++;
        }

        if(cont == 0){
            entidade = new Categoria();
        }

        rs.close();
        ps.close();
        return entidade;
    }

}