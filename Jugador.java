package comdiegocano.proyectofinalpoo;

/**
 *
 * @author diego
 */
import java.util.*;

public class Jugador {

    private String nombre;
    private Mano mano;
    private int fichas;
    private boolean activo;//si sigue en el juego o abandono
    private boolean haActuado;
    private int apuestaActual = 0;
    
    public Jugador(String nombre, int fichas) {
        this.nombre = nombre;
        this.fichas = fichas;
        this.mano = new Mano();
        this.activo = true;
    }
    
    public void reiniciarApuestaActual(){
        apuestaActual = 0;
    }
    
    public int getApuestaActual(){
        return apuestaActual;
    }
    
    public void setHaActuado(boolean haActuado) {
        this.haActuado = haActuado;
    }

    public boolean getHaActuado() {
        return haActuado;
    }

    public void recibirCarta(Carta carta) {
        mano.recibirCarta(carta);
    }

    public void reemplazarCartas(ArrayList<Integer> indices, ArrayList<Carta> nuevas) {
        mano.reemplazarCartas(indices, nuevas);
    }

    public ArrayList<Integer> elegirCartasADescartarConInterfaz(Interfaz interfaz) {
        return interfaz.mostrarDialogoDescarte(getMano());
    }

    public void apostar(int cantidad) {
        fichas -= cantidad;
        apuestaActual +=cantidad;
    }

    public void ganarBote(int cantidad) {
        fichas += cantidad;
    }

    public void realizarApuesta(int cantidad) {
        apostar(cantidad);
    }

    public void retirarse() {
        activo = false;
        mano.vaciar();//se vacia su mano porque abandono
    }

    public boolean estaActivo() {
        return activo;
    }

    public ArrayList<Carta> getMano() {
        return mano.getCartas();
    }

    public String getNombre() {
        return nombre;
    }

    public int getFichas() {
        return fichas;
    }

    public String obtenerEstado() {
        StringBuilder estado = new StringBuilder();
        estado.append("Nombre: ").append(nombre).append("\n");
        estado.append("Fichas: ").append(fichas).append("\n");
        estado.append("Activo: ").append(activo ? "Sí" : "No").append("\n");
        return estado.toString();
    }
}
