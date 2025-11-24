package fatec.lanchoneteapp.adapters.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.ItemPedido;

public class ItemPedidoRepository implements RepositoryNoReturn<ItemPedido>{
    private Connection connection;

    public ItemPedidoRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public void salvar(ItemPedido entidade) throws SQLException {
        String sql = "INSERT INTO Item_Pedido(?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getNumPedido());
        ps.setInt(2, entidade.getIdProduto());
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
        ps.setInt(2, entidade.getIdProduto());
        ps.setInt(3, entidade.getQtd());
        ps.setDouble(4, entidade.getValorUnit());
        ps.setDouble(5, entidade.getValorTotal());
        ps.setInt(6, entidade.getNumPedido());
        ps.setInt(7, entidade.getIdProduto());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluir(ItemPedido entidade) throws SQLException {
        String sql = "DELETE FROM Item_Pedido WHERE Num_Pedido = ? AND ID_Produto = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getNumPedido());
        ps.setInt(2, entidade.getIdProduto());
        ps.execute();
        ps.close();
    }

    @Override
    public ItemPedido buscarPorID(ItemPedido entidade) throws SQLException {
        String sql = "SELECT Num_Pedido, ID_Produto, Qtd, ValorUnit, ValorTotalItem FROM Item_Pedido " +
                    "WHERE Num_Pedido = ? AND ID_Produto = ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getNumPedido());
        ps.setInt(2, entidade.getIdProduto());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            entidade.setNumPedido(rs.getInt("Num_Pedido"));
            entidade.setIdProduto(rs.getInt("ID_Produto"));
            entidade.setQtd(rs.getInt("Qtd"));
            entidade.setValorUnit(rs.getDouble("ValorUnit"));
            entidade.setValorTotal(rs.getDouble("ValorTotalItem"));

            cont++;
        }

        if(cont == 0){
            entidade = new ItemPedido();
        }

        rs.close();
        ps.close();
        return entidade;
    }

    @Override
    public List<ItemPedido> listar() throws SQLException {
        String sql = "SELECT Num_Pedido, ID_Produto, Qtd, ValorUnit, ValorTotalItem FROM Item_Pedido";
        PreparedStatement ps = connection.prepareStatement(sql);

        List<ItemPedido> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            ItemPedido entidade = new ItemPedido();
            entidade.setNumPedido(rs.getInt("Num_Pedido"));
            entidade.setIdProduto(rs.getInt("ID_Produto"));
            entidade.setQtd(rs.getInt("Qtd"));
            entidade.setValorUnit(rs.getDouble("ValorUnit"));
            entidade.setValorTotal(rs.getDouble("ValorTotalItem"));

            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }

    @Override
    public ItemPedido buscarPorChaveSecundaria(ItemPedido entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ip.Num_Pedido, ip.ID_Produto, ip.Qtd, ip.ValorUnit, ipValorTotalItem, ");
        sql.append("p.Nome AS Nome_Produto ");
        sql.append("FROM ItemPedido ip INNER JOIN Produto p ");
        sql.append("ON ip.ID_Produto = p.ID ");
        sql.append("WHERE p.Nome LIKE ?%");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setString(1, entidade.getNomeProduto());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            entidade.setNumPedido(rs.getInt("Num_Pedido"));
            entidade.setIdProduto(rs.getInt("ID_Produto"));
            entidade.setQtd(rs.getInt("Qtd"));
            entidade.setValorUnit(rs.getDouble("ValorUnit"));
            entidade.setValorTotal(rs.getDouble("ValorTotalItem"));

            cont++;
        }

        if(cont == 0){
            entidade = new ItemPedido();
        }

        rs.close();
        ps.close();
        return entidade;
    }
}
