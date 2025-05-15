
package comdiegocano.proyectofinalpoo;


import java.util.ArrayList;

public class Mano {
    private ArrayList<Carta> cartas;

    public Mano() {
        this.cartas = new ArrayList<>();
    }

    public void recibirCarta(Carta carta) {
        cartas.add(carta);
    }

    public void reemplazarCartas(ArrayList<Integer> indices, ArrayList<Carta> nuevas) {
        for (int i = 0; i < indices.size(); i++) {
            cartas.set(indices.get(i), nuevas.get(i));
        }
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void vaciar() {
        cartas.clear();
    }
}

