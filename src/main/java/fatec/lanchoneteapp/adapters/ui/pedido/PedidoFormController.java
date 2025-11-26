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
import fatec.lanchoneteapp.domain.entity.ItemPedido;
import javafx.application.Platform;
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
import java.time.LocalDate;
import java.util.List;

public class PedidoFormController extends Controller implements IController<ItemPedidoDTO>, IFormController<PedidoDTO> {

    private PedidoFacade pedidoFacade;
    private CadastroFacade cadastroFacade;
    private ItemPedidoMapper itemPedidoMapper = new ItemPedidoMapper();
    private ClienteMapper clienteMapper = new ClienteMapper();

    private int onInit = 0;

    @FXML private Button btnVoltarPedido;

    private int nPedido;
    @FXML private TextField tfNomeCliente;
    @FXML private DatePicker dpDataPedido;
    @FXML private ComboBox<String> cbStatusPedido;
    @FXML private TextField tfValorTotal;

    @FXML private TableView<ItemPedidoDTO> tvListaItensPedido;
    @FXML private TableColumn<ItemPedidoDTO, Integer> tcIDProduto;
    @FXML private TableColumn<ItemPedidoDTO, String> tcNomeProduto;
    @FXML private TableColumn<ItemPedidoDTO, Integer> tcQtdItemPedido;
    @FXML private TableColumn<ItemPedidoDTO, Double> tcValorUnitItemPedido;
    @FXML private TableColumn<ItemPedidoDTO, Double> tcValorTotalItemPedido;
    @FXML private TableColumn<ItemPedidoDTO, Void> tcAcoesItemPedido;  
    @FXML private ObservableList<ItemPedidoDTO> itensObservableList;

    private ContextMenu clientesMenu;
    private List<ClienteDTO> clientes;
    private ClienteDTO clienteSelecionado;

    ObservableList<String> statusPedido = FXCollections.observableArrayList(
        "Em Preparo", "Aguardando Envio", "Enviado", "Entregue", "Cancelado"
    );

    @Override
    public void onInserirClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/itemPedido/CadastroItemPedido.fxml"
        ));
        Parent form = loader.load();

        ItemPedidoFormController controller = loader.getController();
        controller.setIds(-1, 0);
        controller.setCadastroFacade(cadastroFacade);
        controller.setListaItensPedido(itensObservableList); 
        
        Stage stage = new Stage();
        stage.setTitle("Adicionar Item");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @Override
    public void onAtualizarClick(ItemPedidoDTO itemPedido) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/itemPedido/CadastroItemPedido.fxml"
        ));
        Parent form = loader.load();

        ItemPedidoFormController controller = loader.getController();
        controller.setIds(itensObservableList.indexOf(itemPedido), itemPedido.nPedido());
        controller.setCadastroFacade(cadastroFacade);
        controller.setListaItensPedido(itensObservableList);
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
       itensObservableList.remove(itemPedidoDTO);
    }

    //Não é necessária a implementação
    @Override
    public void onBuscarClick() {
        return;
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
        tcIDProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        tcNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        tcQtdItemPedido.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        tcValorUnitItemPedido.setCellValueFactory(new PropertyValueFactory<>("valorUnit"));
        tcValorTotalItemPedido.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tcAcoesItemPedido.setCellFactory(fabricanteColunaAcoes);

        if(this.nPedido > 0 && this.onInit == 0){
            try {
                itensObservableList.clear();
                itensObservableList.addAll(pedidoFacade.listarItensPorNumPedido(nPedido).stream().toList());
                onInit++;
            } catch (SQLException e) {
                criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
            }
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
                    cbStatusPedido.getValue(),
                    clienteMapper.toEntity(clienteSelecionado)
            );

           try {
                pedidoFacade.atualizarPedido(pedidoDTO);
                for (ItemPedido item : pedidoDTO.itensPedido()) {
                    item.setNumPedido(pedidoDTO.nPedido());
                    pedidoFacade.atualizarQuantidadeItem(itemPedidoMapper.toDTO(item));
                }

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
                    cbStatusPedido.getValue(),
                    clienteMapper.toEntity(clienteSelecionado)
            );

            try {
                pedidoDTO = pedidoFacade.criarPedido(pedidoDTO);
                for (ItemPedido item : pedidoDTO.itensPedido()) {
                    item.setNumPedido(pedidoDTO.nPedido());
                    pedidoFacade.adicionarItem(itemPedidoMapper.toDTO(item));
                }
                
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
        dpDataPedido.setValue(pedido.dataPedido());
        cbStatusPedido.setValue(pedido.status());
        clienteSelecionado = pedido.getClienteDTO();

        tfValorTotal.setText(String.valueOf(pedido.valorTotal()));
    }

    public void setCamposDefault(){
        this.cbStatusPedido.setDisable(true);
        this.cbStatusPedido.setStyle("-fx-opacity: 1");
        this.dpDataPedido.setDisable(true);
        this.dpDataPedido.setStyle("-fx-opacity: 1");
        this.cbStatusPedido.setValue(statusPedido.get(0));
        this.dpDataPedido.setValue(LocalDate.now());
        this.tfValorTotal.setText(String.valueOf(0));
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

        cbStatusPedido.setItems(statusPedido);
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

            Platform.runLater(() -> {
                if(!clientesMenu.isShowing()){
                    Bounds bounds = tfNomeCliente.localToScreen(tfNomeCliente.getBoundsInLocal());
                    clientesMenu.show(tfNomeCliente, bounds.getMinX(), bounds.getMaxY());
                }
            });
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
