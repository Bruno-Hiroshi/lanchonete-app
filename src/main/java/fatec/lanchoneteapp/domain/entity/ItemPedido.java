package fatec.lanchoneteapp.domain.entity;

public class ItemPedido {
    private int nPedido;
    private int idProduto;
    private int qtd;
    private double valorUnit;
    private double valorTotal;

    public ItemPedido() {
        super();
    }

    public ItemPedido(int nPedido, int idProduto) {
        super();
        this.nPedido = nPedido;
        this.idProduto = idProduto;
    }

    public ItemPedido(int nPedido, int idProduto, int qtd) {
        super();
        this.nPedido = nPedido;
        this.idProduto = idProduto;
        this.qtd = qtd;
    }

    public int getNumPedido() {
        return nPedido;
    }
    public int getIdProduto() {
        return idProduto;
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
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
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
