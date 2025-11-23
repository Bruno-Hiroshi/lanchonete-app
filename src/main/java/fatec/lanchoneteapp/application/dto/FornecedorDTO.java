package fatec.lanchoneteapp.application.dto;

import fatec.lanchoneteapp.domain.entity.Produto;

import java.util.List;

public record FornecedorDTO(
        int id,
        String nome,
        String tel,
        String cnpj,
        String logradouro,
        int numero,
        String cep,
        String complemento,
        List<Produto> produtos
) { }
