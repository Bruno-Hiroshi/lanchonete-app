package fatec.lanchoneteapp.adapters.ui.controller;

public interface IController<T> {
    public void onInserirClick() throws Exception;
    public void onAtualizarClick(T t) throws Exception;
    public void onRemoverClick(T t) throws Exception;
    public void onBuscarClick() throws Exception;
    public void carregarTabela() throws Exception;
}
