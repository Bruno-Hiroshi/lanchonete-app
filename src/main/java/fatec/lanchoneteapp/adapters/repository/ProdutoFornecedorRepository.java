package fatec.lanchoneteapp.adapters.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.ProdutoFornecedor;

public class ProdutoFornecedorRepository implements RepositoryNoReturn<ProdutoFornecedor>{
    private Connection connection;

    public ProdutoFornecedorRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(ProdutoFornecedor entidade) throws SQLException {
        String sql = "INSERT INTO Produto_Fornecedor(?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getIdProduto());
        ps.setInt(2, entidade.getIdFornecedor());
        ps.execute();
        ps.close();
        
    }

    @Override
    public void atualizar(ProdutoFornecedor entidade) throws SQLException {
        String sql = "UPDATE Produto_Fornecedor SET ID_Produto = ? AND ID_Fornecedor = ? " + 
                    "WHERE ID_Produto = ?, ID_Fornecedor = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getIdProduto());
        ps.setInt(2, entidade.getIdFornecedor());
        ps.setInt(3, entidade.getIdProduto());
        ps.setInt(4, entidade.getIdFornecedor());
        ps.execute();
        ps.close();

    }

    @Override
    public void excluir(ProdutoFornecedor entidade) throws SQLException {
        String sql = "DELETE FROM Produto_Fornecedor WHERE ID_Produto = ? AND ID_Fornecedor = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getIdProduto());
        ps.setInt(2, entidade.getIdFornecedor());
        ps.execute();
        ps.close();

    }

    @Override
    public ProdutoFornecedor buscarPorID(ProdutoFornecedor entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT pf.ID_Produto, pf.ID_Fornecedor, ");
        sql.append("p.Nome AS Nome_Produto, ");
        sql.append("f.Nome AS Nome_Fornecedor ");
        sql.append("FROM Produto_Fornecedor pf INNER JOIN Produto p ");
        sql.append("ON pf.ID_Produto = p.ID ");
        sql.append("INNER JOIN Fornecedor f ");
        sql.append("ON pf.ID_Fornecedor = f.ID ");
        sql.append("WHERE pf.ID_Produto = ? AND pf.ID_Fornecedor = ?");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setInt(1, entidade.getIdProduto());
        ps.setInt(2, entidade.getIdFornecedor());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            entidade.setIdProduto(rs.getInt("ID_Produto"));
            entidade.setIdFornecedor(rs.getInt("ID_Fornecedor"));
            entidade.setNomeProduto(rs.getString("Nome_Produto"));
            entidade.setNomeFornecedor(rs.getString("Nome_Fornecedor"));

            cont++;
        }

        if(cont == 0){
            entidade = new ProdutoFornecedor();
        }

        rs.close();
        ps.close();
        return entidade;
    }

    @Override
    public List<ProdutoFornecedor> listar() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT pf.ID_Produto, pf.ID_Fornecedor, ");
        sql.append("p.Nome AS Nome_Produto, ");
        sql.append("f.Nome AS Nome_Fornecedor ");
        sql.append("FROM Produto_Fornecedor pf INNER JOIN Produto p ");
        sql.append("ON pf.ID_Produto = p.ID ");
        sql.append("INNER JOIN Fornecedor f ");
        sql.append("ON pf.ID_Fornecedor = f.ID");
        PreparedStatement ps = connection.prepareStatement(sql.toString());

        List<ProdutoFornecedor> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ProdutoFornecedor entidade = new ProdutoFornecedor();
            entidade.setIdProduto(rs.getInt("ID_Produto"));
            entidade.setIdFornecedor(rs.getInt("ID_Fornecedor"));
            entidade.setNomeProduto(rs.getString("Nome_Produto"));
            entidade.setNomeFornecedor(rs.getString("Nome_Fornecedor"));

            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }

    @Override
    public ProdutoFornecedor buscarPorChaveSecundaria(ProdutoFornecedor entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT pf.ID_Produto, pf.ID_Fornecedor, ");
        sql.append("p.Nome AS Nome_Produto, ");
        sql.append("f.Nome AS Nome_Fornecedor ");
        sql.append("FROM Produto_Fornecedor pf INNER JOIN Produto p ");
        sql.append("ON pf.ID_Produto = p.ID ");
        sql.append("INNER JOIN Fornecedor f ");
        sql.append("ON pf.ID_Fornecedor = f.ID ");
        sql.append("WHERE p.Nome LIKE ?% AND f.Nome LIKE ?%");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setString(1, entidade.getNomeProduto());
        ps.setString(2, entidade.getNomeFornecedor());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            entidade.setIdProduto(rs.getInt("ID_Produto"));
            entidade.setIdFornecedor(rs.getInt("ID_Fornecedor"));
            entidade.setNomeProduto(rs.getString("Nome_Produto"));
            entidade.setNomeFornecedor(rs.getString("Nome_Fornecedor"));

            cont++;
        }

        if(cont == 0){
            entidade = new ProdutoFornecedor();
        }

        rs.close();
        ps.close();
        return entidade;
    }
    
}
