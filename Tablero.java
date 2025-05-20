package comdiegocano.proyectofinalpoo;


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
    
    
    //agrega una carta al tablero
    public void agregarCartaMesa(Carta carta) {
        cartasTablero.add(carta);
    }
    
    //reinicia el pozo de las apuestas
    public void reiniciarPozo() {
        pozo = 0;
    }
    
    //agrega una apuesta al pozo
    public void agregarAlPozo(int cantidad) {
        pozo += cantidad;
    }

    public int getPozo() {
        return pozo;
    }
    
    //regresa la baraja que se esta usando
    public ArrayList<Carta> getBaraja() {
        return baraja.getBaraja();
    }
    
    //regresa las cartas que estan en el tablero
    public ArrayList<Carta> getCartasTablero() {
        return cartasTablero;
    }
    
    public void limpiarTablero(){
        cartasTablero.clear();
    }
}