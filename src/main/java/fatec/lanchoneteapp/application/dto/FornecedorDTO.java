package fatec.lanchoneteapp.application.dto;

public record FornecedorDTO(
        int id,
        String nome,
        String tel,
        String cnpj,
        String logradouro,
        int numero,
        String cep,
        String complemento
) { 
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
}
