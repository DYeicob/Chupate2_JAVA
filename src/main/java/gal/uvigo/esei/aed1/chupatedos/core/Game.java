package gal.uvigo.esei.aed1.chupatedos.core; // Define el paquete del proyecto donde se encuentra esta clase

import gal.uvigo.esei.aed1.chupatedos.iu.IU; // Importa la interfaz IU, que probablemente maneja la interacción con el usuario
import java.util.ArrayList; // Importa ArrayList para manejar listas dinámicas de jugadores
import java.util.List; // Importa la interfaz List para trabajar con listas

public class Game { // Define la clase principal del juego

    private final IU iu; // Instancia de la interfaz IU para la interacción con el usuario
    private List<Player> players; // Lista de jugadores
    private DeckOfCards deck; // Instancia del mazo de cartas
    private Table table; // Instancia de la mesa de juego, donde se colocan las cartas
    private int currentPlayerIndex; // Índice del jugador cuyo turno es el actual
    private int direction = 1; // Dirección del juego (1 para sentido horario, -1 para antihorario)
    private Card lastPlayedCard = null; // Última carta jugada en la mesa

    public Game(IU iu) { // Constructor que inicializa los componentes del juego
        this.iu = iu; // Inicializa la interfaz de usuario
        this.players = new ArrayList<>(); // Inicializa la lista de jugadores
        this.deck = new DeckOfCards(); // Inicializa el mazo de cartas
        this.table = new Table(); // Inicializa la mesa
        this.currentPlayerIndex = 0; // Empieza con el primer jugador
    }

    public void play() { // Método principal para iniciar el juego
        int numberOfPlayers = getNumberOfPlayers(); // Obtiene el número de jugadores
        createPlayers(numberOfPlayers); // Crea los jugadores según el número seleccionado
        initializeGame(); // Inicializa el mazo y reparte las cartas
        playGame(); // Empieza la partida
    }

    private int getNumberOfPlayers() { // Método que obtiene el número de jugadores de la interfaz de usuario
        int numberOfPlayers = iu.readNumber("Cuantos jugadores van a jugar (2-5)? "); // Lee el número de jugadores
        while (numberOfPlayers < 2 || numberOfPlayers > 5) { // Verifica que el número esté entre 2 y 5
            numberOfPlayers = iu.readNumber("Numero invalido. Cuantos jugadores van a jugar (2-5)? "); // Si es inválido, pide de nuevo el número
        }
        return numberOfPlayers; // Devuelve el número de jugadores válido
    }

    private void createPlayers(int numberOfPlayers) { // Método para crear los jugadores y agregar sus nombres
        players.clear(); // Limpia la lista de jugadores
        for (int i = 0; i < numberOfPlayers; i++) { // Itera para crear el número de jugadores deseado
            String name = iu.readString("Nombre del jugador " + (i + 1) + ": "); // Pide el nombre de cada jugador
            players.add(new Player(name)); // Crea un nuevo jugador y lo agrega a la lista
        }
    }

    private void initializeGame() { // Método para inicializar el mazo y repartir las cartas
        deck.shuffle(); // Baraja el mazo
        for (Player player : players) { // Para cada jugador
            for (int j = 0; j < 7; j++) { // Cada jugador recibe 7 cartas
                player.addCard(deck.drawCard()); // Reparte una carta
            }
        }
        Card topCard = deck.drawCard(); // Se saca la primera carta del mazo para colocarla en la mesa
        table.placeCard(topCard); // Coloca la carta en la mesa
        iu.displayMessage("Primera carta en la mesa: " + topCard); // Muestra el mensaje con la carta en la mesa
        lastPlayedCard = topCard; // Guarda la última carta jugada
        if (topCard.getNumber() == 2) { // Si la carta es un 2
            Player firstPlayer = players.get(currentPlayerIndex); // Obtiene al primer jugador
            drawCards(firstPlayer, 2); // Hace que el primer jugador robe 2 cartas
            iu.displayMessage(firstPlayer.getName() + " empieza robando 2 cartas y pierde el turno.");
            currentPlayerIndex = getNextPlayerIndex(); // Pasa el turno al siguiente jugador
        } else if (topCard.getNumber() == 7) { // Si la carta es un 7
            direction *= -1; // Cambia la dirección del juego
            iu.displayMessage("El sentido del juego ha cambiado.");
            currentPlayerIndex = getNextPlayerIndex(); // Pasa el turno al siguiente jugador
        }
    }

