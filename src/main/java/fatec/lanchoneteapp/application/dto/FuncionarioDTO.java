package fatec.lanchoneteapp.application.dto;

import fatec.lanchoneteapp.application.mapper.CargoMapper;
import fatec.lanchoneteapp.domain.entity.Cargo;

import java.time.LocalDate;

public record FuncionarioDTO(
        int id,
        String nome,
        String tel,
        String email,
        LocalDate dataContrato,
        Cargo cargo
) {
    static CargoMapper cargomapper = new CargoMapper();

    public int getId(){
        return id;
    }
    public String getNome() { return nome; }
    public String getTel() { return tel; }
    public String getEmail() { return email; }
    public LocalDate getDataContrato() { return dataContrato; }
    public String getCargo() { return cargo.getNome(); }
    public double getSalario() { return cargo.getSalario(); }
    public CargoDTO getCargoDTO(){
        return cargomapper.toDTO(cargo);
    }
}
