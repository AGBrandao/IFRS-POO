import java.time.LocalDate;

public class Reserva {
    // cada objeto reserva está vinculado a um objeto quarto e um objeto cliente
    // essa classe altera a disponibilidade de quartos
    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;

    //Relação: A reserva está associada a um cliente e a um quarto específico (composição).

    // construtor com cliente e quarto obrigatórios (composição)
    public Reserva(Cliente cliente, Quarto quarto, LocalDate dataCheckIn, LocalDate dataCheckOut) {
        this.cliente = cliente;
        this.quarto = quarto;
        // reserva precisa de cliente e de quarto definidos
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        quarto.reservar();
        // quando a reserva é criada, o quarto é reservado
    }
    // método para confirmar reserva (não utilizado)
    public void confirmaReserva() {
        if (quarto.getEstaDisponivel()) {
            System.out.println("Reserva confirmada! O quarto " + quarto + " está reservado para o cliente " + cliente);
        } else {
            System.out.println("Reserva não confirmada! O quarto " + quarto + " segue vago!");
        }
    }
    // método interno para opção 6
    public void cancelarReserva() {
        if (quarto.getEstaDisponivel()) {
            System.out.println("Quarto " + quarto + " não estava reservado!");
        } else {
            quarto.liberar();
            System.out.println("Reserva para o quarto " + quarto + " cancelada com sucesso!");
        }
    }
    // dados da reserva
    @Override
    public String toString() {
        return quarto + " com data de Check-In " + dataCheckIn + " e data de Check-Out " + dataCheckOut + ".";
    }
}