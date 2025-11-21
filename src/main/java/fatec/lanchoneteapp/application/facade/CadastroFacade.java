package fatec.lanchoneteapp.application.facade;

import fatec.lanchoneteapp.application.dto.*;
import fatec.lanchoneteapp.domain.entity.*;

import java.time.LocalDate;

public interface CadastroFacade {
    //CLIENTE
    ClienteDTO novoCliente(String nome, String tel, String logradouro, int numero, String cep, String complemento, String email, String password);
    ClienteDTO buscarCliente(int idCliente);
    ClienteDTO atualizarCliente(Cliente cliente);
    ClienteDTO removerCliente(int idCliente);

    //FUNCIONARIO
    FuncionarioDTO novoFuncionario(String nome, String telefone, LocalDate dataContrato, int cargoId);
    FuncionarioDTO buscarFuncionario(int idFuncionario);
    FuncionarioDTO atualizarFuncionario(Funcionario funcionario);
    Funcionario removerFuncionario(int idFuncionario);

    //CARGO
    CargoDTO novoCargo(String nome, double salario, String descricao);
    CargoDTO buscarCargo(int idCargo);
    CargoDTO atualizarCargo(Cargo cargo);
    CargoDTO removerCargo(int idCargo);

    //---------------------------------------------------------
    //PRODUTO
    ProdutoDTO novoProduto(String nome, int qtdEstoque, double valorUn, int idCategoria);
    ProdutoDTO buscarProduto(int idProduto);
    ProdutoDTO atualizarProduto(Produto produto);
    ProdutoDTO removerProduto(int idProduto);

    //CATEGORIA
    CategoriaDTO novaCategoria(String nome, String descricao);
    CategoriaDTO buscarCategoria(int idCategoria);
    CategoriaDTO atualizarCategoria(Categoria categoria);
    CategoriaDTO removerCategoria(int idCategoria);
}
