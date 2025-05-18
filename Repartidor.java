import java.util.ArrayList;
import java.util.*;

public class Repartidor {

    private Baraja baraja;

    public Repartidor() {
        this.baraja = new Baraja();
    }

    public void barajar() {
        baraja.barajar();
    }

    public Carta repartirUna() {
        return baraja.repartirCarta();
    }
    /*
     * Se encarga de repartir cartas a los jugadores
     * donde cada ciclo for representa una ronda
     * Esta puesto para funcionar con el poker de texas
     * ya que en sus reglas se reparten 2 cartas iniciales
     * Puedes modificarlo para tu version de poker.
     */
    public void repartirManos(ArrayList<Jugador> jugadores, int cartasPorJugador) {
        for (int i = 0; i < cartasPorJugador; i++) {
            for (Jugador jugador : jugadores) {
                jugador.recibirCarta(repartirUna());
            }
        }
    }

    public Baraja getBaraja() {
        return baraja;
    }
}
