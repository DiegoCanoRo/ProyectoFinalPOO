
package comdiegocano.proyectofinalpoo;

/**
 *
 * @author diego
 */
import java.util.*;

public class Jugador {

    private String nombre;
    private ArrayList<Carta> mano;
    private int fichas;
    private boolean activo; //si sigue jugando o dejo la mano

    public Jugador(String nombre, int fichas) {
        this.nombre = nombre;
        this.fichas = fichas;
        this.mano = new ArrayList<>();
        this.activo = true;
    }

    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }

    public void reemplazarCartas(ArrayList<Integer> indices, ArrayList<Carta> nuevas) {
        for (int i = 0; i < indices.size(); i++) {
            mano.set(indices.get(i), nuevas.get(i));
        }
    }

    //falta
//    public List<Integer> elegirCartasADescartar() {
//        return new ArrayList<>(); 
//    }

    public void apostar(int cantidad) {
        fichas -= cantidad;
    }
    
    
    
    // mtodo para que el jugador gane el bote
    public void ganarBote(int cantidad) {
        fichas += cantidad;
    }

    // metodo para que el jugador realice una apuesta
    public void realizarApuesta(int cantidad) {
        apostar(cantidad);
    }

    public void retirarse() {
        activo = false;
    }

    public boolean estaActivo() {
        return activo;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFichas() {
        return fichas;
    }

    
    //to string para info del jugador
    public String obtenerEstado() {
        StringBuilder estado = new StringBuilder();

        estado.append("Nombre: ").append(nombre).append("\n");
        estado.append("Fichas: ").append(fichas).append("\n");
        estado.append("Activo: ").append(activo ? "Sí" : "No").append("\n");
        
        //estado.append("Mano: ");

//        for (Carta carta : mano) {
//            estado.append(carta.toString()).append(" "); 
//        }

        return estado.toString();
    }
}
