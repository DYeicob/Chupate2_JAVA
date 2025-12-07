package gal.uvigo.esei.aed1.chupatedos.core; // Define el paquete en el que se encuentra esta clase

import java.util.Stack; // Importa la clase Stack para manejar una pila de cartas

public class Table { // Define la clase Table, que representa la mesa de juego donde se colocan las cartas

    private Stack<Card> cardsOnTable; // Atributo que almacena la pila de cartas que están sobre la mesa
    private Card topCard; // Atributo que almacena la carta que está en la parte superior de la mesa (última jugada)

    public Table() { // Constructor que inicializa la mesa y la pila de cartas
        this.cardsOnTable = new Stack<>(); // Inicializa la pila de cartas en la mesa
        this.topCard = null; // Inicializa la carta superior como null (sin carta al principio)
    }

    public void placeCard(Card card) { // Método para colocar una carta en la mesa
        cardsOnTable.push(card); // Añade la carta a la pila de cartas en la mesa
        topCard = card; // Actualiza la carta superior de la mesa
    }

    public Card getTopCard() { // Método para obtener la carta superior de la mesa
        return topCard; // Devuelve la carta que está en la parte superior de la mesa
    }

    public Stack<Card> collectCards() { // Método para recolectar todas las cartas de la mesa
        Stack<Card> collectedCards = new Stack<>(); // Crea una nueva pila para almacenar las cartas recolectadas
        while (!cardsOnTable.isEmpty()) { // Mientras haya cartas en la mesa
            collectedCards.push(cardsOnTable.pop()); // Extrae las cartas de la mesa y las añade a la nueva pila
        }
        return collectedCards; // Devuelve la pila de cartas recolectadas
    }

    public int getNumberOfCardsOnTable() { // Método para obtener el número de cartas que hay en la mesa
        return cardsOnTable.size(); // Devuelve el tamaño de la pila de cartas en la mesa
    }
}
