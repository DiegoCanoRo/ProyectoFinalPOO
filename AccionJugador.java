public class AccionJugador {
    public enum Tipo {
        APOSTAR,
        IGUALAR, 
        RETIRARSE, 
        PASAR 
    }

    private Tipo tipo; 
    private int cantidad;  

    public AccionJugador(Tipo tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }
    public Tipo getTipo() {
        return tipo;
    }
    public int getCantidad() {
        return cantidad;
    }
}
