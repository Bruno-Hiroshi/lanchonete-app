package fatec.lanchoneteapp.domain.entity;

public class ItemPedido {
    private int nPedido;
    private Produto produto;
    private int qtd;
    private double valorUnit;
    private double valorTotal;

    public ItemPedido() {
        super();
    }

    public ItemPedido(int nPedido, Produto produto) {
        super();
        this.nPedido = nPedido;
        this.produto = produto;
    }

    public ItemPedido(int nPedido, Produto produto, int qtd) {
        super();
        this.nPedido = nPedido;
        this.produto = produto;
        this.qtd = qtd;
    }

    public int getNumPedido() {
        return nPedido;
    }
    public Produto getProduto() {
        return produto;
    }
    public int getQtd() {
        return qtd;
    }
    public double getValorUnit() {
        return valorUnit;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setNumPedido(int nPedido) {
        this.nPedido = nPedido;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    public void setValorUnit(double valorUnit) {
        this.valorUnit = valorUnit;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public void calcularValorTotal(){
        this.valorTotal = this.valorUnit * this.qtd;
    }
}
