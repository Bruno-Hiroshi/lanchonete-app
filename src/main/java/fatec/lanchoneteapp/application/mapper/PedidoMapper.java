package fatec.lanchoneteapp.application.mapper;

import fatec.lanchoneteapp.application.dto.PedidoDTO;
import fatec.lanchoneteapp.domain.entity.Pedido;

public class PedidoMapper {
    public PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        return new PedidoDTO(
                pedido.getnPedido(),
                pedido.getValorTotal(),
                pedido.getItensPedido(),
                pedido.getData(),
                pedido.getStatus(),
                pedido.getCliente()
        );
    }
}
