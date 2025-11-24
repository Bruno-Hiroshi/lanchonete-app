package fatec.lanchoneteapp.adapters.repository;

import fatec.lanchoneteapp.application.repository.RepositoryReturn;
import fatec.lanchoneteapp.domain.entity.Cliente;
import fatec.lanchoneteapp.domain.entity.Pedido;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository implements RepositoryReturn<Pedido> {
    private Connection connection;

    public PedidoRepository(Connection connection){
        this.connection = connection; 
    }

    @Override
    public int salvar(Pedido entidade) throws SQLException{
        String sql = "INSERT INTO Pedido(?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, entidade.getValorTotal());
        ps.setDate(2, Date.valueOf(entidade.getData()));
        ps.setString(3, entidade.getStatus());
        ps.setInt(4, entidade.getCliente().getId());
        ps.execute();

        int numPedido = 0;
        ResultSet rs = ps.getGeneratedKeys();
        
        if(rs.next()){
            numPedido = rs.getInt(1);
        }
        
        ps.close();
        return numPedido;
    }   

    @Override
    public void atualizar(Pedido entidade) throws SQLException {
        String sql = "UPDATE Pedido SET ValorTotal = ?, DataPedido = ?, StatusPedido = ?, ID_Cliente = ? WHERE Num_Pedido = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDouble(1, entidade.getValorTotal());
        ps.setDate(2, Date.valueOf(entidade.getData()));
        ps.setString(3, entidade.getStatus());
        ps.setInt(4, entidade.getCliente().getId());
        ps.setInt(5, entidade.getnPedido());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluir(Pedido entidade) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE Num_Pedido = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, entidade.getnPedido());
        ps.execute();
        ps.close();
    }

    @Override
    public Pedido buscarPorID(Pedido entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.Num_Pedido, p.ValorTotal, p.DataPedido, p.StatusPedido, ");
        sql.append("c.ID, c.Nome, c.Telefone, c.Logradouro, c.Numero, c.CEP, c.Complemento ");
        sql.append("FROM Pedido p INNER JOIN Cliente c ");
        sql.append("ON p.ID_Cliente = c.ID ");
        sql.append("WHERE p.Num_Pedido = ?");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setInt(1, entidade.getnPedido());

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("ID"));
            cliente.setNome(rs.getString("Nome"));
            cliente.setTel(rs.getString("Telefone"));
            cliente.setLogradouro(rs.getString("Logradouro"));
            cliente.setNumero(rs.getInt("Numero"));
            cliente.setCep(rs.getString("CEP"));
            cliente.setComplemento(rs.getString("Complemento"));

            entidade.setnPedido(rs.getInt("Num_Pedido"));
            entidade.setValorTotal(rs.getDouble("ValorTotal"));
            entidade.setData(rs.getDate("DataPedido").toLocalDate());
            entidade.setStatus(rs.getString("StatusPedido"));
            entidade.setCliente(cliente);

            cont++;
        }

        if(cont == 0){
            entidade = new Pedido();
            Cliente cliente = new Cliente();
            entidade.setCliente(cliente);
        }

        rs.close();
        ps.close();
        return entidade;
    }

    @Override
    public List<Pedido> listar() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.Num_Pedido, p.ValorTotal, p.DataPedido, p.StatusPedido, ");
        sql.append("c.ID, c.Nome, c.Telefone, c.Logradouro, c.Numero, c.CEP, c.Complemento ");
        sql.append("FROM Pedido p INNER JOIN Cliente c ");
        sql.append("ON p.ID_Cliente = c.ID");
        PreparedStatement ps = connection.prepareStatement(sql.toString());

        List<Pedido> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("ID"));
            cliente.setNome(rs.getString("Nome"));
            cliente.setTel(rs.getString("Telefone"));
            cliente.setLogradouro(rs.getString("Logradouro"));
            cliente.setNumero(rs.getInt("Numero"));
            cliente.setCep(rs.getString("CEP"));
            cliente.setComplemento(rs.getString("Complemento"));

            Pedido entidade = new Pedido();
            entidade.setnPedido(rs.getInt("Num_Pedido"));
            entidade.setValorTotal(rs.getDouble("ValorTotal"));
            entidade.setData(rs.getDate("DataPedido").toLocalDate());
            entidade.setStatus(rs.getString("StatusPedido"));
            entidade.setCliente(cliente);

            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }

    public List<Pedido> historicoPedidos(Cliente cliente) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.Num_Pedido, p.ValorTotal, p.DataPedido, p.StatusPedido, ");
        sql.append("c.ID, c.Nome, c.Telefone, c.Logradouro, c.Numero, c.CEP, c.Complemento ");
        sql.append("FROM Pedido p INNER JOIN Cliente c ");
        sql.append("ON p.ID_Cliente = c.ID ");
        sql.append("WHERE p.ID_Cliente = ?");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setInt(1, cliente.getId());

        List<Pedido> entidades = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            cliente.setId(rs.getInt("ID"));
            cliente.setNome(rs.getString("Nome"));
            cliente.setTel(rs.getString("Telefone"));
            cliente.setLogradouro(rs.getString("Logradouro"));
            cliente.setNumero(rs.getInt("Numero"));
            cliente.setCep(rs.getString("CEP"));
            cliente.setComplemento(rs.getString("Complemento"));

            Pedido entidade = new Pedido();
            entidade.setnPedido(rs.getInt("Num_Pedido"));
            entidade.setValorTotal(rs.getDouble("ValorTotal"));
            entidade.setData(rs.getDate("DataPedido").toLocalDate());
            entidade.setStatus(rs.getString("StatusPedido"));
            entidade.setCliente(cliente);

            entidades.add(entidade);
        }

        rs.close();
        ps.close();
        return entidades;
    }

    @Override
    public Pedido buscarPorChaveSecundaria(Pedido entidade) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.Num_Pedido, p.ValorTotal, p.DataPedido, p.StatusPedido, ");
        sql.append("c.ID, c.Nome, c.Telefone, c.Logradouro, c.Numero, c.CEP, c.Complemento ");
        sql.append("FROM Pedido p INNER JOIN Cliente c ");
        sql.append("ON p.ID_Cliente = c.ID ");
        sql.append("WHERE p.DataPedido = ?");
        PreparedStatement ps = connection.prepareStatement(sql.toString());
        ps.setDate(1, Date.valueOf(entidade.getData()));

        int cont = 0;
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("ID"));
            cliente.setNome(rs.getString("Nome"));
            cliente.setTel(rs.getString("Telefone"));
            cliente.setLogradouro(rs.getString("Logradouro"));
            cliente.setNumero(rs.getInt("Numero"));
            cliente.setCep(rs.getString("CEP"));
            cliente.setComplemento(rs.getString("Complemento"));

            entidade.setnPedido(rs.getInt("Num_Pedido"));
            entidade.setValorTotal(rs.getDouble("ValorTotal"));
            entidade.setData(rs.getDate("DataPedido").toLocalDate());
            entidade.setStatus(rs.getString("StatusPedido"));
            entidade.setCliente(cliente);

            cont++;
        }

        if(cont == 0){
            entidade = new Pedido();
            Cliente cliente = new Cliente();
            entidade.setCliente(cliente);
        }

        rs.close();
        ps.close();
        return entidade;
    }
}
