package gal.uvigo.esei.aed1.chupatedos.core; // Define el paquete del proyecto donde se encuentra esta clase

import java.util.Collections; // Importa la clase Collections para operaciones con colecciones (como barajar)
import java.util.List; // Importa la interfaz List para trabajar con listas
import java.util.Stack; // Importa la clase Stack que representa una pila (LIFO)

public class DeckOfCards { // Define la clase que representa el mazo de cartas

    private Stack<Card> cards; // Declara una pila de cartas (Stack), que mantendrá el mazo de cartas

    public DeckOfCards() { // Constructor de la clase DeckOfCards
        cards = new Stack<>(); // Inicializa la pila de cartas
        Collections.addAll(cards, Card.values()); // Agrega todas las cartas del enum Card al mazo
    }

    public void shuffle() { // Método para barajar el mazo de cartas
        Collections.shuffle(cards); // Baraja el mazo usando el método shuffle de Collections
    }

    public Card drawCard() { // Método para sacar una carta del mazo
        return cards.isEmpty() ? null : cards.pop(); // Si el mazo está vacío, devuelve null; de lo contrario, saca una carta
    }

    public int deckSize() { // Método que devuelve el número de cartas en el mazo
        return cards.size(); // Devuelve el tamaño de la pila (cantidad de cartas en el mazo)
    }

    public void addCards(List<Card> newCards) { // Método para agregar cartas al mazo
        cards.addAll(newCards); // Agrega las cartas proporcionadas por la lista al mazo
    }
}
