import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private List<Eventos> eventos;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.eventos = new ArrayList<>();
    }
    // Metodo para adicionar os eventos ao usuario

    public void adicionareventos(Eventos evento) {
        this.eventos.add(evento);
    }
    // Retorna a lista de eventos do ususario
    public List<Eventos> getEventos() {
        return eventos;
    }
    public boolean confirmarEventoporIndice(int IndiceBase1){
        int idx = IndiceBase1 - 1;
        if (idx < 0 || idx >= eventos.size())return false;
        eventos.get(idx).confirmar();
        return true;

    }
    public boolean removerEventoporIndice(int IndiceBase1){
        int idx = IndiceBase1 - 1;
        if (idx < 0 || idx >= eventos.size())return false;
        eventos.get(idx).desconfirmar();
        return true;
    }
    public boolean excluirEventoporIndice(int IndiceBase1){
        int idx = IndiceBase1 - 1;
        if (idx < 0 || idx >= eventos.size())return false;
        eventos.remove(idx);
        return true;
    }

    public void ordenarEventosPorData(){
        eventos.sort((e1 , e2) -> e1.getDatahora().compareTo(e2.getDatahora()));
    }


    // Mostra o nome e email do usuario formatado
    @Override
    public String toString() {
        return nome + "(" + email + ")";
    }
}