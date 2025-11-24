package fatec.lanchoneteapp.adapters.ui.controller;

public interface IFormController<T> {
    public void onVoltarClick();
    public void onSalvarClick();
    public void setCampos(T t);
}
