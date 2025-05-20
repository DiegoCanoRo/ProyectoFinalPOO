package comdiegocano.proyectofinalpoo;


import java.util.*;

public abstract class Poker {
    protected ArrayList<Jugador> jugadores;
    protected Repartidor repartidor; 
    protected Tablero tablero;
    protected int pozo;
    protected Turnos turno;
    protected Interfaz interfaz;
    
    public Poker(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.tablero = new Tablero(); 
        this.repartidor = new Repartidor();
        this.turno = new Turnos(this.jugadores);
        this.pozo = 0;
    }

    public abstract void iniciarJuego();
    protected abstract void repartirCartas();
    public abstract int evaluarMano(ArrayList<Carta> mano);

    protected void barajarMazo() {
       repartidor.barajar();
    }
    
    protected Carta repartirCarta() {
        return repartidor.repartirUna();
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    public void setInterfaz(Interfaz interfaz) {
        this.interfaz = interfaz;
    }
    
    
    public String getTurnoActualComoTexto() {
        return "Turno " + turno.obtenerJugadorActual().getNombre();
    }
    
     public String toStringApuestas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jugadores.size(); i++) {
            sb.append("Apuesta del jugador ").append(i + 1).append(": ")
                    .append(jugadores.get(i).getApuestaActual()).append("\n");
        }
        sb.append("\nPozo: ").append(tablero.getPozo());
        return sb.toString();
    }
     
    protected boolean quedanJugadoresConFichas() {
    for (Jugador jugador : jugadores) {
        if (jugador.estaActivo() && jugador.getFichas() > 0) {
            return true;
        }
    }
    return false;
}
}