package comdiegocano.proyectofinalpoo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Baraja {

    private ArrayList<Carta> baraja;

    public Baraja() {
        this.baraja = crearBaraja();
    }

    public ArrayList<Carta> crearBaraja() {
        String[] palos = {"Corazon", "Diamante", "Trebol", "Espada"};
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        HashMap<String, Integer> valorNumerico = new HashMap<>();
        valorNumerico.put("2", 2);
        valorNumerico.put("3", 3);
        valorNumerico.put("4", 4);
        valorNumerico.put("5", 5);
        valorNumerico.put("6", 6);
        valorNumerico.put("7", 7);
        valorNumerico.put("8", 8);
        valorNumerico.put("9", 9);
        valorNumerico.put("10", 10);
        valorNumerico.put("J", 11);
        valorNumerico.put("Q", 12);
        valorNumerico.put("K", 13);
        valorNumerico.put("A", 14);

        ArrayList<Carta> nuevoMazo = new ArrayList<>();
        for (String palo : palos) {
            for (String valor : valores) {
                nuevoMazo.add(new Carta(valor, palo, valorNumerico.get(valor)));
            }
        }

        Collections.shuffle(nuevoMazo);
        return nuevoMazo;
    }
    
    
     public void barajar() {
        Collections.shuffle(baraja);
    }

    public Carta repartirCarta() {
        return baraja.remove(0);
    }
    
    public ArrayList<Carta> getBaraja() {
        return baraja;
    }

}
