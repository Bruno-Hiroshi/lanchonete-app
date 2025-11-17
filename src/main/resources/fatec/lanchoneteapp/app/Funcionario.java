import java.time.LocalDate;
public class Funcionario {
  String nome;
    Cargo cargo;
    String tel;
    LocalDate dataContrat;

    public Cargo getCargo() {
        return cargo;
    }

    public LocalDate getDataContrat() {
        return dataContrat;
    }

    public String getNome() {
        return nome;
    }

    public String getTel() {
        return tel;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setDataContrat(LocalDate dataContrat) {
        this.dataContrat = dataContrat;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}

