package fatec.lanchoneteapp.application.dto;

import fatec.lanchoneteapp.domain.entity.Cliente;
import fatec.lanchoneteapp.domain.entity.ItemPedido;

import java.time.LocalDate;
import java.util.List;

public record PedidoDTO(
        int nPedido,
        double valorTotal,
        List<ItemPedido> itensPedido,
        LocalDate data,
        String status,
        Cliente cliente
) {}
