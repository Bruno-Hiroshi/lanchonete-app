package fatec.lanchoneteapp.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int nPedido;
    private double valorTotal;
    private List<ItemPedido> itensPedido;
    private LocalDate data;
    private String status;
    private Cliente cliente;

    public Pedido(){

    }

    public Pedido(int nPedido){
        super();
        this.nPedido = nPedido;
        this.itensPedido = new ArrayList<>();
    }

    public Pedido(int nPedido, double valorTotal, List<ItemPedido> itensPedido, LocalDate data, String status, Cliente cliente) {
        this.cliente = cliente;
        this.data = data;
        this.itensPedido = itensPedido;
        this.nPedido = nPedido;
        this.status = status;
        this.valorTotal = valorTotal;
        this.itensPedido = new ArrayList<>();
    }

    public int getnPedido() {
        return nPedido;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }
    public LocalDate getData() {
        return data;
    }
    public String getStatus() {
        return status;
    }    
    public Cliente getCliente() {
        return cliente;
    }
    public void setnPedido(int nPedido) {
        this.nPedido = nPedido;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void calcularValorTotal(){
        this.valorTotal = 0;
        for (ItemPedido item : itensPedido) {
            this.valorTotal += item.getValorTotal();
        }
    }

    public void adicionarItem(ItemPedido item) {
        itensPedido.add(item);
        calcularValorTotal();
    }

    public void removerItem(ItemPedido item) {
        itensPedido.remove(item);
        calcularValorTotal();
    }

    public void atualizarQuantidadeItem(ItemPedido item, int novaQtd) {
        item.setQtd(novaQtd);
        calcularValorTotal();
    }

    public void atualizarStatus(String novoStatus) {
        setStatus(novoStatus);
    }
}

