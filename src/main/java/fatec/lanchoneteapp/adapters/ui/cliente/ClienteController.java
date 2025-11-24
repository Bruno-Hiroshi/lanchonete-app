package fatec.lanchoneteapp.adapters.ui.cliente;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IController;
import fatec.lanchoneteapp.application.dto.ClienteDTO;
import fatec.lanchoneteapp.application.exception.ClienteNaoEncontradoException;
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

public class ClienteController extends Controller implements Initializable, IController<ClienteDTO> {

    private final CadastroFacade cadastroFacade;

    @FXML private TextField tfBuscarCliente;

    //Tabela
    @FXML private TableView<ClienteDTO> tvListaClientes;
    @FXML private TableColumn<ClienteDTO, Integer> tcIDCliente;
    @FXML private TableColumn<ClienteDTO, String> tcNomeCliente;
    @FXML private TableColumn<ClienteDTO, String> tcTelefoneCliente;
    @FXML private TableColumn<ClienteDTO, String> tcCPFCliente;
    @FXML private TableColumn<ClienteDTO, String> tcLogradouroCliente;
    @FXML private TableColumn<ClienteDTO, Integer> tcNumeroCliente;
    @FXML private TableColumn<ClienteDTO, String> tcCEPCliente;
    @FXML private TableColumn<ClienteDTO, String> tcComplementoCliente;
    @FXML private TableColumn<ClienteDTO, Void> tcAcoesCliente;
    @FXML private ObservableList<ClienteDTO> clientesObservableList;

    public ClienteController(CadastroFacade cadastroFacade) {
        super();
        this.cadastroFacade = cadastroFacade;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientesObservableList = FXCollections.observableArrayList();
        tvListaClientes.setItems(clientesObservableList);

        carregarTabela();
    }

    @FXML
    Callback<TableColumn<ClienteDTO, Void>, TableCell<ClienteDTO, Void>> fabricanteColunaAcoes =
    ( param ) -> new TableCell<>() {
        private Button btnApagar = new Button("Apagar");
        private Button btnEditar = new Button("Editar");

        {
            btnApagar.setOnAction(click -> {
                    onRemoverClick(tvListaClientes.getItems().get(getIndex()));
                }
            );

            btnEditar.setOnAction(click -> {
                    try {
                        onAtualizarClick(tvListaClientes.getItems().get(getIndex()));
                    } catch (IOException e) {
                        criarErrorAlert("Ocorreu um erro", e.getMessage());
                    }
                }
            );
        }

        @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic( new HBox(btnEditar, btnApagar) );
            } else {
                setGraphic( null );
            }
        }
    };

    @FXML
    @Override
    public void onInserirClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/cliente/CadastroCliente.fxml"
        ));
        Parent form = loader.load();

        ClienteFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);

        Stage stage = new Stage();
        stage.setTitle("Novo Cliente");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onAtualizarClick(ClienteDTO cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/cliente/CadastroCliente.fxml"
        ));
        Parent form = loader.load();

        ClienteFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);
        controller.setCampos(cliente);

        Stage stage = new Stage();
        stage.setTitle("Atualizar Cliente");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onRemoverClick(ClienteDTO cliente) {
        try {
            cadastroFacade.removerCliente(cliente.id());
            carregarTabela();
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    @Override
    public void onBuscarClick() {
        if(tfBuscarCliente.getText().isEmpty()) {
            carregarTabela();
            return;
        }

        try{
            clientesObservableList.clear();
            clientesObservableList.addAll(
                    cadastroFacade.buscarCliente(Integer.parseInt(tfBuscarCliente.getText()))
            );
        } catch (ClienteNaoEncontradoException e) {
            criarWarningAlert("Cliente não encontrado", e.getMessage());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    @Override
    public void carregarTabela() {
        tcIDCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tcCPFCliente.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tcLogradouroCliente.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        tcNumeroCliente.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tcCEPCliente.setCellValueFactory(new PropertyValueFactory<>("cep"));
        tcComplementoCliente.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        tcAcoesCliente.setCellFactory(fabricanteColunaAcoes);

        try {
            clientesObservableList.clear();
            clientesObservableList.addAll(cadastroFacade.listarClientes().stream().toList());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }
}
