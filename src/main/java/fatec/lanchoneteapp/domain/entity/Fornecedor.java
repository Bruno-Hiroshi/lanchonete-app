package fatec.lanchoneteapp.domain.entity;

import java.util.List;

public class Fornecedor {
    private int id;
    private String nome;
    private String tel;
    private String cnpj;
    private String logradouro;
    private int numero;
    private String cep;
    private String complemento;
    private List<Produto> produtos;

    public Fornecedor(){}
    public Fornecedor(int id) {
        this.id = id;
    }
    public Fornecedor(String nome, String tel, String cnpj, String logradouro, int numero, String cep, String complemento) {
        this.nome = nome;
        this.tel = tel;
        this.cnpj = cnpj;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
    }
    public Fornecedor(int id, String nome, String tel, String cnpj, String logradouro, int numero, String cep, String complemento, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.tel = tel;
        this.cnpj = cnpj;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getTel() {
        return tel;
    }
    public String getCnpj() {
        return cnpj;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public int getNumero() {
        return numero;
    }
    public String getCep() {
        return cep;
    }
    public String getComplemento() {
        return complemento;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
