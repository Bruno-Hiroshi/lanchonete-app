package fatec.lanchoneteapp.application.mapper;

import fatec.lanchoneteapp.application.dto.ClienteDTO;
import fatec.lanchoneteapp.domain.entity.Cliente;

public class ClienteMapper {
    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setId(dto.id());
        cliente.setNome(dto.nome());
        cliente.setTel(dto.tel());
        cliente.setLogradouro(dto.logradouro());
        cliente.setNumero(dto.numero());
        cliente.setCep(dto.cep());
        cliente.setComplemento(dto.complemento());

        return cliente;
    }

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTel(),
                cliente.getLogradouro(),
                cliente.getNumero(),
                cliente.getCep(),
                cliente.getComplemento()
        );
    }
}
