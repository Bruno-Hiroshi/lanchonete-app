package fatec.lanchoneteapp.domain.entity;

public class Cliente {
    private int id;
    private String nome;
    private String tel;
    private String logradouro;
    private int numero;
    private String cep;
    private String complemento;

    public Cliente(){}

    public Cliente(int id) {
        this.id = id;
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
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTel(String tel) {
        this.tel = tel;
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
}

