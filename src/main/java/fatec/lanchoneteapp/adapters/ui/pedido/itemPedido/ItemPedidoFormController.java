package fatec.lanchoneteapp.adapters.ui.pedido.itemPedido;

import java.sql.SQLException;
import java.util.List;

import fatec.lanchoneteapp.adapters.ui.controller.Controller;
import fatec.lanchoneteapp.adapters.ui.controller.IFormController;
import fatec.lanchoneteapp.application.dto.ItemPedidoDTO;
import fatec.lanchoneteapp.application.dto.ProdutoDTO;
import fatec.lanchoneteapp.application.exception.ProdutoInvalidoException;
import fatec.lanchoneteapp.application.facade.CadastroFacade;
import fatec.lanchoneteapp.application.facade.PedidoFacade;
import fatec.lanchoneteapp.application.mapper.ProdutoMapper;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ItemPedidoFormController extends Controller implements IFormController<ItemPedidoDTO> {

    private PedidoFacade pedidoFacade;
    private CadastroFacade cadastroFacade;
    private ProdutoMapper produtoMapper = new ProdutoMapper();

    // Botões
    @FXML private Button btnVoltarItemPedido;

    //Campos
    private int itemPedidoId;
    @FXML private TextField tfProdutoItemPedido;
    @FXML private TextField tfQuantidadeItemPedido;
    @FXML private TextField tfValorUnitItemPedido;
    @FXML private TextField tfValorTotalItemPedido;

    private ContextMenu produtosMenu;
    private List<ProdutoDTO> produtos;
    private ProdutoDTO produtoSelecionado;

    @Override
    public void onVoltarClick() {
        Stage stage = (Stage) btnVoltarItemPedido.getScene().getWindow();
        stage.close();
    }

    @Override
    public void onSalvarClick() {
        if(!validarCampos())
            return;

        if(itemPedidoId > 0){
            ItemPedidoDTO pedidoDTO = new ItemPedidoDTO(
                itemPedidoId,
                produtoMapper.toEntity(produtoSelecionado),
                Integer.parseInt(tfQuantidadeItemPedido.getText()),
                Double.parseDouble(tfValorUnitItemPedido.getText()),
                Double.parseDouble(tfValorTotalItemPedido.getText())
            );

           try {
                pedidoFacade.atualizarQuantidadeProduto(pedidoDTO);

                criarInfoAlert("Sucesso!", "Item atualizado com sucesso.");
                onVoltarClick();
           } catch (ProdutoInvalidoException e) {
               criarErrorAlert("Produto inválido!", e.getMessage());
           } catch (SQLException sql) {
               criarErrorAlert("Ocorreu um erro", sql.getMessage());
           }
        }
        else {
            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO(
                0,
                produtoMapper.toEntity(produtoSelecionado),
                Integer.parseInt(tfQuantidadeItemPedido.getText()),
                Double.parseDouble(tfValorUnitItemPedido.getText()),
                Double.parseDouble(tfValorTotalItemPedido.getText())
            );

            try {
                pedidoFacade.adicionarProduto(itemPedidoDTO);

                criarInfoAlert("Sucesso!", "Item inserido com sucesso");
                onVoltarClick();
            } catch (ProdutoInvalidoException e) {
                criarErrorAlert("Pedido inválido!", e.getMessage());
            } catch (SQLException sql) {
                criarErrorAlert("Ocorreu um erro", sql.getMessage());
            }
        }
    }

    @Override
    public void setCampos(ItemPedidoDTO itemPedidoDTO) {
        this.itemPedidoId = itemPedidoDTO.nPedido();
        tfProdutoItemPedido.setText(itemPedidoDTO.getProdutoDTO().getNome());
        tfQuantidadeItemPedido.setText(String.valueOf(itemPedidoDTO.getQtd()));
        tfValorUnitItemPedido.setText(String.valueOf(itemPedidoDTO.getValorUn()));
    }

    @Override
    public boolean validarCampos() {
        String message = "";
        if(tfValorUnitItemPedido.getText().isEmpty())
            message = "O Campo 'Valor Unitário' é obrigatório";
        if(tfQuantidadeItemPedido.getText().isEmpty())
            message = "O Campo 'Quantidade' é obrigatório";
        if(tfProdutoItemPedido.getText().isEmpty())
            message = "O Campo 'Nome' é obrigatório";

        if(message.isEmpty())
            return true;
        else {
            criarErrorAlert("Cadastro inválido!", message);
            return false;
        }
    }

    public void setPedidoFacade(PedidoFacade pedidoFacade) {
        this.pedidoFacade = pedidoFacade;

        produtosMenu = new ContextMenu();
        carregarProdutos();
        configurarAutocomplete();
    }

    private void configurarAutocomplete() {
        tfProdutoItemPedido.textProperty().addListener((obs, oldText, newText) -> {
            if(newText == null || newText.isBlank()){
                produtosMenu.hide();
                return;
            }

            List<ProdutoDTO> filter = produtos.stream().
                    filter(f -> f.getNome().toLowerCase().contains(newText.toLowerCase()))
                    .toList();

            if(filter.isEmpty()){
                produtosMenu.hide();
                return;
            }

            List<MenuItem> itens = filter.stream().map(f -> {
                        MenuItem item = new MenuItem(f.getNome());

                        item.setOnAction(e -> {
                            tfProdutoItemPedido.setText(f.getNome());
                            produtoSelecionado = f;
                            produtosMenu.hide();
                        });

                        return item;
                    })
                    .toList();

            produtosMenu.getItems().setAll(itens);

            if(!produtosMenu.isShowing()){
                Bounds bounds = tfProdutoItemPedido.localToScreen(tfProdutoItemPedido.getBoundsInLocal());
                produtosMenu.show(tfProdutoItemPedido, bounds.getMinX(), bounds.getMaxY());
            } else {
                Bounds bounds = tfProdutoItemPedido.localToScreen(tfProdutoItemPedido.getBoundsInLocal());
                produtosMenu.show(tfProdutoItemPedido, bounds.getMinX(), bounds.getMaxY());
            }
        });
    }

    private void carregarProdutos() {
        try {
            produtos = cadastroFacade.listarProdutos();
        } catch (SQLException e) {
            criarErrorAlert("Erro ao acessar o banco de dados", "Não foi possível carregar os clientes:\n" + e.getMessage());
        }
    }
}
