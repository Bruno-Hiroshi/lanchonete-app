package fatec.lanchoneteapp.application.mapper;

import fatec.lanchoneteapp.application.dto.ProdutoDTO;
import fatec.lanchoneteapp.domain.entity.Produto;

public class ProdutoMapper {
    public ProdutoDTO toDTO(Produto produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getQntdEstoq(),
                produto.getValorUn(),
                produto.getCategoria(),
                produto.getFornecedor()
        );
    }

    public Produto toEntity(ProdutoDTO dto) {
        if(dto == null){
            return null;
        }

        return new Produto(
                dto.id(),
                dto.nome(),
                dto.qtdEstoque(),
                dto.valorUn(),
                dto.categoria(),
                dto.fornecedor()
        );
    }
}
