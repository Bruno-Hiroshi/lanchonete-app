package fatec.lanchoneteapp.domain.entity;

public class Produto {
    private int id;
    private String nome;
    private int qntdEstoq;
    private double valorUn;
    private Categoria categoria;

    public Produto() {
        super();
    }

    public Produto(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getQntdEstoq() {
        return qntdEstoq;
    }
    public double getValorUn() {
        return valorUn;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setQntdEstoq(int qntdEstoq) {
        this.qntdEstoq = qntdEstoq;
    }
    public void setValorUn(double valorUn) {
        this.valorUn = valorUn;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void atualizarQtdEstoque(int qtdCompra) {
        qntdEstoq -= qtdCompra;
    }
}