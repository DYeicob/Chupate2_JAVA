# Proyecto AED I – Curso 2024/2025

## Implementación del juego **¡Chúpate 2!** en Java (Maven + NetBeans)

Este proyecto forma parte de la asignatura **Algoritmos y Estructuras de Datos I (AED I)** de la ESEI, y ha sido desarrollado en grupo siguiendo las especificaciones proporcionadas por el profesorado.

---

## 🎮 Descripción del Juego

**¡Chúpate 2!** es un juego de cartas basado en la baraja española. Sus mecánicas son similares al juego comercial *UNO*, pero con reglas tradicionales propias.

### Reglas Principales

* **Jugadores:** 2–5
* **Baraja:** 40 cartas (oros, copas, espadas, bastos)
* **Cartas numéricas:** 1 a 7
* **Figuras:** 10 (sota), 11 (caballo), 12 (rey)
* **Objetivo:** descartarse de todas las cartas

### Preparación de la partida

1. Barajar el mazo.
2. Repartir **7 cartas** por jugador.
3. Colocar **1 carta** boca arriba en la mesa.

### Mecánica de turnos

* Los turnos avanzan en sentido **inverso a las agujas del reloj**.
* Un/a jugador/a debe jugar una carta que coincida en **palo** o **número** con la carta en mesa.
* Si no puede, roba y:

  * juega la carta si es válida,
  * o pierde el turno.
* Si el mazo se agota, se barajan las cartas jugadas excepto la última.

### Cartas especiales

* **2:** el siguiente jugador roba **dos cartas** y pierde turno.
* **7:** cambia el **sentido** del juego.

*(Ambos efectos se aplican también si estas cartas son la inicial.)*

### Final de partida

Gana quien se quede sin cartas.

---

## 📁 Estructura del Proyecto

El proyecto sigue una estructura estándar **Maven**, dividida en módulos lógicos:

```
src/
└── main/
    ├── java/
    │   └── gal/uvigo/esei/aed1/chupatedos/
    │       ├── core/           # Lógica del juego
    │       │   ├── Card.java
    │       │   ├── DeckOfCards.java
    │       │   ├── Game.java
    │       │   ├── Player.java
    │       │   ├── Suit.java
    │       │   └── Table.java
    │       └── iu/             # Interfaz de usuario (texto)
    │           ├── IU.java
    │           └── Main.java
    └── resources/               # (vacío, si no se usan recursos)
```

Archivos generados por Maven:

```
target/
└── classes/gal/uvigo/esei/aed1/chupatedos/
    ├── core/*.class
    └── iu/*.class

maven-status/
└── maven-compiler-plugin/compile/
    ├── createdFiles.lst
    └── inputFiles.lst

pom.xml
```

---

## ▶️ Ejecución del Proyecto

### Requisitos

* **Java 17** o superior
* **Maven 3.8+**
* Entorno de desarrollo recomendado: **NetBeans** o **IntelliJ IDEA**

### Ejecutar desde Maven

```bash
mvn clean package
mvn exec:java -Dexec.mainClass="gal.uvigo.esei.aed1.chupatedos.iu.Main"
```

### Ejecutar desde NetBeans

1. Abrir el proyecto como **Proyecto Maven**.
2. Ejecutar directamente mediante el botón *Run*.
3. La clase principal es:

```
gal.uvigo.esei.aed1.chupatedos.iu.Main
```

---

## 🧩 Organización del Trabajo

El proyecto se realiza en grupos de **4–5 estudiantes**. Cada integrante debe conocer todas las partes del sistema: lógica del juego, interfaz y funcionamiento general.

La docencia supervisará el desarrollo a través de **tres entregas obligatorias**. Cada entrega debe defenderse por **todo el grupo**, y cualquier ausencia sin justificar implica expulsión del equipo.

La copia de código entre grupos implica **suspenso de la parte práctica para ambos**.

---

## 📌 Notas finales

Este README recoge toda la información necesaria para comprender, compilar y ejecutar el proyecto, así como las reglas del juego **¡Chúpate 2!**.
