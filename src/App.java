import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Usuario> usuarios = Usuario.carregarUsuarios();

        System.out.println("== MENU INICIAL ==");
        System.out.println("1 - Cadastrar Usuario: ");
        System.out.println("2 - Login Usuario: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        Usuario usuario = null;

        switch (escolha) {
            case 1:
                System.out.println("Digite o nome do usuario: ");
                String nome = sc.nextLine();
                System.out.println("Didite o email do usuario: ");
                String email = sc.nextLine();
                System.out.println("Digite o senha do usuario: ");
                String senha = sc.nextLine();
                Usuario novoUsuario = new Usuario(nome, email, senha);
                usuarios.add(novoUsuario);


                // SALVAR O USUARIO NO ARQUIVO Usuarios.data
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("Usuarios.data"),
                        StandardCharsets.UTF_8 , StandardOpenOption.CREATE , StandardOpenOption.APPEND)){
                    writer.write(nome + ";" + email + ";" + senha);
                    writer.newLine();

                }catch (IOException e){
                System.out.println("Erro ao criar o usuario " + e.getMessage());
                }
                System.out.println("Usuario cadastrado com sucesso!");
                usuario = novoUsuario;
                break;

            case 2:
                System.out.println("Email: ");
                String emailLogin = sc.nextLine();
                System.out.println("Senha: ");
                String senhaLogin = sc.nextLine();

                for(Usuario u : usuarios) {
                    if (u.getSenha().equals(senhaLogin) && u.getEmail().equals(emailLogin)) {
                        usuario = u;
                        break;
                    }
                }

                if (usuario == null) {
                    System.out.println("Nenhum usuario encontrado ou senha incorreta!");
                    return;
                }
                break;

            default:
                    System.out.println("Opção inválida");
                    return;

        }
        // Carregando eventos no arquivo
        String evFile = usuario.eventosFilenameforemail(usuario.getEmail());
        usuario.carregarEventos(evFile);


        // Looping para saber o que o usuario vai querer

        while(true){
            System.out.print("\n=== MENU ===");
            System.out.println("\n1- Cadastrar evento");
            System.out.println("2 - Listar meus eventos");
            System.out.println("3 - Confirmar presença em um evento");
            System.out.println("4 - Cancelar presença em um evento");
            System.out.println("5 - Excluir evento ccadastrado");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");
            int opcao = Integer.parseInt(sc.nextLine());


            // Condiçoes para cada escolha

            if (opcao == 1){

                // Cadastrando usuario
                System.out.print("Nome do evento: ");
                String nome_evento = sc.nextLine();
                System.out.print("Local: ");
                String endereco = sc.nextLine();
                System.out.print("Categoria: ");
                String categoria = sc.nextLine();
                System.out.print("Descrição: ");
                String descricao = sc.nextLine();

                // Cadastrando evento
                System.out.print("Ano: ");
                int ano = Integer.parseInt(sc.nextLine());
                System.out.print("Mes: ");
                int mes = Integer.parseInt(sc.nextLine());
                System.out.print("Dia: ");
                int dia = Integer.parseInt(sc.nextLine());
                System.out.print("Hora: ");
                int hora = Integer.parseInt(sc.nextLine());
                System.out.print("Minuto: ");
                int minuto = Integer.parseInt(sc.nextLine());


                LocalDateTime datahora = LocalDateTime.of(ano,mes,dia,hora,minuto);
                Eventos eventos = new Eventos(nome_evento, endereco, categoria, descricao, datahora );
                usuario.adicionareventos(eventos);
                System.out.print("Evento Cadastrado !!!");
            }
            else if (opcao == 2){
                System.out.println("\n=== Meus Eventos === ");
                if (usuario.getEventos().isEmpty()){
                    System.out.println("Nenhum evento encontrado");
                }else {
                    usuario.ordenarEventosPorData();
                    for (Eventos evento_usuario : usuario.getEventos()){
                        System.out.println(evento_usuario);

                    }
                }
            }
            else if (opcao == 3){
                if (usuario.getEventos().isEmpty()){
                    System.out.println("Nenhum evento encontrado");
                    continue;
                }
                listareventos(usuario);
                int idx = lerint(sc, "Digite o número que quer confirmar: ");
                boolean ok = usuario.confirmarEventoporIndice(idx);
                System.out.println(ok ? "Presença confirmado" : "ìndice inválido");


            }
            else if (opcao == 4){
                if (usuario.getEventos().isEmpty()){
                    System.out.println("Nenhum evento encontrado");
                    continue;
                }
                listareventos(usuario);
                int idx = lerint(sc, "Digite o número do evento que quer cancelar: ");
                boolean ok = usuario.removerEventoporIndice(idx);
                System.out.print(ok ? "Cancelamento realizado" : "Indice inválido" );
            }

            else if (opcao == 5){
                if (usuario.getEventos().isEmpty()){
                    System.out.println("Nenhum evento encontrado");
                    continue;
                }
                listareventos(usuario);
                int idx = lerint(sc , "Digite o número do evento que deseja excluir: ");
                boolean ok = usuario.excluirEventoporIndice(idx);
                System.out.println(ok ? "Evento excluido com sucesso" : "Indice inválido");
            }

            else if (opcao == 6){
                usuario.salvarEventos(evFile);
                System.out.println("Saindo... Até mais tarde...");
                break;
            }
        }


    }

    private static void listareventos(@NotNull Usuario usuario){
        if (usuario.getEventos().isEmpty()){
            System.out.println("Nenhum evento encontrado");
        }
        int i = 1 ;
        for (Eventos evento_usuario : usuario.getEventos()){
            System.out.println((i++) + ". " + evento_usuario);
        }
    }
    private static int lerint(@NotNull Scanner sc, String prompt){
        while (true){
            System.out.println(prompt);
            String s = sc.nextLine().trim();
            try{
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Digite um número válido");
            }
        }
    }


}




