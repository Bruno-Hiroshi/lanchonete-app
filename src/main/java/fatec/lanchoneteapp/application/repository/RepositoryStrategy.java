package fatec.lanchoneteapp.application.repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryStrategy<T> {

    /**
     * Atualiza a entidade especificada no banco de dados.
     *
     * @param entidade Entidade a ser atualizada.
     * @exception SQLException exception lançada em caso de erro na operação com o banco de dados
     */
    void atualizar(T entidade) throws SQLException;

    /**
     * Remove a entidade especificada do banco de dados.
     *
     * @param entidade Entidade a ser excluída
     * @exception SQLException exception lançada em caso de erro na operação com o banco de dados
     */
    void excluir(T entidade) throws SQLException;

    /**
     * Realiza a busca de uma entidade no banco de dados com base nos parâmetros fornecidos.
     *
     * @param entidade Entidade utilizada como parâmetro de busca.
     * @return A entidade correspondente encontrada ou null caso não seja localizada.
     * @exception SQLException exception lançada em caso de erro na operação com o banco de dados
     */
    T buscarPorID(T entidade) throws SQLException;

    /**
     * Retorna uma lista contendo todas as entidades armazenadas em determinada tabela no banco de dados.
     *
     * @return Uma lista de entidades do tipo T.
     * @exception SQLException exception lançada em caso de erro na operação com o banco de dados
     */
    List<T> listar() throws SQLException;

    /**
     * Realiza a busca de uma entidade no banco de dados com base nos parâmetros fornecidos.
     *
     * @param entidade Entidade utilizada como parâmetro de busca.
     * @return A entidade correspondente encontrada ou null caso não seja localizada.
     * @exception SQLException exception lançada em caso de erro na operação com o banco de dados
     */
    T buscarPorChaveSecundaria(T entidade) throws SQLException;
}
