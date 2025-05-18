package comdiegocano.proyectofinalpoo;
import java.util.*;

import java.util.ArrayList;
/*
 * no tomes en cuenta esta clase, 
 * solo la agregue para poder compilar e ir probando las demas clases
 */
public class Prueba{

    public static void main(String[] args) {
        Baraja baraja = new Baraja();

        baraja.barajar();

        Jugador jugador = new Jugador("TestPlayer", 1000);
        Carta carta = baraja.repartirCarta();
        jugador.recibirCarta(carta);

        System.out.println("Jugador: " + jugador.getNombre());
        System.out.println("Carta repartida: " + carta);
        System.out.println("Cartas en mano del jugador:");
        for (Carta c : jugador.getMano()) {
            System.out.println("  " + c);
        }
    }
}
