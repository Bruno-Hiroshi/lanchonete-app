package fatec.lanchoneteapp.application.dto;

import fatec.lanchoneteapp.application.mapper.ClienteMapper;
import fatec.lanchoneteapp.domain.entity.Cliente;
import fatec.lanchoneteapp.domain.entity.ItemPedido;

import java.time.LocalDate;
import java.util.List;

public record PedidoDTO(
        int nPedido,
        double valorTotal,
        List<ItemPedido> itensPedido,
        LocalDate dataPedido,
        String status,
        Cliente cliente
) {
        static ClienteMapper clienteMapper = new ClienteMapper();

        public int getNPedido() {
                return nPedido;
        }
        public double getValorTotal(){
                return valorTotal;
        }
        public LocalDate getDataPedido(){
                return dataPedido;
        }
        public String getStatus(){
                return status;
        }
        public int getIdCliente(){
                return cliente.getId();
        }
        public String getNomeCliente(){
                return cliente.getNome();
        }
        public ClienteDTO getClienteDTO(){
                return clienteMapper.toDTO(cliente);
        }
        
}
