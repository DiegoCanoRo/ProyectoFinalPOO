package comdiegocano.proyectofinalpoo;


public class Carta {
    private String valor;
    private String palo;
    private int valorNumerico;

    public Carta(String valor, String palo, int valorNumerico) {
        this.valor = valor;
        this.palo = palo;
        this.valorNumerico = valorNumerico;
    }

    public String getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }

    public int getValorNumerico() {
        return valorNumerico;
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}

