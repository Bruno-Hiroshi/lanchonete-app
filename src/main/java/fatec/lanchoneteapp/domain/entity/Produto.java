package fatec.lanchoneteapp.domain.entity;

public class Produto {
    private int id;
    private String nome;
    private int qntdEstoq;
    private double valorUn;
    private Categoria categoria;
    private Fornecedor fornecedor;

    public Produto() {
        super();
    }
    public Produto(int id) {
        super();
        this.id = id;
    }
    public Produto(int id, String nome, int qntdEstoq, double valorUn, Categoria categoria, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.qntdEstoq = qntdEstoq;
        this.valorUn = valorUn;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
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
    public Fornecedor getFornecedor() {
        return fornecedor;
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
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void atualizarQtdEstoque(int qtdCompra) {
        qntdEstoq -= qtdCompra;
    }
}