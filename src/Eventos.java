import java.time.LocalDateTime;

public class Eventos {

    private   String nome_evento;
    private  String endereco;
    private  String categoria;
    private  LocalDateTime datahora;
    private String descricao;
    private  boolean confirmado;

    public String getNome_evento() {return nome_evento;}
    public String getEndereco() {return endereco;}
    public String getCategoria() {return categoria;}
    public String getDescricao() {return descricao;}
    public LocalDateTime getdatahora() {return datahora;}


    public Eventos (String nome_evento , String endereco, String categoria,String descricao, LocalDateTime datahora){

        this.nome_evento = nome_evento;
        this.endereco = endereco;
        this.categoria = categoria;
        this.descricao = descricao;
        this.datahora = datahora;
        this.confirmado = false;
    }
    // Retorna se já confirmou ou não
    public boolean isConfirmado() {
        return confirmado;
    }
    // Marca como presença confirmada
    public void confirmar(){
        this.confirmado = true;
    }
    // Volta para pendente
    public void desconfirmar(){
        this.confirmado = false;
    }

    public boolean jaOcorreu(){
        return  datahora.isBefore(LocalDateTime.now());
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    @Override
    public String toString(){
        String status = confirmado ? "[CONFIRMADO]" : "[PENDENTE]";
        String ocorreu = jaOcorreu() ? "[JÁ ACONTECEU]" : "[FUTURO]";
        return status + "| Nome: " + nome_evento +
                " | Local: " + endereco +
                " | Categoria: " + categoria +
                " | Descrição:  " + descricao +
                " | Data/Hora: " + datahora +
                ocorreu;
    }


}
