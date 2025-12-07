package gal.uvigo.esei.aed1.chupatedos.core; // Define el paquete del proyecto donde se encuentra esta clase

public enum Card { // Enumeración que representa todas las cartas posibles de una baraja española
    // Cartas del palo de oros
    AS_OROS(1, Suit.OROS),
    DOS_OROS(2, Suit.OROS),
    TRES_OROS(3, Suit.OROS),
    CUATRO_OROS(4, Suit.OROS),
    CINCO_OROS(5, Suit.OROS),
    SEIS_OROS(6, Suit.OROS),
    SIETE_OROS(7, Suit.OROS),
    SOTA_OROS(10, Suit.OROS),
    CABALLO_OROS(11, Suit.OROS),
    REY_OROS(12, Suit.OROS),
    // Cartas del palo de copas
    AS_COPAS(1, Suit.COPAS),
    DOS_COPAS(2, Suit.COPAS),
    TRES_COPAS(3, Suit.COPAS),
    CUATRO_COPAS(4, Suit.COPAS),
    CINCO_COPAS(5, Suit.COPAS),
    SEIS_COPAS(6, Suit.COPAS),
    SIETE_COPAS(7, Suit.COPAS),
    SOTA_COPAS(10, Suit.COPAS),
    CABALLO_COPAS(11, Suit.COPAS),
    REY_COPAS(12, Suit.COPAS),
    // Cartas del palo de espadas
    AS_ESPADAS(1, Suit.ESPADAS),
    DOS_ESPADAS(2, Suit.ESPADAS),
    TRES_ESPADAS(3, Suit.ESPADAS),
    CUATRO_ESPADAS(4, Suit.ESPADAS),
    CINCO_ESPADAS(5, Suit.ESPADAS),
    SEIS_ESPADAS(6, Suit.ESPADAS),
    SIETE_ESPADAS(7, Suit.ESPADAS),
    SOTA_ESPADAS(10, Suit.ESPADAS),
    CABALLO_ESPADAS(11, Suit.ESPADAS),
    REY_ESPADAS(12, Suit.ESPADAS),
    // Cartas del palo de bastos
    AS_BASTOS(1, Suit.BASTOS),
    DOS_BASTOS(2, Suit.BASTOS),
    TRES_BASTOS(3, Suit.BASTOS),
    CUATRO_BASTOS(4, Suit.BASTOS),
    CINCO_BASTOS(5, Suit.BASTOS),
    SEIS_BASTOS(6, Suit.BASTOS),
    SIETE_BASTOS(7, Suit.BASTOS),
    SOTA_BASTOS(10, Suit.BASTOS),
    CABALLO_BASTOS(11, Suit.BASTOS),
    REY_BASTOS(12, Suit.BASTOS);

    private final int number;     // Valor numérico de la carta (1–7, 10–12)
    private final Suit suit;      // Palo al que pertenece la carta (OROS, COPAS, ESPADAS, BASTOS)

    Card(int number, Suit suit) { // Constructor del enum que asigna valor y palo a cada carta
        this.number = number;
        this.suit = suit;
    }

    public int getNumber() { // Método getter que devuelve el número de la carta
        return this.number;
    }

    public Suit getSuit() { // Método getter que devuelve el palo de la carta
        return this.suit;
    }

    public boolean isPlayable(Card topCard) { // Método que determina si la carta actual se puede jugar sobre otra
        return this.getNumber() == topCard.getNumber() || this.getSuit() == topCard.getSuit(); // Se puede jugar si tiene el mismo número o el mismo palo
    }

    @Override
    public String toString() { // Representación en texto de la carta, con formato especial para los palos y valores
        StringBuilder sb = new StringBuilder();             // Construcción del string final
        StringBuilder suitToDisplay = new StringBuilder(); // Construcción del nombre del palo con formato
        switch (this.suit.name()) { // Se ajusta el formato según el palo para que quede alineado al mostrarse
            case "OROS" ->
                suitToDisplay.append("  ").append(this.suit).append(" ");
            case "COPAS" ->
                suitToDisplay.append(" ").append(this.suit).append(" ");
            case "ESPADAS" ->
                suitToDisplay.append(this.suit);
            case "BASTOS" ->
                suitToDisplay.append(this.suit).append(" ");
        }
        StringBuilder numberToDisplay = new StringBuilder(); // Construcción del número con posible espacio
        numberToDisplay.append(this.number < 10 ? " " + this.number : this.number);
        sb.append(" [ ").append(suitToDisplay).append(" | ").append(numberToDisplay).append(" ] "); // Formato final de la carta: [  PALO  |  VALOR ]
        return sb.toString();
    }
}
