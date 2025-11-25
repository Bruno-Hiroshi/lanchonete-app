package fatec.lanchoneteapp.application.dto;

public record CategoriaDTO (
        int id,
        String nome,
        String descricao
) {
    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
