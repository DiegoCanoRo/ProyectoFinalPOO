package comdiegocano.proyectofinalpoo;
public class Carta implements Comparable<Carta>{

    private String valor;
    private String palo;
    private int valorNumerico;
    private boolean bocaAbajo;

    public Carta(String valor, String palo, int valorNumerico) {
        this.valor = valor;
        this.palo = palo;
        this.valorNumerico = valorNumerico;
        this.bocaAbajo = false; //carta boca abajo por defecto
    }

    public String getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }

    //para ver si esta ya sabes
    public boolean getBocaAbajo() {
        return bocaAbajo;
    }

    public void voltearCarta() {
        this.bocaAbajo = !this.bocaAbajo;
    }

    public int getValorNumerico() {
        return valorNumerico;
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
    
    @Override
    public int compareTo(Carta otraCarta) {
        return Integer.compare(this.valorNumerico, otraCarta.valorNumerico);
    }
}
