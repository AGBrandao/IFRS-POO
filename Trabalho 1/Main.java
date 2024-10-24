import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Administrador novoADM = new Administrador("Dono", "admin");
        int opcao;

        do {
            System.out.println("===== Menu Principal =====");
            System.out.println("1. Adicionar Novo Hotel");
            System.out.println("2. Listar Todos os Hotéis");
            System.out.println("3. Adicionar Quarto a um Hotel");
            System.out.println("4. Listar Quartos Disponíveis em um Hotel");
            System.out.println("5. Fazer Reserva");
            System.out.println("6. Cancelar Reserva");
            System.out.println("7. Listar Reservas de um Cliente");
            System.out.println("8. Listar todos os quartos");
            System.out.println("9. Gerencias quartos");
            System.out.println("0. Sair\n");

            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();
            Cliente clienteDoHotel = null; //cliente dummy criado aqui para fazer referências futuras

            switch (opcao) {
                case 1:
                    entrada.nextLine(); // coloquei esses comandos por tudo para evitar que strings sejam nulas (acontece "aleatóriamente"). não funcionou.                      
                    novoADM.adicionarHotel(); // todo hotel é gerenciado pelo ADM
                    break;

                case 2:
                    if (novoADM.getListaDeHoteis().isEmpty()) { // verifica se há hotel
                        System.out.println("Erro! Nenhum hotel cadastrado!");
                        break;
                    }
                    novoADM.listarHoteis(); // todo hotel é gerenciado pelo ADM
                    break;

                case 3:
                    entrada.nextLine();
                    novoADM.adicionarQuarto(); // todo quarto existe com ou sem hotel. estão guardados em uma lista de ADM E nas listas internas dos hoteis se houver
                    break;

                case 4:
                    if (novoADM.getListaDeHoteis().isEmpty()) { // precisa de hotel para listar quartos de hoteis
                        System.out.println("Erro! Nenhum hotel cadastrado!");
                        break;
                    }
                    novoADM.funcaoListarQuartosDisponiveis(); // passa pelo ADM para selecionar o hotel, mas quem fornece a lista é a classe Hotel
                    break;

                case 5:
                    if (novoADM.getListaDeHoteis().isEmpty()) { // cliente só pode reservar quarto vinculado a um hotel
                        System.out.println("Erro! Nenhum hotel cadastrado!");
                        break;
                    }

                    if (!novoADM.getListaDeClientes().isEmpty()) { // permite reutilizar clientes
                        System.out.println("Você já é nosso cliente cadastrado? 1 = sim | 2 = não");
                        opcao = entrada.nextInt();
                        switch (opcao) {
                            case 1: // lista de clientes do ADM
                                clienteDoHotel = novoADM.getClienteDoHotel();
                                break;

                            case 2: // cadastra novo cliente
                                entrada.nextLine();
                                clienteDoHotel = novoADM.adicionarCliente();
                                break;

                            default:
                                System.out.println("Opção inválida. Tente novamente.");

                                break;
                        }
                    } else { // se não existe cliente, cadastra novo cliente
                        entrada.nextLine();
                        clienteDoHotel = novoADM.adicionarCliente();
                    }
                    // impede prosseguimento se nenhum hotel possui quarto
                    if (novoADM.getHotelTemp() == null) {
                        System.out.println("Erro! Nenhum hotel tem quarto cadastrado!");
                        break;
                    }
                    // impede prosseguimento se nenhum hotel possui quarto disponível
                    if (novoADM.getHotelTemp().listarQuartosDisponiveis().size() < 1) {
                        break;
                    }
                    // faz a reserva usando a classe cliente com ajuda de ADM para selecionar o quarto (lista de quartos existe em ADM e hotel foi escolhido anteriormente)
                    clienteDoHotel.fazerReserva(novoADM.montarReserva());
                    break;

                case 6:
                    if (novoADM.getListaDeClientes().isEmpty()) { // sem cliente, sem cancelamento
                        System.out.println("Erro! Nenhum cliente cadastrado!");
                        break;
                    }
                    // seleciona o cliente
                    clienteDoHotel = novoADM.getClienteDoHotel();
                    // se cliente não possui reservas, interrompe
                    if (clienteDoHotel.getListaDeReservas().size() == 0) {
                        System.out.println("Erro! Cliente sem reservas cadastradas!");
                        break;
                    }

                    // passa pelo ADM para selecionar o reserva, mas quem fornece a lista e efetua o cancelamento é a classe Cliente
                    clienteDoHotel.funcaoCancelarReserva();
                    break;

                case 7:
                    if (novoADM.getListaDeClientes().isEmpty()) { // se não há clientes, não há reservas
                        System.out.println("Erro! Nenhum cliente cadastrado!");
                        break;
                    }
                    clienteDoHotel = novoADM.getClienteDoHotel(); // pergunta o cliente
                    clienteDoHotel.listarReservasCliente(); // chama método de Cliente para listar reservas
                    break;

                case 8:
                    novoADM.listarQuartos(); // lista todos os quartos, independente de hotel
                    break;

                case 9:
                    if (novoADM.getListaDeQuartos().isEmpty()) { // não permite gerenciar quartos se não há quartos
                        System.out.println("Erro! Nenhum quarto cadastrado!");
                        break;
                    }
                    if (novoADM.getListaDeHoteis().isEmpty()) { // não permite gerenciar quartos se não há hoteis (vai mover para onde?)
                        System.out.println("Erro! Nenhum hotel cadastrado!");
                        break;
                    }
                    novoADM.gerenciarQuartos();
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        entrada.close();
    }
}