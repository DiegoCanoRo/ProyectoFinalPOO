package comdiegocano.proyectofinalpoo;
import java.util.*;


import java.util.*;

public class Tablero {
    private Baraja baraja;
    private int pozo;
    private ArrayList<Carta> cartasTablero;

    public Tablero() {
        this.baraja = new Baraja();
        this.pozo = 0;
        this.cartasTablero = new ArrayList<>();
    }

    public void agregarCartaMesa(Carta carta) {
        cartasTablero.add(carta);
    }

    public void reiniciarPozo() {
        pozo = 0;
    }

    public void agregarAlPozo(int cantidad) {
        pozo += cantidad;
    }

    public int getPozo() {
        return pozo;
    }

    public ArrayList<Carta> getBaraja() {
        return baraja.getBaraja();
    }

    public ArrayList<Carta> getTablero() {
        return cartasTablero;
    }
}