package fatec.lanchoneteapp.run;

import fatec.lanchoneteapp.adapters.ui.cargo.CargoController;
import fatec.lanchoneteapp.adapters.ui.categoria.CategoriaController;
import fatec.lanchoneteapp.adapters.ui.cliente.ClienteController;
import fatec.lanchoneteapp.adapters.ui.fornecedor.FornecedorController;
import fatec.lanchoneteapp.adapters.ui.funcionario.FuncionarioController;
import fatec.lanchoneteapp.adapters.ui.pedido.PedidoController;
import fatec.lanchoneteapp.adapters.ui.produto.ProdutoController;
import fatec.lanchoneteapp.config.AppBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    private AppBuilder builder;

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        builder = new AppBuilder();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fatec/lanchoneteapp/run/main.fxml"));

        fxmlLoader.setControllerFactory(type -> {
            if (type == ClienteController.class) {
                return new ClienteController(builder.getCadastroFacade());
            }
            if (type == CategoriaController.class) {
                return new CategoriaController(builder.getCadastroFacade());
            }
            if (type == CargoController.class) {
                return new CargoController(builder.getCadastroFacade());
            }
            if (type == FornecedorController.class) {
                return new FornecedorController(builder.getCadastroFacade());
            }
            if (type == FuncionarioController.class) {
                return new FuncionarioController(builder.getCadastroFacade());
            }
            if (type == ProdutoController.class) {
                return new ProdutoController(builder.getCadastroFacade());
            }
            if (type == PedidoController.class) {
                return new PedidoController(builder.getPedidoFacade());
            }

            // fallback padrão
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Erro ao criar controller: " + type, e);
            }
        });

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Gerenciamento de Lanchonete");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
