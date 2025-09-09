import org.jetbrains.annotations.NotNull;
import java.time.LocalDateTime;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner input = new Scanner(System.in);

        // Input do cadastro dos usuarios

        System.out.println("=== CADASTRO DE USUÁRIO ===");
        System.out.println("Digite seu nome: ");
        String nome = input.nextLine();
        System.out.println("Digite seu email: ");
        String email = input.nextLine();
        System.out.println("Digite seu senha: ");
        String senha = input.nextLine();

        Usuario usuario = new Usuario(nome, email, senha);
        System.out.println("Usuario Cadastrado: " + usuario);

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
            int opcao = input.nextInt();


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
                int ano = sc.nextInt();
                System.out.print("Mes: ");
                int mes = sc.nextInt();
                System.out.print("Dia: ");
                int dia = sc.nextInt();
                System.out.print("Hora: ");
                int hora = sc.nextInt();
                System.out.print("Minuto: ");
                int minuto = sc.nextInt();
                sc.nextLine();

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




