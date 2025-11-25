package fatec.lanchoneteapp.adapters.ui.pedido;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IController;
import fatec.lanchoneteapp.adapters.ui.controller.IFormController;
import fatec.lanchoneteapp.adapters.ui.pedido.itemPedido.ItemPedidoFormController;
import fatec.lanchoneteapp.application.dto.ClienteDTO;
import fatec.lanchoneteapp.application.dto.ItemPedidoDTO;
import fatec.lanchoneteapp.application.dto.PedidoDTO;
import fatec.lanchoneteapp.application.exception.ProdutoInvalidoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import fatec.lanchoneteapp.application.facade.PedidoFacade;
import fatec.lanchoneteapp.application.mapper.ClienteMapper;
import fatec.lanchoneteapp.application.mapper.ItemPedidoMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PedidoFormController extends Controller implements IController<ItemPedidoDTO>, IFormController<PedidoDTO> {

    private PedidoFacade pedidoFacade;
    private CadastroFacade cadastroFacade;
    private ItemPedidoMapper itemPedidoMapper = new ItemPedidoMapper();
    private ClienteMapper clienteMapper = new ClienteMapper();


    @FXML private Button btnVoltarPedido;

    private int nPedido;
    @FXML private TextField tfNomeCliente;
    @FXML private DatePicker dpDataPedido;
    @FXML private TextField tfStatusPedido;
    @FXML private TextField tfValorTotal;

    @FXML private TableView<ItemPedidoDTO> tvListaItensPedido;
    @FXML private TableColumn<ItemPedidoDTO, Integer> tcIDProduto;
    @FXML private TableColumn<ItemPedidoDTO, String> tcNomeProduto;
    @FXML private TableColumn<ItemPedidoDTO, Integer> tcQtdProduto;
    @FXML private TableColumn<ItemPedidoDTO, Double> tcValorUnitProduto;
    @FXML private TableColumn<ItemPedidoDTO, Double> tcValorTotalProduto;
    @FXML private TableColumn<ItemPedidoDTO, Void> tcAcoesProduto;
    @FXML private ObservableList<ItemPedidoDTO> itensObservableList;

    private ContextMenu clientesMenu;
    private List<ClienteDTO> clientes;
    private ClienteDTO clienteSelecionado;

    @Override
    public void onInserirClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/itemPedido/CadastroItemPedido.fxml"
        ));
        Parent form = loader.load();

        ItemPedidoFormController controller = loader.getController();
        controller.setPedidoFacade(pedidoFacade);

        Stage stage = new Stage();
        stage.setTitle("Adicionar Item");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }


    public void onAtualizarClick(ItemPedidoDTO itemPedido) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/pedido/CadastroPedido.fxml"
        ));
        Parent form = loader.load();

        ItemPedidoFormController controller = loader.getController();
        controller.setPedidoFacade(pedidoFacade);
        controller.setCampos(itemPedido);

        Stage stage = new Stage();
        stage.setTitle("Atualizar Item");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @Override
    public void onRemoverClick(ItemPedidoDTO itemPedidoDTO) {
       try{
           pedidoFacade.removerProduto(itemPedidoDTO);
       } catch (SQLException e) {
           criarErrorAlert("Erro", e.getMessage());
       }
    }


    @Override
    public void onBuscarClick() {
        //implementar busca na tabela de itens do pedido
    }

    @FXML
    Callback<TableColumn<ItemPedidoDTO, Void>, TableCell<ItemPedidoDTO, Void>> fabricanteColunaAcoes =
            ( param ) -> new TableCell<>() {
                private Button btnApagar = new Button("Apagar");
                private Button btnEditar = new Button("Editar");

                {
                    btnApagar.setOnAction(click -> {
                                onRemoverClick(tvListaItensPedido.getItems().get(getIndex()));
                            }
                    );
                    btnApagar.setPrefWidth(100);

                    btnEditar.setOnAction(click -> {
                                try {
                                    onAtualizarClick(tvListaItensPedido.getItems().get(getIndex()));
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
        tcIDProduto.setCellValueFactory(new PropertyValueFactory<>("num"));
        tcNomeProduto.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tcQtdProduto.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));
        tcValorUnitProduto.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcValorTotalProduto.setCellValueFactory(new PropertyValueFactory<>("clienteNome"));
        tcAcoesProduto.setCellFactory(fabricanteColunaAcoes);

        try {
            itensObservableList.clear();
            itensObservableList.addAll(pedidoFacade.listarProdutos());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onVoltarClick() {
        Stage stage = (Stage) btnVoltarPedido.getScene().getWindow();
        stage.close();
    }

    @Override
    public void onSalvarClick() {
        if(!validarCampos())
            return;

        if(nPedido > 0){
            PedidoDTO pedidoDTO = new PedidoDTO(
                    nPedido,
                    Double.parseDouble(tfValorTotal.getText()),
                    itensObservableList.stream().map(itemPedidoMapper::toEntity).toList(),
                    dpDataPedido.getValue(),
                    tfStatusPedido.getText(),
                    clienteMapper.toEntity(clienteSelecionado)
            );

           try {
                pedidoFacade.atualizarPedido(pedidoDTO);

                criarInfoAlert("Sucesso!", "Produto atualizado com sucesso.");
                onVoltarClick();
           } catch (ProdutoInvalidoException e) {
               criarErrorAlert("Produto inválido!", e.getMessage());
           } catch (SQLException sql) {
               criarErrorAlert("Ocorreu um erro", sql.getMessage());
           }
        }
        else {
            PedidoDTO pedidoDTO = new PedidoDTO(
                    0,
                    Double.parseDouble(tfValorTotal.getText()),
                    itensObservableList.stream().map(itemPedidoMapper::toEntity).toList(),
                    dpDataPedido.getValue(),
                    tfStatusPedido.getText(),
                    clienteMapper.toEntity(clienteSelecionado)
            );

            try {
                pedidoFacade.criarPedido(pedidoDTO);

                criarInfoAlert("Sucesso!", "Pedido inserido com sucesso");
                onVoltarClick();
            } catch (ProdutoInvalidoException e) {
                criarErrorAlert("Pedido inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
    }

    @Override
    public void setCampos(PedidoDTO pedido) {
        this.nPedido = pedido.nPedido();
        tfNomeCliente.setText(String.valueOf(pedido.cliente().getNome()));
        dpDataPedido.setValue(pedido.data());
        tfStatusPedido.setText(pedido.status());
        tfValorTotal.setText(String.valueOf(pedido.valorTotal()));
    }

    @Override
    public boolean validarCampos() {
        String message = "";

        if(itensObservableList.isEmpty())
            message = "O pedido deve possuir ao menos 1 item";
        if(tfNomeCliente.getText().isEmpty())
            message = "O Campo 'Nome' é obrigatório";

        if(message.isEmpty())
            return true;
        else {
            criarErrorAlert("Cadastro inválido!", message);
            return false;
        }
    }

    public void setFacades(PedidoFacade pedidoFacade, CadastroFacade cadastroFacade){
        this.pedidoFacade = pedidoFacade;
        this.cadastroFacade = cadastroFacade;

        itensObservableList = FXCollections.observableArrayList();
        tvListaItensPedido.setItems(itensObservableList);
        carregarTabela();

        clientesMenu = new ContextMenu();
        carregarClientes();
        configurarAutocomplete();
    }

    private void configurarAutocomplete() {
        tfNomeCliente.textProperty().addListener((obs, oldText, newText) -> {
            if(newText == null || newText.isBlank()){
                clientesMenu.hide();
                return;
            }

            List<ClienteDTO> filter = clientes.stream().
                    filter(f -> f.getNome().toLowerCase().contains(newText.toLowerCase()))
                    .toList();

            if(filter.isEmpty()){
                clientesMenu.hide();
                return;
            }

            List<MenuItem> itens = filter.stream().map(f -> {
                        MenuItem item = new MenuItem(f.getNome());

                        item.setOnAction(e -> {
                            tfNomeCliente.setText(f.getNome());
                            clienteSelecionado = f;
                            clientesMenu.hide();
                        });

                        return item;
                    })
                    .toList();

            clientesMenu.getItems().setAll(itens);

            if(!clientesMenu.isShowing()){
                Bounds bounds = tfNomeCliente.localToScreen(tfNomeCliente.getBoundsInLocal());
                clientesMenu.show(tfNomeCliente, bounds.getMinX(), bounds.getMaxY());
            } else {
                Bounds bounds = tfNomeCliente.localToScreen(tfNomeCliente.getBoundsInLocal());
                clientesMenu.show(tfNomeCliente, bounds.getMinX(), bounds.getMaxY());
            }
        });
    }

    private void carregarClientes() {
        try {
            clientes = cadastroFacade.listarClientes();
        } catch (SQLException e) {
            criarErrorAlert("Erro ao acessar o banco de dados", "Não foi possível carregar os clientes:\n" + e.getMessage());
        }
    }
}
