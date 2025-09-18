import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class Usuario {

    private List <Eventos> eventos = new ArrayList<>();
    private String nome;
    private String email;
    private String senha;


    private static final DateTimeFormatter formatar = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public void salvarEventos(){
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("eventos.data"))){
            for (Eventos e : eventos) {
                String linha = String.join(";",
                        e.getNome_evento(),
                        e.getEndereco(),
                        e.getCategoria(),
                        e.getDescricao(),
                        e.getDatahora().format(formatar),
                        String.valueOf(e.isConfirmado())

                );
                writer.write(linha);
                writer.newLine(); // <-- quebra de linha

            }


        }catch (IOException ex){
            System.out.println("Erro ao salvar eventos" + ex.getMessage());
        }

    }

    public void carregarEventos(){
        Path caminho = Paths.get("eventos.data");
        if (!Files.exists(caminho)) return;

        try (BufferedReader reader = Files.newBufferedReader(caminho)){
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] partes = linha.split(";");
                if(partes.length == 6){
                    String nome_evento = partes[0];
                    String endereco = partes[1];
                    String categoria = partes[2];
                    String descricao = partes[3];
                    LocalDateTime datahora = LocalDateTime.parse(partes[4], formatar);
                    Boolean confirmado = Boolean.parseBoolean(partes[5]);

                    Eventos e = new Eventos(nome_evento,endereco,categoria,descricao,datahora);
                    if (confirmado) e.confirmar();
                    eventos.add(e);
                }
            }
        }catch (IOException ex){
            System.out.println("Erro ao carregar eventos" + ex.getMessage());

        }


    }

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