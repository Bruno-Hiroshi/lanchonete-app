package fatec.lanchoneteapp.adapters.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Categoria;
import fatec.lanchoneteapp.domain.entity.ItemPedido;
import fatec.lanchoneteapp.domain.entity.Produto;

public class ItemPedidoRepository implements RepositoryNoReturn<ItemPedido>{
    private Connection connection;

    public ItemPedidoRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public void salvar(ItemPedido entidade) throws SQLException {
        String sql = "INSERT INTO Item_Pedido(Num_Pedido, ID_Produto, Qtd, ValorUnit, ValorTotalItem) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getNumPedido());
        ps.setInt(2, entidade.getProduto().getId());
        ps.setInt(3, entidade.getQtd());
        ps.setDouble(4, entidade.getValorUnit());
        ps.setDouble(5, entidade.getValorTotal());
        ps.execute();
        ps.close();
    }

    @Override
    public void atualizar(ItemPedido entidade) throws SQLException {
        String sql = "UPDATE Item_Pedido SET Num_Pedido = ?, ID_Produto = ?, Qtd = ?, ValorUnit = ?, ValorTotalItem = ? " +
                    "WHERE Num_Pedido = ? AND ID_Produto = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getNumPedido());
        ps.setInt(2, entidade.getProduto().getId());
        ps.setInt(3, entidade.getQtd());
        ps.setDouble(4, entidade.getValorUnit());
        ps.setDouble(5, entidade.getValorTotal());
        ps.setInt(6, entidade.getNumPedido());
        ps.setInt(7, entidade.getProduto().getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluir(ItemPedido entidade) throws SQLException {
        String sql = "DELETE FROM Item_Pedido WHERE Num_Pedido = ? AND ID_Produto = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getNumPedido());
        ps.setInt(2, entidade.getProduto().getId());
        ps.execute();
        ps.close();
    }

    @Override
    public ItemPedido buscarPorID(ItemPedido entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ip.Num_Pedido, ip.Qtd, ip.ValorUnit, ip.ValorTotalItem, ");
        sql.append("p.ID AS ID_Produto, p.Nome AS Nome_Produto,  p.QtdEstoque, p.ValorUnit ");
        sql.append("c.ID AS ID_Categoria c.Nome, c.Descricao ");
        sql.append("FROM ItemPedido ip INNER JOIN Produto p ");
        sql.append("ON ip.ID_Produto = p.ID ");
        sql.append("INNER JOIN Categoria c ");
        sql.append("ON p.ID_Categoria = c.ID ");
        sql.append("WHERE ip.Num_Pedido = ? AND ip.ID_Produto = ?");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setInt(1, entidade.getNumPedido());
        ps.setInt(2, entidade.getProduto().getId());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("ID_Categoria"));
            categoria.setNome(rs.getString("Nome"));
            categoria.setDescricao(rs.getString("Descricao"));
            
            Produto produto = new Produto();
            produto.setId(rs.getInt("ID_Produto"));
            produto.setNome(rs.getString("Nome_Produto"));
            produto.setQntdEstoq(rs.getInt("QtdEstoque"));
            produto.setValorUn(rs.getDouble("ValorUnit"));
            produto.setCategoria(categoria);
            
            entidade.setNumPedido(rs.getInt("Num_Pedido"));
            entidade.setQtd(rs.getInt("Qtd"));
            entidade.setValorUnit(rs.getDouble("ValorUnit"));
            entidade.setValorTotal(rs.getDouble("ValorTotalItem"));
            entidade.setProduto(produto);

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
    public List<ItemPedido> listar() throws SQLException {
         StringBuilder sql = new StringBuilder();
        sql.append("SELECT ip.Num_Pedido, ip.Qtd, ip.ValorUnit, ip.ValorTotalItem, ");
        sql.append("p.ID AS ID_Produto, p.Nome AS Nome_Produto,  p.QtdEstoque, p.ValorUnit ");
        sql.append("c.ID AS ID_Categoria c.Nome, c.Descricao ");
        sql.append("FROM ItemPedido ip INNER JOIN Produto p ");
        sql.append("ON ip.ID_Produto = p.ID ");
        sql.append("INNER JOIN Categoria c ");
        sql.append("ON p.ID_Categoria = c.ID");
        PreparedStatement ps = connection.prepareStatement(sql.toString());

        List<ItemPedido> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("ID_Categoria"));
            categoria.setNome(rs.getString("Nome"));
            categoria.setDescricao(rs.getString("Descricao"));
            
            Produto produto = new Produto();
            produto.setId(rs.getInt("ID_Produto"));
            produto.setNome(rs.getString("Nome_Produto"));
            produto.setQntdEstoq(rs.getInt("QtdEstoque"));
            produto.setValorUn(rs.getDouble("ValorUnit"));
            produto.setCategoria(categoria);
            
            ItemPedido entidade = new ItemPedido();
            entidade.setNumPedido(rs.getInt("Num_Pedido"));
            entidade.setQtd(rs.getInt("Qtd"));
            entidade.setValorUnit(rs.getDouble("ValorUnit"));
            entidade.setValorTotal(rs.getDouble("ValorTotalItem"));
            entidade.setProduto(produto);

            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }

    @Override
    public ItemPedido buscarPorChaveSecundaria(ItemPedido entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ip.Num_Pedido, ip.Qtd, ip.ValorUnit, ip.ValorTotalItem, ");
        sql.append("p.ID AS ID_Produto, p.Nome AS Nome_Produto,  p.QtdEstoque, p.ValorUnit ");
        sql.append("c.ID AS ID_Categoria c.Nome, c.Descricao ");
        sql.append("FROM ItemPedido ip INNER JOIN Produto p ");
        sql.append("ON ip.ID_Produto = p.ID ");
        sql.append("INNER JOIN Categoria c ");
        sql.append("ON p.ID_Categoria = c.ID ");
        sql.append("WHERE p.Nome = ?");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setString(1, entidade.getProduto().getNome());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("ID_Categoria"));
            categoria.setNome(rs.getString("Nome"));
            categoria.setDescricao(rs.getString("Descricao"));
            
            Produto produto = new Produto();
            produto.setId(rs.getInt("ID_Produto"));
            produto.setNome(rs.getString("Nome_Produto"));
            produto.setQntdEstoq(rs.getInt("QtdEstoque"));
            produto.setValorUn(rs.getDouble("ValorUnit"));
            produto.setCategoria(categoria);
            
            entidade.setNumPedido(rs.getInt("Num_Pedido"));
            entidade.setQtd(rs.getInt("Qtd"));
            entidade.setValorUnit(rs.getDouble("ValorUnit"));
            entidade.setValorTotal(rs.getDouble("ValorTotalItem"));
            entidade.setProduto(produto);

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
