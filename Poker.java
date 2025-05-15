package comdiegocano.proyectofinalpoo;


import java.util.*;

public abstract class Poker {
    protected ArrayList<Jugador> jugadores;
    protected Baraja baraja;  // antes era ArrayList<Carta> mazo
    protected Tablero tablero;
    protected int pozo;

    public Poker(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.tablero = new Tablero(); // aún puedes usarlo para cartas en mesa, pozo, etc.
        this.baraja = new Baraja();   // creas la baraja
        this.pozo = 0;
    }

    public abstract void iniciarJuego();
    protected abstract void repartirCartas();
    public abstract int evaluarMano(ArrayList<Carta> mano);

    protected void barajarMazo() {
        baraja.barajar();
    }

    protected Carta repartirCarta() {
        return baraja.repartirCarta();
    }
}