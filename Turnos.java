import java.util.*;

public class Turnos {

    private ArrayList<Jugador> jugadores; 
    private int indiceActual;// Posicion dl jugador en turno             
    //Constructor 
        public Turnos(ArrayList<Jugador> jugadores) {
        this.jugadores = new ArrayList<>(jugadores); 
        this.indiceActual = 0;
    }

    public Jugador obtenerJugadorActual() {
        return jugadores.get(indiceActual);
    }
    //Para avnazar al siguiente jugador
    public void siguiente() {
        int total = jugadores.size();
        do {
            indiceActual = (indiceActual + 1) % total; // La manera en que avanza es de forma circular
        } while (!jugadores.get(indiceActual).estaActivo());
    }

      public void eliminarJugador(Jugador jugador) {
        jugadores.remove(jugador);
        // Si se retira un jugador que estaba antes del actual, retrocede una posición
        if (indiceActual >= jugadores.size()) {
            indiceActual = 0;
        }
    }

    //Cuando existan mas de 2 jugadores el juego continua
    public boolean quedanVariosJugadoresActivos() {
        int activos = 0;
        for (Jugador j : jugadores) {
            if (j.estaActivo()) {
                activos++;
            }
        }
        return activos > 1;
    }
}
