package fatec.lanchoneteapp.application.mapper;

import fatec.lanchoneteapp.application.dto.FornecedorDTO;
import fatec.lanchoneteapp.domain.entity.Fornecedor;

public class FornecedorMapper {
    public Fornecedor toEntity(FornecedorDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Fornecedor(
                dto.id(),
                dto.nome(),
                dto.tel(),
                dto.cnpj(),
                dto.logradouro(),
                dto.numero(),
                dto.cep(),
                dto.complemento()
        );
    }

    public FornecedorDTO toDTO(Fornecedor fornecedor) {
        if (fornecedor == null) {
            return null;
        }

        return new FornecedorDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getTel(),
                fornecedor.getCnpj(),
                fornecedor.getLogradouro(),
                fornecedor.getNumero(),
                fornecedor.getCep(),
                fornecedor.getComplemento()
        );
    }
}
