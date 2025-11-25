package fatec.lanchoneteapp.adapters.ui.produto;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IController;
import fatec.lanchoneteapp.application.dto.CategoriaDTO;
import fatec.lanchoneteapp.application.dto.FuncionarioDTO;
import fatec.lanchoneteapp.application.dto.ProdutoDTO;
import fatec.lanchoneteapp.application.exception.ProdutoNaoEncontradoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProdutoController extends Controller implements Initializable, IController<ProdutoDTO> {

    private final CadastroFacade cadastroFacade;

    @FXML private TextField tfBuscarProduto;

    //Tabela
    @FXML private TableView<ProdutoDTO> tvListaProdutos;
    @FXML private TableColumn<ProdutoDTO, Integer> tcIDProduto;
    @FXML private TableColumn<ProdutoDTO, String> tcNomeProduto;
    @FXML private TableColumn<ProdutoDTO, String> tcQtdEstoqueProduto;
    @FXML private TableColumn<ProdutoDTO, Double> tcValorUnitProduto;
    @FXML private TableColumn<ProdutoDTO, String> tcCategoriaProduto;
    @FXML private TableColumn<ProdutoDTO, String> tcFornecedorProduto;
    @FXML private TableColumn<ProdutoDTO, Void> tcAcoesProduto;
    @FXML private ObservableList<ProdutoDTO> produtosObservableList;

    public ProdutoController(CadastroFacade cadastroFacade) {
        this.cadastroFacade = cadastroFacade;
    }

    @FXML
    @Override
    public void onInserirClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/produto/CadastroProduto.fxml"
        ));
        Parent form = loader.load();

        ProdutoFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);

        Stage stage = new Stage();
        stage.setTitle("Novo Produto");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onAtualizarClick(ProdutoDTO produto) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/produto/CadastroProduto.fxml"
        ));
        Parent form = loader.load();

        ProdutoFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);
        controller.setCampos(produto);

        Stage stage = new Stage();
        stage.setTitle("Atualizar Produto");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onRemoverClick(ProdutoDTO produto) {
        try {
            cadastroFacade.removerProduto(produto.id());
            carregarTabela();
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    @Override
    public void onBuscarClick() {
        if(tfBuscarProduto.getText().isEmpty()) {
            carregarTabela();
            return;
        }

        try{
            produtosObservableList.clear();
            produtosObservableList.addAll(
                    cadastroFacade.buscarProduto(Integer.parseInt(tfBuscarProduto.getText()))
            );
        } catch (ProdutoNaoEncontradoException e) {
            criarWarningAlert("Aviso", e.getMessage());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    Callback<TableColumn<ProdutoDTO, Void>, TableCell<ProdutoDTO, Void>> fabricanteColunaAcoes =
            ( param ) -> new TableCell<>() {
                private Button btnApagar = new Button("Apagar");
                private Button btnEditar = new Button("Editar");

                {
                    btnApagar.setOnAction(click -> {
                                onRemoverClick(tvListaProdutos.getItems().get(getIndex()));
                            }
                    );
                    btnApagar.setPrefWidth(100);

                    btnEditar.setOnAction(click -> {
                                try {
                                    onAtualizarClick(tvListaProdutos.getItems().get(getIndex()));
                                } catch (IOException e) {
                                    criarErrorAlert("Ocorreu um erro", e.getMessage());
                                }
                            }
                    );
                    btnEditar.setPrefWidth(100);
                }

                private final HBox hbox = new HBox(5, btnEditar, btnApagar);
                {
                    hbox.setStyle("-fx-alignment: CENTER;");
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setGraphic( hbox );
                    } else {
                        setGraphic( null );
                    }
                }
            };

    @Override
    public void carregarTabela() {
        tcIDProduto.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcQtdEstoqueProduto.setCellValueFactory(new PropertyValueFactory<>("qtdEstoque"));
        tcValorUnitProduto.setCellValueFactory(new PropertyValueFactory<>("valorUn"));
        tcCategoriaProduto.setCellValueFactory(new PropertyValueFactory<>("categoriaNome"));
        tcFornecedorProduto.setCellValueFactory(new PropertyValueFactory<>("fornecedorNome"));
        tcAcoesProduto.setCellFactory(fabricanteColunaAcoes);

        try {
            produtosObservableList.clear();
            produtosObservableList.addAll(cadastroFacade.listarProdutos());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produtosObservableList = FXCollections.observableArrayList();
        tvListaProdutos.setItems(produtosObservableList);

        carregarTabela();
    }
}
