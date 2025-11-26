package fatec.lanchoneteapp.adapters.ui.pedido;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IController;
import fatec.lanchoneteapp.application.dto.PedidoDTO;
import fatec.lanchoneteapp.application.exception.PedidoNaoEncontradoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import fatec.lanchoneteapp.application.facade.PedidoFacade;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PedidoController extends Controller implements Initializable, IController<PedidoDTO> {

    private final PedidoFacade pedidoFacade;
    private final CadastroFacade cadastroFacade;

    @FXML
    private TextField tfBuscarPedido;

    //Tabela
    @FXML private TableView<PedidoDTO> tvListaPedidos;
    @FXML private TableColumn<PedidoDTO, Integer> tcNumPedido;
    @FXML private TableColumn<PedidoDTO, Double> tcValorTotalPedido;
    @FXML private TableColumn<PedidoDTO, LocalDate> tcDataPedido;
    @FXML private TableColumn<PedidoDTO, String> tcStatusPedido;
    @FXML private TableColumn<PedidoDTO, String> tcClientePedido;
    @FXML private TableColumn<PedidoDTO, Void> tcAcoesPedido;
    @FXML private ObservableList<PedidoDTO> pedidosObservableList;

    public PedidoController(PedidoFacade pedidoFacade, CadastroFacade cadastroFacade) {
        this.pedidoFacade = pedidoFacade;
        this.cadastroFacade = cadastroFacade;
    }

    @Override
    public void onInserirClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/pedido/CadastroPedido.fxml"
        ));
        Parent form = loader.load();

        PedidoFormController controller = loader.getController();
        controller.setFacades(pedidoFacade, cadastroFacade);
        controller.setCamposDefault();

        Stage stage = new Stage();
        stage.setTitle("Novo Pedido");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @Override
    public void onAtualizarClick(PedidoDTO pedido) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/pedido/CadastroPedido.fxml"
        ));
        Parent form = loader.load();

        PedidoFormController controller = loader.getController();
        controller.setCampos(pedido);
        controller.setFacades(pedidoFacade, cadastroFacade);
        

        Stage stage = new Stage();
        stage.setTitle("Atualizar Pedido");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @Override
    public void onRemoverClick(PedidoDTO pedidoDTO) {
        try{
            pedidoFacade.cancelarPedido(pedidoDTO.nPedido());
        } catch (SQLException e) {
            criarErrorAlert("Erro", e.getMessage());
        }
    }

    @Override
    public void onBuscarClick() {
        if(tfBuscarPedido.getText().isEmpty()) {
            carregarTabela();
            return;
        }

        try{
            pedidosObservableList.clear();
            pedidosObservableList.addAll(
                    pedidoFacade.buscarPedido(Integer.parseInt(tfBuscarPedido.getText()))
            );
        } catch (PedidoNaoEncontradoException e) {
            criarWarningAlert("Aviso", e.getMessage());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    Callback<TableColumn<PedidoDTO, Void>, TableCell<PedidoDTO, Void>> fabricanteColunaAcoes =
            ( param ) -> new TableCell<>() {
                private Button btnCancelar = new Button("Apagar");
                private Button btnEditar = new Button("Editar");

                {
                    btnCancelar.setOnAction(click -> {
                                onRemoverClick(tvListaPedidos.getItems().get(getIndex()));
                            }
                    );
                    btnCancelar.setPrefWidth(100);

                    btnEditar.setOnAction(click -> {
                                try {
                                    onAtualizarClick(tvListaPedidos.getItems().get(getIndex()));
                                } catch (IOException e) {
                                    criarErrorAlert("Ocorreu um erro", e.getMessage());
                                }
                            }
                    );
                    btnEditar.setPrefWidth(100);
                }

                private final HBox hbox = new HBox(5, btnEditar, btnCancelar);
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
        tcNumPedido.setCellValueFactory(new PropertyValueFactory<>("nPedido"));
        tcValorTotalPedido.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tcDataPedido.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));
        tcStatusPedido.setCellValueFactory(new PropertyValueFactory<>("status"));
        tcClientePedido.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tcAcoesPedido.setCellFactory(fabricanteColunaAcoes);

        try {
            pedidosObservableList.clear();
            pedidosObservableList.addAll(pedidoFacade.listarPedidos().stream().toList());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pedidosObservableList = FXCollections.observableArrayList();
        tvListaPedidos.setItems(pedidosObservableList);

        carregarTabela();
    }
}
