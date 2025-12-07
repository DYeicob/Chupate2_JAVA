package gal.uvigo.esei.aed1.chupatedos.iu; // Define el paquete en el que se encuentra la clase Main

import gal.uvigo.esei.aed1.chupatedos.core.Game; // Importa la clase Game, que representa el juego

public class Main { // Define la clase Main, que es el punto de entrada de la aplicación

    public static void main(String[] args) { // Método principal que inicia el juego
        IU iu = new IU(); // Crea una nueva instancia de la clase IU (Interfaz de Usuario)
        Game chupateDos = new Game(iu); // Crea una nueva instancia del juego, pasando la interfaz de usuario (IU)
        chupateDos.play(); // Inicia el juego llamando al método play() de la clase Game
    }
}
