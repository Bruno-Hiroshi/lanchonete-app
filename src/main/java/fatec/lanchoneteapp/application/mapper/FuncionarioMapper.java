package fatec.lanchoneteapp.application.mapper;

import fatec.lanchoneteapp.application.dto.FuncionarioDTO;
import fatec.lanchoneteapp.domain.entity.Funcionario;

public class FuncionarioMapper {
    public Funcionario toEntity(FuncionarioDTO dto) {
        if (dto == null) {

        }

        return new Funcionario(
                dto.nome(),
                dto.tel(),
                dto.dataContrato(),
                dto.cargo()
        );
    }

    public FuncionarioDTO toDTO(Funcionario funcionario) {
        if (funcionario == null) {
            return null;
        }

        return new FuncionarioDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getTel(),
                funcionario.getDataContrato(),
                funcionario.getCargo()
        );
    }
}
