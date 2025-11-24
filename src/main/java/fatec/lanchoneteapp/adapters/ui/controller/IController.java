package fatec.lanchoneteapp.adapters.ui.controller;

import java.io.IOException;

public interface IController<T> {
    public void onInserirClick() throws IOException;
    public void onAtualizarClick(T t) throws IOException;
    public void onRemoverClick(T t) ;
    public void onBuscarClick();
    public void carregarTabela();
}
