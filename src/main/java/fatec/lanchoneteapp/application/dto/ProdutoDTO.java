package fatec.lanchoneteapp.application.dto;

import fatec.lanchoneteapp.application.mapper.CategoriaMapper;
import fatec.lanchoneteapp.application.mapper.FornecedorMapper;
import fatec.lanchoneteapp.domain.entity.Categoria;
import fatec.lanchoneteapp.domain.entity.Fornecedor;

public record ProdutoDTO(
        int id,
        String nome,
        int qtdEstoque,
        double valorUn,
        Categoria categoria,
        Fornecedor fornecedor
) {
    static CategoriaMapper categoriaMapper = new CategoriaMapper();
    static FornecedorMapper fornecedorMapper = new FornecedorMapper();

    public int getId(){ return id; }
    public String getNome() { return nome; }
    public int getQtdEstoque() { return qtdEstoque; }
    public double getValorUn() { return valorUn; }
    public CategoriaDTO getCategoriaDTO() { return categoriaMapper.toDTO(categoria); }
    public String getCategoriaNome() { return categoria.getNome(); }
    public FornecedorDTO getFornecedor() { return fornecedorMapper.toDTO(fornecedor); }
    public String getFornecedorNome() { return fornecedor.getNome(); }
}
