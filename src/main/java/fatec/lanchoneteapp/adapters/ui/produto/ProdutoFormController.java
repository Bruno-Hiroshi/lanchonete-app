package fatec.lanchoneteapp.adapters.ui.produto;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IFormController;
import fatec.lanchoneteapp.application.dto.*;
import fatec.lanchoneteapp.application.exception.ClienteInvalidoException;
import fatec.lanchoneteapp.application.exception.ProdutoInvalidoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import fatec.lanchoneteapp.application.mapper.CategoriaMapper;
import fatec.lanchoneteapp.application.mapper.FornecedorMapper;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ProdutoFormController extends Controller implements IFormController<ProdutoDTO> {

    private CadastroFacade cadastroFacade;
    private final CategoriaMapper categoriaMapper = new CategoriaMapper();
    private final FornecedorMapper fornecedorMapper = new FornecedorMapper();

    @FXML private Button btnVoltarProduto;

    private int idProduto;
    @FXML private TextField tfNomeProduto;
    @FXML private TextField tfQtdEstoqueProduto;
    @FXML private TextField tfValorUnitProduto;
    @FXML private TextField tfFornecedorProduto;
    @FXML private ComboBox<CategoriaDTO> cbCategoriaProduto;

    private ContextMenu fornecedoresMenu;
    private List<FornecedorDTO> fornecedores;
    private FornecedorDTO fornecedorSelecionado;

    @Override
    public void onVoltarClick() {
        Stage stage = (Stage) btnVoltarProduto.getScene().getWindow();
        stage.close();
    }

    @Override
    public void onSalvarClick() {
        if(!validarCampos())
            return;

        if(idProduto > 0){
            ProdutoDTO produtoDTO = new ProdutoDTO(
                    idProduto,
                    tfNomeProduto.getText(),
                    Integer.parseInt(tfQtdEstoqueProduto.getText()),
                    Double.parseDouble(tfValorUnitProduto.getText()),
                    categoriaMapper.toEntity(cbCategoriaProduto.getValue()),
                    fornecedorMapper.toEntity(fornecedorSelecionado)
            );

            try {
                cadastroFacade.atualizarProduto(produtoDTO);

                criarInfoAlert("Sucesso!", "Produto atualizado com sucesso.");
                onVoltarClick();
            } catch (ProdutoInvalidoException e) {
                criarErrorAlert("Produto inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
        else {
            ProdutoDTO produtoDTO = new ProdutoDTO(
                    0,
                    tfNomeProduto.getText(),
                    Integer.parseInt(tfQtdEstoqueProduto.getText()),
                    Double.parseDouble(tfValorUnitProduto.getText()),
                    categoriaMapper.toEntity(cbCategoriaProduto.getValue()),
                    fornecedorMapper.toEntity(fornecedorSelecionado)
            );

            System.out.println(fornecedorSelecionado.getNome());

            try {
                cadastroFacade.novoProduto(produtoDTO);

                criarInfoAlert("Sucesso!", "Produto inserido com sucesso");
                onVoltarClick();
            } catch (ProdutoInvalidoException e) {
                criarErrorAlert("Produto inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
    }

    @Override
    public void setCampos(ProdutoDTO produto) {
        this.idProduto = produto.id();
        tfNomeProduto.setText(produto.getNome());
        tfQtdEstoqueProduto.setText(String.valueOf(produto.getQtdEstoque()));
        tfValorUnitProduto.setText(String.valueOf(produto.getValorUn()));
        cbCategoriaProduto.setValue(produto.getCategoriaDTO());
        tfFornecedorProduto.setText(String.valueOf(produto.getFornecedor().getNome()));
    }

    @Override
    public boolean validarCampos() {
        String message = "";

        if(fornecedorSelecionado == null)
            message = "Selecione um 'Fornecedor' válido da lista";
        if(cbCategoriaProduto.getSelectionModel().getSelectedItem() == null)
            message = "Selecione uma 'Categoria'";
        if(tfValorUnitProduto.getText().isEmpty())
            message = "O Campo 'Valor Unitário' é obrigatório";
        if(tfQtdEstoqueProduto.getText().isEmpty())
            message = "O Campo 'Quantidade em Estoque' é obrigatório";
        if(tfNomeProduto.getText().isEmpty())
            message = "O Campo 'Nome' é obrigatório";

        if(message.isEmpty())
            return true;
        else {
            criarErrorAlert("Cadastro inválido!", message);
            return false;
        }
    }

    public void setCadastroFacade(CadastroFacade cadastroFacade){
        this.cadastroFacade = cadastroFacade;

        carregarCategorias();

        fornecedoresMenu = new ContextMenu();
        carregarFornecedores();
        configurarAutocomplete();
    }

    private void configurarAutocomplete() {
        tfFornecedorProduto.textProperty().addListener((obs, oldText, newText) -> {
            if(newText == null || newText.isBlank()){
                fornecedoresMenu.hide();
                return;
            }

            List<FornecedorDTO> filter = fornecedores.stream().
                    filter(f -> f.getNome().toLowerCase().contains(newText.toLowerCase()))
                    .toList();

            if(filter.isEmpty()){
                fornecedoresMenu.hide();
                return;
            }

            List<MenuItem> itens = filter.stream().map(f -> {
                MenuItem item = new MenuItem(f.getNome());

                item.setOnAction(e -> {
                    tfFornecedorProduto.setText(f.getNome());
                    fornecedorSelecionado = f;
                    fornecedoresMenu.hide();
                });

                return item;
                })
                .toList();

            fornecedoresMenu.getItems().setAll(itens);

            if(!fornecedoresMenu.isShowing()){
                Bounds bounds = tfFornecedorProduto.localToScreen(tfFornecedorProduto.getBoundsInLocal());
                fornecedoresMenu.show(tfFornecedorProduto, bounds.getMinX(), bounds.getMaxY());
            } else {
                Bounds bounds = tfFornecedorProduto.localToScreen(tfFornecedorProduto.getBoundsInLocal());
                fornecedoresMenu.show(tfFornecedorProduto, bounds.getMinX(), bounds.getMaxY());
            }
        });
    }

    private void carregarFornecedores() {
        try {
            fornecedores = cadastroFacade.listarFornecedores();
        } catch (SQLException e) {
            criarErrorAlert("Erro ao acessar o banco de dados", "Não foi possível carregar os fornecedores:\n" + e.getMessage());
        }
    }

    private void carregarCategorias() {
        List<CategoriaDTO> categorias = null;
        try {
            categorias = cadastroFacade.listarCategorias();

            cbCategoriaProduto.getItems().clear();
            cbCategoriaProduto.getItems().addAll(categorias);

            cbCategoriaProduto.setCellFactory(listView -> new ListCell<>() {
                @Override
                protected void updateItem(CategoriaDTO categoria, boolean empty) {
                    super.updateItem(categoria, empty);
                    setText(empty || categoria == null ? "" : categoria.getNome());
                }
            });

            cbCategoriaProduto.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(CategoriaDTO categoria, boolean empty) {
                    super.updateItem(categoria, empty);
                    setText(empty || categoria == null ? "" : categoria.getNome());
                }
            });

        } catch (SQLException e) {
            criarErrorAlert("Erro", "Não foi possível carregar as categorias:\n" + e.getMessage());
        }
    }
}
