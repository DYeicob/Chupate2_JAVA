package gal.uvigo.esei.aed1.chupatedos.iu; // Define el paquete en el que se encuentra la clase IU

import gal.uvigo.esei.aed1.chupatedos.core.Card; // Importa la clase Card, que representa una carta del juego
import gal.uvigo.esei.aed1.chupatedos.core.DeckOfCards; // Importa la clase DeckOfCards, que representa el mazo de cartas
import gal.uvigo.esei.aed1.chupatedos.core.Player; // Importa la clase Player, que representa a un jugador del juego
import gal.uvigo.esei.aed1.chupatedos.core.Suit; // Importa la clase Suit, que representa los palos de las cartas
import gal.uvigo.esei.aed1.chupatedos.core.Table; // Importa la clase Table, que representa la mesa de juego
import java.util.Collection; // Importa la interfaz Collection
import java.util.LinkedList; // Importa la clase LinkedList, una implementación de lista
import java.util.List; // Importa la interfaz List, una colección ordenada
import java.util.Scanner; // Importa la clase Scanner para leer entradas del teclado

public class IU { // Define la clase IU, que gestiona la interacción con el usuario

    private final Scanner keyboard; // Declara un objeto Scanner para leer entradas del teclado

    public IU() { // Constructor que inicializa el objeto Scanner
        keyboard = new Scanner(System.in); // Crea una nueva instancia de Scanner para leer desde el teclado
    }

    /**
     * Lee un número del teclado
     *
     * @param msg El mensaje a visualizar para indicar al usuario que ingrese un
     * número
     * @return El número leído como entero
     */
    public int readNumber(String msg) {
        boolean repeat; // Variable para controlar si el ciclo debe repetirse en caso de error
        int toret = 0; // Variable para almacenar el número leído

        do {
            repeat = false; // Inicializa la variable a false antes de cada intento de lectura
            System.out.print(msg); // Muestra el mensaje para solicitar la entrada
            try {
                toret = Integer.parseInt(keyboard.nextLine()); // Intenta leer una línea del teclado y convertirla a entero
            } catch (NumberFormatException exc) { // Si ocurre una excepción (por ejemplo, si no es un número válido)
                repeat = true; // Establece repeat a true para repetir la lectura
            }
        } while (repeat); // Si el valor ingresado no es un número válido, repite el proceso
        return toret; // Devuelve el número leído
    }

    /**
     * Lee un string del teclado
     *
     * @param msg Mensaje a mostrar antes de la lectura del string
     * @return El string leído
     */
    public String readString(String msg) {
        String toret; // Variable para almacenar el string leído
        System.out.print(msg); // Muestra el mensaje para solicitar la entrada
        toret = keyboard.nextLine(); // Lee la línea de texto desde el teclado
        return toret; // Devuelve el string leído
    }

    /**
     * Muestra un mensaje por pantalla
     *
     * @param msg El mensaje a mostrar
     */
    public void displayMessage(String msg) {
        System.out.println(msg); // Muestra el mensaje en la consola
    }
}
