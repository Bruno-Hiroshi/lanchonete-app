package fatec.lanchoneteapp.application.dto;

import fatec.lanchoneteapp.domain.entity.Categoria;

public record ProdutoDTO(
        int id,
        String nome,
        int qtdEstoque,
        double valorUn,
        Categoria categoria
) {}
