package fatec.lanchoneteapp.application.dto;

public record CargoDTO(
        int id,
        String nome,
        double salario,
        String descricao
) {
    public int getId() {
        return id;
    }
    public String getNome(){
        return nome;
    }
    public double getSalario() {
        return salario;
    }
    public String getDescricao(){
        return descricao;
    }
}