    private void playGame() { // Método que gestiona el ciclo del juego (turnos de los jugadores)
        boolean gameRunning = true; // Bandera para determinar si el juego sigue en ejecución
        do {
            Player currentPlayer = players.get(currentPlayerIndex); // Obtiene el jugador actual
            iu.displayMessage("\nTurno de: " + currentPlayer.getName()); // Muestra el nombre del jugador en turno
            displayGameState(currentPlayerIndex); // Muestra el estado actual del juego (mesa, cartas restantes)
            if (table.getTopCard() == null) { // Si no hay carta en la mesa, termina el juego
                iu.displayMessage("No hay carta en la mesa, el juego no puede continuar.");
                break;
            }
            List<Card> playableCards = currentPlayer.getPlayableCards(table); // Obtiene las cartas jugables del jugador
            if (!playableCards.isEmpty()) { // Si tiene cartas jugables
                playCard(currentPlayer, playableCards); // Juega una carta
            } else { // Si no tiene cartas jugables
                drawCard(currentPlayer); // Roba una carta
            }
            if (currentPlayer.getHandSize() == 0) { // Si el jugador se queda sin cartas
                iu.displayMessage(currentPlayer.getName() + " ha ganado!"); // Muestra que ha ganado
                gameRunning = false; // Termina el juego
                break;
            }
            if (lastPlayedCard != null) { // Si hay una carta jugada recientemente
                switch (lastPlayedCard.getNumber()) {
                    case 2 -> { // Si se jugó un 2
                        currentPlayerIndex = getNextPlayerIndex(); // Pasa el turno al siguiente jugador
                        Player nextPlayer = players.get(currentPlayerIndex); // Obtiene al siguiente jugador
                        drawCards(nextPlayer, 2); // El siguiente jugador roba 2 cartas
                        iu.displayMessage(nextPlayer.getName() + " roba 2 cartas y pierde su turno.");
                        currentPlayerIndex = getNextPlayerIndex(); // Pasa el turno al siguiente jugador
                    }
                    case 7 -> { // Si se jugó un 7
                        direction *= -1; // Cambia la dirección del juego
                        iu.displayMessage("El sentido del juego ha cambiado!");
                        currentPlayerIndex = getNextPlayerIndex(); // Pasa el turno al siguiente jugador
                    }
                    default ->
                        currentPlayerIndex = getNextPlayerIndex(); // Para cualquier otra carta, solo pasa el turno
                }
            } else {
                currentPlayerIndex = getNextPlayerIndex(); // Pasa el turno al siguiente jugador
            }
        } while (gameRunning); // Mientras el juego esté corriendo
    }

    private void playCard(Player currentPlayer, List<Card> playableCards) { // Método para jugar una carta seleccionada por el jugador
        iu.displayMessage("Cartas jugables: " + playableCards); // Muestra las cartas que el jugador puede jugar
        boolean validSelection; // Variable para controlar la validez de la selección
        do {
            int cardIndex = iu.readNumber("Selecciona la carta a jugar (0-" + (playableCards.size() - 1) + "): "); // Pide al jugador que seleccione una carta
            if (cardIndex >= 0 && cardIndex < playableCards.size()) { // Si la selección es válida
                Card selectedCard = playableCards.get(cardIndex); // Obtiene la carta seleccionada
                currentPlayer.remove(selectedCard); // El jugador juega la carta
                table.placeCard(selectedCard); // Coloca la carta en la mesa
                lastPlayedCard = selectedCard; // Guarda la carta como la última jugada
                validSelection = true; // Selección válida
            } else { // Si la selección es inválida
                iu.displayMessage("Seleccion invalida. Por favor, intenta de nuevo.");
                validSelection = false; // Selección inválida
            }
        } while (!validSelection); // Repite si la selección no es válida
    }

