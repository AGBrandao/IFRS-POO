import java.util.List;
import java.util.ArrayList;

public class Hotel {
    // hotel agrega vários quartos e fornece lista de quartos (total e disponíveis)
    private String nome;
    private String endereco;
    private List < Quarto > ListaDeQuartos;

    //Relação: Um hotel tem vários quartos (agregação).

    // construtor com lista de quartos
    public Hotel(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        ListaDeQuartos = new ArrayList < > ();
        // lista de quartos agregados pelo hotel
    }
    // getter de lista
    public List < Quarto > getListaDeQuartos() {
        return ListaDeQuartos;
    }
    // checa se o quarto já existe no hotel e adiciona se falso
    public void adicionarQuarto(Quarto quarto) {
        if (ListaDeQuartos.contains(quarto)) {
            System.out.println("Hotel já contém o quarto " + quarto + "!");
        } else {
            ListaDeQuartos.add(quarto);
            System.out.println("Quarto " + quarto + " adicionado com sucesso!");
        }
    }
    // retira quarto removido pela opção 9 do hotel atual
    public void removerQuarto(Quarto quarto) {
        ListaDeQuartos.remove(quarto);
        System.out.println("Quarto " + quarto + " removido com sucesso do hotel " + nome + "!");
    }
    // método interno para opção 4
    public List < Quarto > listarQuartosDisponiveis() {
        List < Quarto > ListaDeQuartosDisponiveis = new ArrayList < > ();

        Boolean haQuartosDisponiveis = false;
        for (int i = 0; i < ListaDeQuartos.size(); i++) {
            if (ListaDeQuartos.get(i).getEstaDisponivel()) { // lista quartos com índice
                System.out.println(i + ": " + ListaDeQuartos.get(i) + " está disponível no hotel " + this);
                ListaDeQuartosDisponiveis.add(ListaDeQuartos.get(i));
                haQuartosDisponiveis = true;
            }
        }
        if (!haQuartosDisponiveis) { // não há quartos disponíveis
            System.out.println("Não há quartos disponíveis no hotel " + nome + "!");
        }
        return ListaDeQuartosDisponiveis; // precisa retornar uma lista para medir o size no main
    }
    // dados do hotel
    @Override
    public String toString() {
        return nome + " localizado em " + endereco + " cuja Lista De Quartos contém " + ListaDeQuartos.size() + " quarto(s).";
    }
}