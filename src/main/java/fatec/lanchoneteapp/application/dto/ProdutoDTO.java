package fatec.lanchoneteapp.application.dto;

import fatec.lanchoneteapp.domain.entity.Categoria;
import fatec.lanchoneteapp.domain.entity.Fornecedor;

public record ProdutoDTO(
        int id,
        String nome,
        int qtdEstoque,
        double valorUn,
        Categoria categoria,
        Fornecedor fornecedor
) {}
