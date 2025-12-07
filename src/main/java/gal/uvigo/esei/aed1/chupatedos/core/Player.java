package gal.uvigo.esei.aed1.chupatedos.core; // Define el paquete en el que se encuentra esta clase

import java.util.ArrayList; // Importa la clase ArrayList para manejar listas dinámicas
import java.util.List; // Importa la interfaz List para trabajar con listas

public class Player { // Define la clase Player que representa a un jugador en el juego

    private String name; // Atributo para almacenar el nombre del jugador
    private List<Card> hand; // Atributo que almacena las cartas que tiene el jugador en su mano

    public Player(String name) { // Constructor que inicializa el nombre y la mano del jugador
        this.name = name; // Inicializa el nombre del jugador
        this.hand = new ArrayList<>(); // Inicializa la mano como una lista vacía
    }

    public void addCard(Card card) { // Método para añadir una carta a la mano del jugador
        hand.add(card); // Añade la carta a la mano del jugador
    }

    public void remove(Card card) { // Método para eliminar una carta de la mano del jugador
        hand.remove(card); // Elimina la carta especificada de la mano del jugador
    }

    public String getName() { // Método para obtener el nombre del jugador
        return name; // Devuelve el nombre del jugador
    }

    public List<Card> getHand() { // Método para obtener la lista de cartas en la mano del jugador
        return hand; // Devuelve la lista de cartas del jugador
    }

    public int getHandSize() { // Método para obtener el número de cartas en la mano del jugador
        return hand.size(); // Devuelve el tamaño de la mano (número de cartas)
    }

    public List<Card> getPlayableCards(Table table) { // Método para obtener las cartas jugables del jugador según la carta en la mesa
        List<Card> playableCards = new ArrayList<>(); // Crea una lista vacía para almacenar las cartas jugables
        Card topCard = table.getTopCard(); // Obtiene la carta en la parte superior de la mesa
        for (Card card : hand) { // Itera sobre todas las cartas del jugador
            if (card.isPlayable(topCard)) { // Si la carta es jugable con la carta en la mesa
                playableCards.add(card); // Añade la carta a la lista de cartas jugables
            }
        }
        return playableCards; // Devuelve la lista de cartas jugables
    }

    @Override
    public String toString() { // Método para representar al jugador como una cadena de texto
        StringBuilder handString = new StringBuilder(name + ": "); // Crea un StringBuilder para representar la mano
        for (Card card : hand) { // Itera sobre todas las cartas en la mano
            handString.append(card.toString()).append(" "); // Añade la representación de cada carta a la cadena
        }
        return handString.toString(); // Devuelve la cadena con el nombre del jugador y sus cartas
    }
}
