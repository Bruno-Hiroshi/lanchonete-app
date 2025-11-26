package fatec.lanchoneteapp.application.repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryListById<T> extends RepositoryNoReturn<T>{
    /**
     * Retorna uma lista contendo todas as entidades armazenadas
     * em determinada tabela no banco de dados com base no ID fornecido.
     *
     * @param ID um ID para busca dos itens
     * @return Uma lista de entidades do tipo T.
     * @exception SQLException exception lançada em caso de erro na operação com o banco de dados
     */
    List<T> listarPorId(int ID) throws SQLException;
}
