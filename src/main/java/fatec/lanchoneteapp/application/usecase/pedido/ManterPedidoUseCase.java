package fatec.lanchoneteapp.application.usecase.pedido;

import fatec.lanchoneteapp.domain.entity.Cliente;
import fatec.lanchoneteapp.domain.entity.ItemPedido;
import fatec.lanchoneteapp.domain.entity.Pedido;
import fatec.lanchoneteapp.domain.entity.Produto;

import java.time.LocalDate;
import java.util.List;

public class ManterPedidoUseCase {
    public void criarPedido(Pedido pedido, Cliente cliente, List<ItemPedido> itensPedido) {
        pedido.setItensPedido(itensPedido);
        pedido.setData(LocalDate.now());
        pedido.setStatus("Criado");
        pedido.setCliente(cliente);

        pedido.calcularValorTotal();
    }

    public void adicionarItem(Pedido pedido, Produto produto, ItemPedido item) throws IllegalArgumentException{
        if(item.getQtd() > produto.getQntdEstoq()){
            throw new IllegalArgumentException("Quantidade insuficiente no estoque");
        }

        item.setValorUnit(produto.getValorUn());
        item.calcularValorTotal();

        pedido.adicionarItem(item);
        produto.atualizarQtdEstoque(item.getQtd());
    }

    public void removerItem(Pedido pedido, ItemPedido item) {
        pedido.removerItem(item);
    }

    public void atualizarQuantidadeItem(Pedido pedido, ItemPedido item, int novaQtd) {
        pedido.atualizarQuantidadeItem(item, novaQtd);
    }

    public void atualizarStatus(Pedido pedido, String novoStatus) {
        pedido.atualizarStatus(novoStatus);
    }
}
