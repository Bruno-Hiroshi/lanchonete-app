package fatec.lanchoneteapp.adapters.repository;

import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Categoria;
import fatec.lanchoneteapp.domain.entity.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository implements RepositoryNoReturn<Produto> {
    private Connection connection;

    public ProdutoRepository(Connection connection){
        this.connection = connection; 
    }

    @Override
    public void salvar(Produto entidade) throws SQLException {
        String sql = "INSERT INTO Produto(?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setInt(2, entidade.getQntdEstoq());
        ps.setDouble(3, entidade.getValorUn());
        ps.setInt(4, entidade.getCategoria().getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void atualizar(Produto entidade) throws SQLException {
        String sql = "UPDATE Produto SET Nome = ?, QtdEstoque = ?, ValorUnit = ?, ID_Categoria = ? WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, entidade.getNome());
        ps.setInt(2, entidade.getQntdEstoq());
        ps.setDouble(3, entidade.getValorUn());
        ps.setInt(4, entidade.getCategoria().getId());
        ps.setInt(5, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluir(Produto entidade) throws SQLException {
        String sql = "DELETE FROM Produto WHERE ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public Produto buscarPorID(Produto entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.ID AS ID_Produto, p.Nome AS Nome_Produto, p.QtdEstoque, p.ValorUnit, ");
        sql.append("c.ID AS ID_Categoria, c.Nome AS Nome_Categoria, c.Descricao ");
        sql.append("FROM Produto p INNER JOIN Categoria c ");
        sql.append("ON p.ID_Categoria = c.ID ");
        sql.append("WHERE p.ID = ?");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setInt(1, entidade.getId());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("ID_Categoria"));
            categoria.setNome(rs.getString("Nome_Categoria"));
            categoria.setDescricao(rs.getString("Descricao"));

            entidade.setId(rs.getInt("ID_Produto"));
            entidade.setNome(rs.getString("Nome_Produto"));
            entidade.setQntdEstoq(rs.getInt("QtdEstoque"));
            entidade.setValorUn(rs.getDouble("ValorUnit"));
            entidade.setCategoria(categoria);

            cont++;
        }

        if(cont == 0){
            entidade = new Produto();
        }

        rs.close();
        ps.close();
        return entidade;
    }

    @Override
    public List<Produto> listar() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.ID AS ID_Produto, p.Nome AS Nome_Produto, p.QtdEstoque, p.ValorUnit, ");
        sql.append("c.ID AS ID_Categoria, c.Nome AS Nome_Categoria, c.Descricao ");
        sql.append("FROM Produto p INNER JOIN Categoria c ");
        sql.append("ON p.ID_Categoria = c.ID");
        PreparedStatement ps = connection.prepareStatement(sql.toString());

        List<Produto> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("ID_Categoria"));
            categoria.setNome(rs.getString("Nome_Categoria"));
            categoria.setDescricao(rs.getString("Descricao"));

            Produto entidade = new Produto();
            entidade.setId(rs.getInt("ID_Produto"));
            entidade.setNome(rs.getString("Nome_Produto"));
            entidade.setQntdEstoq(rs.getInt("QtdEstoque"));
            entidade.setValorUn(rs.getDouble("ValorUnit"));
            entidade.setCategoria(categoria);

            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }

    @Override
    public Produto buscarPorChaveSecundaria(Produto entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.ID AS ID_Produto, p.Nome AS Nome_Produto, p.QtdEstoque, p.ValorUnit, ");
        sql.append("c.ID AS ID_Categoria, c.Nome AS Nome_Categoria, c.Descricao ");
        sql.append("FROM Produto p INNER JOIN Categoria c ");
        sql.append("ON p.ID_Categoria = c.ID ");
        sql.append("WHERE p.Nome LIKE ?%");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setString(1, entidade.getNome());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("ID_Categoria"));
            categoria.setNome(rs.getString("Nome_Categoria"));
            categoria.setDescricao(rs.getString("Descricao"));

            entidade.setId(rs.getInt("ID_Produto"));
            entidade.setNome(rs.getString("Nome_Produto"));
            entidade.setQntdEstoq(rs.getInt("QtdEstoque"));
            entidade.setValorUn(rs.getDouble("ValorUnit"));
            entidade.setCategoria(categoria);

            cont++;
        }

        if(cont == 0){
            entidade = new Produto();
        }

        rs.close();
        ps.close();
        return entidade;
    }
}
