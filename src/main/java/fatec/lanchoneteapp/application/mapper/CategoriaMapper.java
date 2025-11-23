package fatec.lanchoneteapp.application.mapper;

import fatec.lanchoneteapp.application.dto.CategoriaDTO;
import fatec.lanchoneteapp.domain.entity.Categoria;

public class CategoriaMapper {
    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Categoria(
                dto.id(),
                dto.nome(),
                dto.descricao()
        );
    }
}
