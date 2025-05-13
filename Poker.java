package comdiegocano.proyectofinalpoo;


import java.util.*;

public abstract class Poker {
    protected ArrayList<Jugador> jugadores;
    protected ArrayList<Carta> mazo;
    protected Tablero tablero;
    protected int pozo;
    

    public Poker(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.mazo = tablero.crearMazo();
        this.pozo = 0;
    }

    public abstract void iniciarJuego();
    protected abstract void repartirCartas();
    public abstract int evaluarMano(ArrayList<Carta> mano);

    protected void barajarMazo() {
        Collections.shuffle(mazo);
    }

    protected Carta repartirCarta() {
        return mazo.remove(0);
    }
}

