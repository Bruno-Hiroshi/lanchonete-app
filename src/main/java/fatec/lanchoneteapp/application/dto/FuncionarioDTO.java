package fatec.lanchoneteapp.application.dto;

import fatec.lanchoneteapp.domain.entity.Cargo;

import java.time.LocalDate;

public record FuncionarioDTO(
        int id,
        String nome,
        String tel,
        LocalDate dataContrato,
        Cargo cargo
) { }
