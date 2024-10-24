import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    // cada objeto cliente gerencia sua lista de reservas
    private String nome;
    private String email;
    private String telefone;
    private List < Reserva > ListaDeReservas;
    private Scanner entrada = new Scanner(System.in);


    //Relação: Um cliente pode ter várias reservas (associação).

    // construtor
    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        ListaDeReservas = new ArrayList < > ();
        // reservas são uma lista do cliente; ele existe sem elas
    }
    // getter de lista
    public List < Reserva > getListaDeReservas() {
        return ListaDeReservas;
    }
    // adiciona reserva
    public void fazerReserva(Reserva reserva) {
        ListaDeReservas.add(reserva);
    }
    // opção 6
    //lista as reservas, cancela a reserva selecionada e remove da lista do cliente
    public void funcaoCancelarReserva() {

        this.listarReservasCliente();

        Boolean checadorDeWhile = false;
        do {
            System.out.println("Selecione a reserva para cancelar: ");
            int indiceSelecionador = entrada.nextInt();
            if (indiceSelecionador >= 0 && indiceSelecionador < this.getListaDeReservas().size()) {
                this.getListaDeReservas().get(indiceSelecionador).cancelarReserva(); // chama método de Reserva para liberar quarto
                ListaDeReservas.remove(this.getListaDeReservas().get(indiceSelecionador));
                checadorDeWhile = true;
            } else {
                System.out.println("Erro! Índice inválido!");
            }
        } while (!checadorDeWhile);
    }
    // opção 7 lista as reservas do cliente selecionado
    public void listarReservasCliente() {
        if (ListaDeReservas.size() > 0) {
            System.out.println("O cliente " + nome + " possui as seguintes reservas:");
            for (int i = 0; i < ListaDeReservas.size(); i++) {
                System.out.println(i + ": " + ListaDeReservas.get(i)); // lista reservas com índice
            }
        } else {
            System.out.println("O cliente " + nome + " não possui reservas.");
        }
    }
    // dados do cliente
    @Override
    public String toString() {
        return nome + " e-mail " + email + " telefone " + telefone;
    }
}