import java.util.*;

public class RondasApuestas {

    private int apuestaActual; 
    private int pozo;          // Suma total de las fichas apostadas
    private ArrayList<Jugador> jugadores; 

    //Constructor con la lsita de los jugadores e inicializa las apuestas
    public RondasApuestas(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.apuestaActual = 0;
        this.pozo = 0;
    }
    
    //Para reiniciar las apuestas si es necesario
    public void nuevaRonda() {
        apuestaActual = 0;
    }

    public void apostar(Jugador jugador, int cantidad) {
        if (cantidad >= apuestaActual) {
            jugador.apostar(cantidad);  // Se descuenta de sus fichas
            pozo += cantidad;           // Se suma al pozo las apuestas
            apuestaActual = cantidad;   // Actualiza
        } else {
            System.out.println(jugador.getNombre() + " debe igualar o superar la apuesta actual de " + apuestaActual);
        }
    }

    public void retirarse(Jugador jugador) {
        jugador.retirarse(); // Marca al jugador como inactivo
    }

       public int getPozo() {
        return pozo;
    }

        public void reiniciarPozo() {
        pozo = 0;
    }

    public int getApuestaActual() {
        return apuestaActual;
    }
}
