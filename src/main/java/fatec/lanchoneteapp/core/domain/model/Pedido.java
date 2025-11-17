import java.time.LocalDate;

public class Pedido {
long nPedido;
long valorTotal;
Produto produto;
LocalDate data;
String status;

    public LocalDate getData() {
        return data;
    }

    public long getnPedido() {
        return nPedido;
    }

    public Produto getProduto() {
        return produto;
    }

   

    public String getStatus() {
        return status;
    }

    public long getValorTotal() {
        return valorTotal;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setnPedido(long nPedido) {
        this.nPedido = nPedido;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setValorTotal(long valorTotal) {
        this.valorTotal = valorTotal;
    }

}

