package fatec.lanchoneteapp.adapters.ui.cargo;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IController;
import fatec.lanchoneteapp.application.dto.CargoDTO;
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

public class CargoController extends Controller implements Initializable, IController<CargoDTO> {
    
    private final CadastroFacade cadastroFacade;

    //Campos de busca
    @FXML private TextField tfBuscarCargo;

    //Tabela
    @FXML private TableView<CargoDTO> tvListaCargos;
    @FXML private TableColumn<CargoDTO, Integer> tcIDCargo; 
    @FXML private TableColumn<CargoDTO, String> tcNomeCargo;
    @FXML private TableColumn<CargoDTO, Double> tcSalarioCargo;
    @FXML private TableColumn<CargoDTO, String> tcDescricaoCargo;
    @FXML private TableColumn<CargoDTO, Void> tcAcoesCargo;
    @FXML private ObservableList<CargoDTO> cargosObservableList;

    public CargoController(CadastroFacade cadastroFacade) {
        super();
        this.cadastroFacade = cadastroFacade;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargosObservableList = FXCollections.observableArrayList();
        tvListaCargos.setItems(cargosObservableList);

        carregarTabela();
    }

    @FXML
    Callback<TableColumn<CargoDTO, Void>, TableCell<CargoDTO, Void>> fabricanteColunaAcoes =
        ( param ) -> new TableCell<>() {
            private Button btnApagar = new Button("Apagar");
            private Button btnEditar = new Button("Editar");
        
            { 
                btnApagar.setOnAction(click -> { 
                        onRemoverClick(tvListaCargos.getItems().get(getIndex()));
                    }
                );

                btnEditar.setOnAction(click -> { 
                        try {
                            onAtualizarClick(tvListaCargos.getItems().get(getIndex()));
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
                    setGraphic( new HBox(btnApagar, btnEditar) );
                } else { 
                    setGraphic( null );
                }
            }
        };

    @FXML
    @Override
    public void onInserirClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/cargo/CadastroCargo.fxml"
        ));
        Parent form = loader.load();

        CargoFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);

        Stage stage = new Stage();
        stage.setTitle("Novo Cargo");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onAtualizarClick(CargoDTO cargo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fatec/lanchoneteapp/run/cargo/CadastroCargo.fxml"
        ));
        Parent form = loader.load();

        CargoFormController controller = loader.getController();
        controller.setCadastroFacade(cadastroFacade);
        controller.setCampos(cargo);

        Stage stage = new Stage();
        stage.setTitle("Novo Cargo");
        stage.setScene(new Scene(form));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        carregarTabela();
    }

    @FXML
    @Override
    public void onRemoverClick(CargoDTO cargo) {
        try {
            cadastroFacade.removerCargo(cargo.id());
            carregarTabela();
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    @Override
    public void onBuscarClick() {
        if(tfBuscarCargo.getText().isEmpty()) {
            carregarTabela();
            return;
        }

        try{
            cargosObservableList.clear();
            cargosObservableList.addAll(
                cadastroFacade.buscarCargo(Integer.parseInt(tfBuscarCargo.getText()))
            );
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }

    @FXML
    @Override
    public void carregarTabela() {
        tcIDCargo.setCellValueFactory(new PropertyValueFactory<CargoDTO, Integer>("id"));
        tcNomeCargo.setCellValueFactory(new PropertyValueFactory<CargoDTO, String>("nome"));
        tcSalarioCargo.setCellValueFactory(new PropertyValueFactory<CargoDTO, Double>("salario"));
        tcDescricaoCargo.setCellValueFactory(new PropertyValueFactory<CargoDTO, String>("descricao"));
        tcAcoesCargo.setCellFactory(fabricanteColunaAcoes);

        try {
            cargosObservableList.clear();
            cargosObservableList.addAll(cadastroFacade.listarCargos());
        } catch (SQLException e) {
            criarErrorAlert("Ocorreu um erro", e.getMessage() + "\n" + e.getSQLState());
        }
    }
}
