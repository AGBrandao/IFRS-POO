import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Administrador {
    // classe que operacionaliza o sistema. tem lista de todos os hoteis, quartos (podem estar sem hotel) e clientes
    private String nome;
    private String id;
    private List < Hotel > ListaDeHoteis;
    private List < Cliente > ListaDeClientes;
    private List < Quarto > ListaDeQuartos;
    // daqui para baixo são variáveis auxiliares para os métodos
    private Boolean checadorDeWhile = false;
    private int indiceSelecionador;
    private Scanner entrada = new Scanner(System.in);
    private Hotel hotelTemp = null;
    private Quarto quartoTemp = null;
    private Reserva reserva = null;
    private Cliente clienteDoHotel = null;

    // construtor com o necessário
    public Administrador(String nome, String id) {
        this.nome = nome;
        this.id = id;
        ListaDeHoteis = new ArrayList < > ();
        ListaDeClientes = new ArrayList < > ();
        ListaDeQuartos = new ArrayList < > ();
    }
    // movi as opções do main para métodos de ADM para reduzir o tamanho do main
    // aqui estão todos os seletores (hotel, quarto, cliente, reserva) e criadores de objetos
    //------------------------------------------------------Hoteis
    //getter de lista
    public List < Hotel > getListaDeHoteis() {
        return ListaDeHoteis;
    }
    // opção 1 
    public void adicionarHotel() {
        System.out.println("Digite o nome do hotel: ");
        String nomeDoHotel = entrada.nextLine();
        System.out.println("Digite o endereço do hotel: ");
        String enderecoDoHotel = entrada.nextLine();
        this.ListaDeHoteis.add(new Hotel(nomeDoHotel, enderecoDoHotel));
        System.out.println("Hotel adicionado com sucesso!");
    }
    // método para remover hotel (não utilizado)
    public void removerHotel(Hotel hotel) {
        for (int i = 0; i < ListaDeHoteis.size(); i++) {
            if (hotel == ListaDeHoteis.get(i)) ListaDeHoteis.remove(hotel);
        }
        System.out.println("Hotel " + hotel + "removido com sucesso!");
    }

    // opção 2
    // lista os hoteis
    public void listarHoteis() {
        for (int i = 0; i < ListaDeHoteis.size(); i++) {
            System.out.println(i + ": " + ListaDeHoteis.get(i)); // lista hoteis com índice
        }
    }
    // opção 4
    public void funcaoListarQuartosDisponiveis() {
        // pergunta o hotel antes de listar quartos
        System.out.println("Digite o índice do hotel para listar os quartos disponíveis:");
        this.listarHoteis();
        indiceSelecionador = entrada.nextInt();
        if (indiceSelecionador >= 0 && indiceSelecionador < this.getListaDeHoteis().size()) {
            hotelTemp = this.getListaDeHoteis().get(indiceSelecionador);
            hotelTemp.listarQuartosDisponiveis(); // lista usando método da classe hotel
        } else {
            System.out.println("Erro! Índice inválido!");
        }
    }
    // método interno para pescar hotelTemp para verificar se hotel é null no main
    public Hotel getHotelTemp() {
        return hotelTemp;
    }
    //------------------------------------------------------Clientes
    public void listarClientes() {
        for (int i = 0; i < ListaDeClientes.size(); i++) {
            System.out.println(i + ": " + ListaDeClientes.get(i)); // lista clientes com índice
        }
    }
    //getter de lista
    public List < Cliente > getListaDeClientes() {
        return ListaDeClientes;
    }
    // cadastrar cliente
    public Cliente adicionarCliente() {
        System.out.println("Antes de fazer uma reserva, precisamos de alguns dados para cadastrá-lo: ");

        System.out.println("Digite o seu nome: ");
        String nomeDoCliente = entrada.nextLine();
        System.out.println("Email do cliente: ");
        String emailDoCliente = entrada.nextLine();
        System.out.println("Telefone do cliente: ");
        String telefoneDoCliente = entrada.nextLine();
        clienteDoHotel = new Cliente(nomeDoCliente, emailDoCliente, telefoneDoCliente);
        this.ListaDeClientes.add(clienteDoHotel);
        System.out.println("Cliente cadastrado com sucesso!");

        return clienteDoHotel;
    }
    // método interno da para pescar o cliente certo
    public Cliente getClienteDoHotel() {
        checadorDeWhile = false;

        do {
            System.out.println("Selecione o cliente: ");
            this.listarClientes();
            indiceSelecionador = entrada.nextInt();
            if (indiceSelecionador >= 0 && indiceSelecionador < this.getListaDeClientes().size()) {
                clienteDoHotel = this.getListaDeClientes().get(indiceSelecionador);
                checadorDeWhile = true;
            } else {
                System.out.println("Erro! Índice inválido!");
            }
        } while (!checadorDeWhile);

        return clienteDoHotel;
    }
    //------------------------------------------------------Reserva    
    // método interno para opção 5
    public Reserva montarReserva() {
        // pergunta o quarto do hotel. main não deixa escolher quarto se nenhum hotel existe. só aceita quartos de hoteis. sem trailers aqui.
        // não pode estar em Reserva, pois precisa do ADM para verificar dentro do hotel
        checadorDeWhile = false;
        do {
            System.out.println("Digite o índice do quarto para reservar:");
            indiceSelecionador = entrada.nextInt();
            if (indiceSelecionador >= 0 && indiceSelecionador < this.getHotelTemp().getListaDeQuartos().size()) {
                quartoTemp = this.getHotelTemp().getListaDeQuartos().get(indiceSelecionador);
                checadorDeWhile = true;
            } else {
                System.out.println("Erro! Índice inválido!");
            }
        } while (!checadorDeWhile);
        entrada.nextLine();
        // datas não são legais =/
        // parse=converte string no que vem antes do parse
        checadorDeWhile = false;
        do {
            System.out.println("Data de entrada no formato ano-mês-dia: ");
            String dataDeEntradaEmTexto = entrada.nextLine();
            LocalDate dataDeEntrada = LocalDate.parse(dataDeEntradaEmTexto);
            System.out.println("Data de saída no formato ano-mês-dia: ");
            String dataDeSaidaEmTexto = entrada.nextLine();
            LocalDate dataDeSaida = LocalDate.parse(dataDeSaidaEmTexto);
            if (dataDeEntrada.isBefore(dataDeSaida)) {
                reserva = new Reserva(clienteDoHotel, quartoTemp, dataDeEntrada, dataDeSaida);
                checadorDeWhile = true;
            } else {
                System.out.println("Erro! data de saída deve ser posterior à data de entrada!");
            }
        } while (!checadorDeWhile);
        // retorna reserva vinculada tanto ao cliente quanto ao quarto. cliente adiciona reserva à sua lista de reservas pelo main
        return reserva;
    }
    //------------------------------------------------------Quartos
    // getter de lista
    public List < Quarto > getListaDeQuartos() {
        return ListaDeQuartos;
    }
    // opção 8
    // lista TODOS os quartos, independente do hotel
    public void listarQuartos() {
        if (!ListaDeQuartos.isEmpty()) {
            for (int i = 0; i < ListaDeQuartos.size(); i++) { // lista quartos com índice
                System.out.println(i + ": " + ListaDeQuartos.get(i) + " vinculado ao hotel " + ListaDeQuartos.get(i).getHotel());
            }
        } // se não há quartos, sysout avisa        
        else {
            System.out.println("Erro! Nenhum quarto cadastrado!");
        }
    }
    // opção 3
    // cria quarto e pergunta onde (ou se) vincular a algum hotel
    public void adicionarQuarto() {
        System.out.println("Digite o número do quarto: ");
        String numeroQuarto = entrada.nextLine();
        System.out.println("Digite o tipo de quarto: ");
        String tipoDeQuarto = entrada.nextLine();
        System.out.println("Digite o preço do quarto: ");
        int precoDoQuarto = entrada.nextInt();

        if (this.getListaDeHoteis().isEmpty() == false) {
            System.out.println("Você quer vincular esse quarto a um hotel existente? 1 = sim | 2 = não");
            entrada.nextLine();

            indiceSelecionador = entrada.nextInt();
            switch (indiceSelecionador) {
                case 1:
                    if (this.getListaDeHoteis().isEmpty()) {
                        System.out.println("Erro! Nenhum hotel cadastrado!");
                        break;
                    }
                    // pergunta qual hotel
                    checadorDeWhile = false;
                    do {
                        System.out.println("Digite o índice do hotel para adicionar um quarto:");
                        this.listarHoteis();
                        indiceSelecionador = entrada.nextInt();
                        if (indiceSelecionador >= 0 && indiceSelecionador < this.getListaDeHoteis().size()) {
                            hotelTemp = this.getListaDeHoteis().get(indiceSelecionador);

                            quartoTemp = new Quarto(numeroQuarto, tipoDeQuarto, precoDoQuarto, hotelTemp);
                            this.ListaDeQuartos.add(quartoTemp);
                            hotelTemp.adicionarQuarto(quartoTemp);
                            checadorDeWhile = true;
                        } else {
                            System.out.println("Erro! Índice inválido!");
                        }
                    } while (!checadorDeWhile);
                    break;

                case 2: // é um trailer!            
                    System.out.println("Esse quarto ficou sem hotel!");
                    quartoTemp = new Quarto(numeroQuarto, tipoDeQuarto, precoDoQuarto, null);
                    this.ListaDeQuartos.add(quartoTemp);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } else { // se não existe hotel, tudo é trailer.
            System.out.println("Esse quarto ficou sem hotel!");
            quartoTemp = new Quarto(numeroQuarto, tipoDeQuarto, precoDoQuarto, null);
            this.ListaDeQuartos.add(quartoTemp);
        }
    }
    // seleciona quartos da lista geral
    public Quarto selecionaQuarto() {
        do {
            System.out.println("Selecione um quarto: ");
            this.listarQuartos();
            indiceSelecionador = entrada.nextInt();
            if (indiceSelecionador >= 0 && indiceSelecionador < this.getListaDeQuartos().size()) {
                quartoTemp = this.getListaDeQuartos().get(indiceSelecionador);
                checadorDeWhile = true;
            } else {
                System.out.println("Erro! Índice inválido!");
            }
        } while (!checadorDeWhile);
        return quartoTemp;
        }
    // opção 9
    // muda os quartos de hoteis
    public void gerenciarQuartos() {
        this.selecionaQuarto();
        // Verificações para setar variavel Hotel dentro de quarto de acordo com a escolha do usuário; -1= null
        checadorDeWhile = false;
        do {
            System.out.println("Digite o índice do hotel para adicionar um quarto ou -1 para deixar o quarto sem hotel:");
            this.listarHoteis();
            indiceSelecionador = entrada.nextInt();
            if (indiceSelecionador == -1 && quartoTemp.getHotel() == null) { // segue nulo (fica num loop sem esse if)
                quartoTemp.setHotel(null);
                checadorDeWhile = true;
            } else if (indiceSelecionador == -1 && quartoTemp.getHotel() != null) { // remove quarto de hotel
                hotelTemp = quartoTemp.getHotel();
                hotelTemp.removerQuarto(quartoTemp);
                quartoTemp.setHotel(null);
                checadorDeWhile = true;
            } else if (indiceSelecionador >= 0 && indiceSelecionador < this.getListaDeHoteis().size()) { // move quarto para algum hotel
                hotelTemp = this.getListaDeHoteis().get(indiceSelecionador);
                quartoTemp.setHotel(this.getListaDeHoteis().get(indiceSelecionador));
                hotelTemp.adicionarQuarto(quartoTemp);
                checadorDeWhile = true;
            } else {
                System.out.println("Erro! Índice inválido!");
            }
        } while (!checadorDeWhile);
    }
    //------------------------------------------------------Outros
    @Override // só para sair o "erro" do java de não usar variável
    public String toString() {
        return "Administrador [nome=" + nome + ", id=" + id + "]";
    }
}