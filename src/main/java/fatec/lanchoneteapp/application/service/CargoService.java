package fatec.lanchoneteapp.application.service;

import fatec.lanchoneteapp.application.exception.CargoInvalidoException;
import fatec.lanchoneteapp.application.exception.CargoNaoEncontradoException;
import fatec.lanchoneteapp.application.repository.RepositoryNoReturn;
import fatec.lanchoneteapp.domain.entity.Cargo;

import java.sql.SQLException;
import java.util.List;

public class CargoService {
    private final RepositoryNoReturn<Cargo> repository;

    public CargoService(RepositoryNoReturn<Cargo> repository) {
        this.repository = repository;
    }

    public void criarCargo(Cargo cargo) throws SQLException, CargoInvalidoException {
        if(!validarCargo(cargo))
            throw new CargoInvalidoException("Cargo já cadastrado");

        repository.salvar(cargo);
    }

    public void atualizarCargo(Cargo cargo) throws SQLException {
        repository.atualizar(cargo);
    }

    public Cargo buscarCargo(int idCargo) throws SQLException, CargoNaoEncontradoException {
        Cargo cargo = repository.buscarPorID(new Cargo(idCargo));

        if(cargo == null)
            throw new CargoNaoEncontradoException("Cargo não encontrado");

        return cargo;
    }

    public void excluirCargo(Cargo cargo) throws SQLException {
        repository.excluir(cargo);
    }

    public List<Cargo> listarCargos() throws SQLException {
        return repository.listar();
    }

    public boolean validarCargo(Cargo cargo) throws SQLException {
        try {
            buscarDuplicata(cargo);
            return true;
        } catch(CargoInvalidoException e) {
            return false;
        }
    }

    private void buscarDuplicata(Cargo cargo) throws SQLException, CargoNaoEncontradoException{
        if(repository.buscarPorChaveSecundaria(cargo) != null)
            throw new CargoInvalidoException("Cargo inválido");
    }
}
