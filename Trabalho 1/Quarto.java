public class Quarto {
    // quartos podem estar vinculados ou não a hoteis
    // podem ser movidos entre hoteis e para fora deles (virar um trailer)
    // disponibilidade gerenciada pela classe reserva
    private String numero;
    private String tipo;
    private double preco;
    private Boolean estaDisponivel;
    private Hotel hotel;

    //Relação: O quarto é associado a uma reserva quando reservado (associação).

    // construtor
    public Quarto(String numero, String tipo, double preco, Hotel hotel) {
        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
        estaDisponivel = true;
        //boolean de disponibilidade -> reserva altera esse boolean
        this.hotel = hotel;
        // construtor com hotel para ser vinculado (ou não)
    }
    //verificador de disponibilidade
    public Boolean getEstaDisponivel() {
        return estaDisponivel;
    }
    // função para reservar só é usada quando se cria um objeto reserva
    public void reservar() {
        if (estaDisponivel) {
            estaDisponivel = false;
            System.out.println("Quarto " + numero + " reservado com sucesso!");
        } else {
            System.out.println("Quarto " + numero + " já está reservado!");
        }
    }
    // método interno para uso de reserva (opção 6)
    public void liberar() {
        if (!estaDisponivel) {
            estaDisponivel = true;
        } else {
            System.out.println("Quarto " + numero + " já está liberado!");
        }
    }
    // getter do hotel ao qual o quarto está associado
    public Hotel getHotel() {
        return hotel;
    }
    // modificador do hotel do quarto (opção 9)
    public void setHotel(Hotel hotelNovo) {
        this.hotel = hotelNovo;
        if (hotelNovo == null) {
            System.out.println("Esse quarto não pertence a nenhum hotel!");
        } else {
            System.out.println("Esse quarto pertence agora ao hotel " + hotelNovo);
        }
    }
    // dados do quarto
    @Override
    public String toString() {
        return numero + " do tipo " + tipo + " com preço " + preco;
    }
}