    private void drawCard(Player currentPlayer) { // Método para robar una carta cuando el jugador no tiene cartas jugables
        iu.displayMessage("No tienes cartas jugables. Robando una carta..."); // Informa al jugador que robará una carta
        refillDeckIfEmpty(); // Recarga el mazo si está vacío
        if (deck.deckSize() > 0) { // Si el mazo tiene cartas
            Card drawnCard = deck.drawCard(); // El jugador roba una carta
            currentPlayer.addCard(drawnCard); // La carta se agrega al mazo del jugador
            iu.displayMessage("Has robado la carta: " + drawnCard); // Muestra la carta robada
            Card topCard = table.getTopCard(); // Obtiene la carta en la mesa
            if (topCard == null) { // Si no hay carta en la mesa, no puede jugar
                iu.displayMessage("No hay carta en la mesa. No puedes jugar.");
                return;
            }
            if (drawnCard.isPlayable(topCard)) { // Si la carta robada es jugable
                iu.displayMessage("Colocando la carta robada en la mesa.");
                currentPlayer.remove(drawnCard); // El jugador la juega
                table.placeCard(drawnCard); // Coloca la carta en la mesa
                lastPlayedCard = drawnCard; // Actualiza la última carta jugada
            } else {
                iu.displayMessage("La carta robada no es jugable. Pierdes el turno."); // Si no se puede jugar, pierde el turno
                lastPlayedCard = null; // Restablece la última carta jugada
            }
        } else {
            iu.displayMessage("No se pudo robar ninguna carta incluso después de recargar la baraja.");
            lastPlayedCard = null; // Si el mazo está vacío, no puede robar cartas
        }
    }

    private void refillDeckIfEmpty() { // Método para recargar el mazo si está vacío
        if (deck.deckSize() == 0) { // Si el mazo está vacío
            iu.displayMessage("No hay cartas en la baraja. Recolectando cartas de la mesa..."); // Informa que se recogerán cartas de la mesa
            List<Card> cardsOnTable = table.collectCards(); // Recoge las cartas de la mesa
            if (!cardsOnTable.isEmpty()) { // Si hay cartas en la mesa
                Card topCardOnTable = table.getTopCard(); // Obtiene la carta en la mesa
                List<Card> otherCards = new ArrayList<>(cardsOnTable); // Copia las cartas en la mesa
                otherCards.remove(topCardOnTable); // Remueve la carta superior para no duplicarla
                deck.addCards(otherCards); // Añade las cartas restantes al mazo
                deck.shuffle(); // Baraja el mazo
                table.placeCard(topCardOnTable); // Vuelve a colocar la carta superior en la mesa
                iu.displayMessage("Las cartas de la mesa han sido recolectadas y barajadas."); // Informa que las cartas han sido recolectadas
            } else {
                iu.displayMessage("No hay cartas disponibles para recargar la baraja."); // Si no hay cartas en la mesa
            }
        }
    }

    private void drawCards(Player player, int numberOfCards) { // Método para hacer que un jugador robe varias cartas
        for (int i = 0; i < numberOfCards; i++) { // Repite el proceso de robar cartas
            refillDeckIfEmpty(); // Recarga el mazo si está vacío
            if (deck.deckSize() > 0) { // Si el mazo tiene cartas
                Card drawnCard = deck.drawCard(); // El jugador roba una carta
                player.addCard(drawnCard); // La carta se añade al jugador
                iu.displayMessage(player.getName() + " roba: " + drawnCard); // Informa qué carta robó
            } else {
                iu.displayMessage(player.getName() + " no pudo robar carta. La baraja sigue vacía.");
            }
        }
    }

    private int getNextPlayerIndex() { // Método para obtener el índice del siguiente jugador según la dirección
        return (currentPlayerIndex + direction + players.size()) % players.size(); // Calcula el índice del siguiente jugador
    }

    private void displayGameState(int currentPlayerIndex) { // Método para mostrar el estado actual del juego
        iu.displayMessage("Carta en la mesa: " + table.getTopCard()); // Muestra la carta en la mesa
        iu.displayMessage("Numero de cartas en la mesa: " + table.getNumberOfCardsOnTable()); // Muestra el número de cartas en la mesa
        iu.displayMessage("Numero de cartas restantes en la baraja: " + deck.deckSize()); // Muestra cuántas cartas quedan en el mazo
        Player currentPlayer = players.get(currentPlayerIndex); // Obtiene el jugador actual
        iu.displayMessage("Cartas de " + currentPlayer.getName() + ": " + currentPlayer.getHand()); // Muestra las cartas del jugador
    }
}
