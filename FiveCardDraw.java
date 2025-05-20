package comdiegocano.proyectofinalpoo;

import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class FiveCardDraw extends Poker {

    private static final int NUM_CARTAS_INICIALES = 5;
    private Fase faseActual = Fase.APUESTA_1;
    private HashSet<Jugador> jugadoresQueDescartaron = new HashSet<>();
    private HashSet<Jugador> jugadoresQueApostaron = new HashSet<>();

    public FiveCardDraw(ArrayList<Jugador> jugadores) {
        super(jugadores);
    }

    public enum Fase {
        APUESTA_1,
        DESCARTE,
        APUESTA_2,
        ENFRENTAMIENTO
    }

    @Override
    public void iniciarJuego() {

        barajarMazo();
        repartirCartas();

    }

    @Override
    public void repartirCartas() {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < NUM_CARTAS_INICIALES; i++) {
                jugador.recibirCarta(repartirCarta());
            }
        }
    }

    @Override
    public int evaluarMano(ArrayList<Carta> mano) {

        if (esEscaleraReal(mano)) {
            return 10;
        }
        if (esEscaleraDeColor(mano)) {
            return 9;
        }
        if (esPoker(mano)) {
            return 8;
        }
        if (esFull(mano)) {
            return 7;
        }
        if (esColor(mano)) {
            return 6;
        }
        if (esEscalera(mano)) {
            return 5;
        }
        if (esTrio(mano)) {
            return 4;
        }
        if (esDoblePar(mano)) {
            return 3;
        }
        if (esPar(mano)) {
            return 2;
        }
        return 1; // Carta alta
    }

    public void tomarDecisionTurnoActual() {

    if (jugadores.isEmpty()) return;

    Jugador jugador = turno.obtenerJugadorActual();

    if (!jugador.estaActivo() || jugador.getHaActuado()) {
        avanzarTurno();
        return;
    }

    if (jugador.getFichas() <= 0) {
        JOptionPane.showMessageDialog(null, jugador.getNombre() + " no tiene fichas. Se salta su turno.");
        jugador.setHaActuado(true);
        avanzarTurno();
        return;
    }

    String[] opciones;
    if (tablero.getPozo() == 0) {
        opciones = new String[]{"Pasar", "Apostar", "Retirarse"};
    } else {
        opciones = new String[]{"Igualar", "Subir apuesta", "Retirarse"};
    }

    String eleccion = (String) JOptionPane.showInputDialog(
            null,
            "Jugador " + jugador.getNombre() + ", elige tu acción:",
            "Ronda de Apuestas",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]
    );

    if (eleccion == null || eleccion.equals("Retirarse")) {
        jugador.retirarse();
        verificarGanadorPorAbandono();
        avanzarTurno();
        interfaz.actualizarInterfaz();
        return;
    }

    switch (eleccion) {
        case "Pasar":
            if (tablero.getPozo() > 0) {
                JOptionPane.showMessageDialog(null, "No puedes pasar si hay una apuesta activa.");
                return;
            }
            break;

        case "Igualar":
            int apuestaMaxima = obtenerApuestaMaxima();
            if (apuestaMaxima > jugador.getFichas()) {
                JOptionPane.showMessageDialog(null, "No tienes suficientes fichas para igualar.");
                return;
            }
            jugador.realizarApuesta(apuestaMaxima);
            tablero.agregarAlPozo(apuestaMaxima);
            break;

        case "Apostar":
        case "Subir apuesta":
            while (true) {
                try {
                    String input = JOptionPane.showInputDialog("¿Cuánto deseas apostar?");
                    if (input == null) return;
                    int cantidad = Integer.parseInt(input);
                    if (cantidad > jugador.getFichas()) {
                        JOptionPane.showMessageDialog(null, "No tienes suficientes fichas. Tienes: " + jugador.getFichas());
                        continue;
                    }
                    jugador.realizarApuesta(cantidad);
                    tablero.agregarAlPozo(cantidad);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor ingresa un número válido.");
                }
            }
            break;
    }

    jugador.setHaActuado(true);
    interfaz.actualizarInterfaz();

    if (rondaDeApuestasTerminada() || !quedanJugadoresConFichas()) {
        avanzarFase();
        avanzarTurno();
    } else {
        avanzarTurno();
    }
}

    private int obtenerApuestaMaxima() {
        return jugadores.stream()
                .mapToInt(Jugador::getApuestaActual) // Método que almacena la última apuesta hecha
                .max().orElse(0); // Si no hay apuestas, devuelve 0
    }

    private void avanzarTurno() {
        turno.siguiente();

        if (!turno.quedanVariosJugadoresActivos()) {
            avanzarFase();
        }

        interfaz.actualizarInterfaz();
    }

    public void ejecutarFaseActual(Interfaz interfaz) {
        switch (faseActual) {
            case APUESTA_1:
            case APUESTA_2:
                tomarDecisionTurnoActual(); // tu método actual para apostar
                break;

            case DESCARTE:
                realizarDescarteTurnoActual(); // pasar interfaz para mostrar checkbox
                break;

            case ENFRENTAMIENTO:
                mostrarGanadorEnMesa();
                break;
        }
    }

    private void avanzarFase() {
        for (Jugador jugador : jugadores) {
            jugador.setHaActuado(false);
        }

        switch (faseActual) {
            case APUESTA_1:
                faseActual = Fase.DESCARTE;

                break;
            case DESCARTE:

                faseActual = Fase.APUESTA_2;

                break;
            case APUESTA_2:
                faseActual = Fase.ENFRENTAMIENTO;

                break;
            case ENFRENTAMIENTO:
                // Aquí se puede mostrar el ganador
                break;
        }

        interfaz.actualizarInterfaz();
    }

    public void verificarGanadorPorAbandono() {
        List<Jugador> activos = jugadores.stream()
                .filter(Jugador::estaActivo)
                .collect(Collectors.toList());

        if (activos.size() == 1) {
            Jugador ganador = activos.get(0);
            JOptionPane.showMessageDialog(null, "Todos los demás se retiraron. " + ganador.getNombre() + " gana automáticamente la ronda.");
            // darle el poso
            faseActual = Fase.ENFRENTAMIENTO;
            interfaz.actualizarInterfaz();
        }
    }

    public boolean rondaDeApuestasTerminada() {
        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            if (jugador.estaActivo() && !jugador.getHaActuado()) {
                return false;
            }
        }

        iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            Jugador j = iterator.next();
            if (j.estaActivo()) {
                j.setHaActuado(false);
            }
        }

        return true;
    }

    public void realizarDescarteTurnoActual() {
        Jugador jugador = turno.obtenerJugadorActual();

        // Si el jugador ya descartó o está inactivo, saltarlo
        if (!jugador.estaActivo() || jugadoresQueDescartaron.contains(jugador)) {
            turno.siguiente();
            verificarFinDescarte();
            return;
        }

        // Descarte real
        ArrayList<Integer> cartasADescartar = jugador.elegirCartasADescartarConInterfaz(interfaz);
        ArrayList<Carta> cartasDescartadas = new ArrayList<>();

        for (int i = 0; i < cartasADescartar.size(); i++) {
            cartasDescartadas.add(jugador.getMano().get(cartasADescartar.get(i)));
        }

        // Añadir al mazo y a la mesa
        tablero.getBaraja().addAll(cartasDescartadas);
        for (Carta carta : cartasDescartadas) {
            tablero.agregarCartaMesa(carta);
        }
        Collections.shuffle(tablero.getBaraja());

        // Nuevas cartas
        ArrayList<Carta> nuevasCartas = new ArrayList<>();
        for (int i = 0; i < cartasADescartar.size(); i++) {
            nuevasCartas.add(tablero.getBaraja().remove(0));
        }

        jugador.reemplazarCartas(cartasADescartar, nuevasCartas);

        jugadoresQueDescartaron.add(jugador); // Marcar como ya descartado

        turno.siguiente();
        verificarFinDescarte();
        interfaz.actualizarInterfaz();
    }

    private void verificarFinDescarte() {
        long activos = jugadores.stream().filter(Jugador::estaActivo).count();

        // Si ya descartaron todos los activos, avanzar fase
        if (jugadoresQueDescartaron.size() == activos) {
            jugadoresQueDescartaron.clear(); // Preparar para futura ronda
            avanzarFase();
        }
    }

    public Jugador determinarGanador(ArrayList<Jugador> jugadoresRestantes) {
        Jugador ganador = jugadoresRestantes.get(0);
        for (Jugador jugador : jugadoresRestantes) {
            if (compararManos(jugador.getMano(), ganador.getMano()) > 0) {
                ganador = jugador;
            }
        }
        return ganador;
    }

    public void mostrarGanadorEnMesa() {
        // Limpiar el tablero
        tablero.limpiarTablero();

        // Obtener jugadores restantes
        ArrayList<Jugador> jugadoresRestantes = new ArrayList<>(
                jugadores.stream().filter(Jugador::estaActivo).collect(Collectors.toList())
        );

        if (jugadoresRestantes.isEmpty()) {
            interfaz.mostrarMensaje("No hay jugadores restantes.");
            return;
        }

        // Determinar el ganador de la ronda
        Jugador ganador = determinarGanador(jugadoresRestantes);

        // Cambiar turno para que refleje al ganador antes de evaluar la mejor jugada
        turno.setJugadorActual(ganador); // Asegurar que `obtenerMejorJugada()` evalúe su mano
        String mejorJugada = obtenerMejorJugada(); // Ahora usa la mano del ganador

        ArrayList<Carta> mejoresCartas = obtenerCartasDeMejorJugada();

        // Agregar las cartas de la mejor jugada al tablero
        for (Carta carta : mejoresCartas) {
            tablero.agregarCartaMesa(carta);
        }

        interfaz.actualizarInterfaz();
        // Mostrar mensaje en la interfaz
        interfaz.mostrarMensaje("El jugador " + ganador.getNombre()
                + " ganó " + tablero.getPozo() + " puntos con la jugada: " + mejorJugada);
    }

    public int compararManos(ArrayList<Carta> mano1, ArrayList<Carta> mano2) {
        int valorMano1 = evaluarMano(mano1);
        int valorMano2 = evaluarMano(mano2);

        if (valorMano1 > valorMano2) {
            return 1; // Mano 1 es mejor
        } else if (valorMano1 < valorMano2) {
            return -1; // Mano 2 es mejor
        } else {
            return compararCartas(mano1, mano2); // Si son iguales, se compara la carta más alta
        }
    }

    public boolean esEscaleraReal(ArrayList<Carta> mano) {
        if (esEscaleraDeColor(mano)) {
            ArrayList<Integer> valores = new ArrayList<>();
            for (Carta carta : mano) {
                valores.add(carta.getValorNumerico());
            }
            Collections.sort(valores);
            return valores.get(0) == 10 && valores.get(4) == 14; // Si el valor más bajo es 10 y el más alto es As
        }
        return false;
    }

    public boolean esEscaleraDeColor(ArrayList<Carta> mano) {
        if (esEscalera(mano)) {
            String palo = mano.get(0).getPalo();
            for (Carta carta : mano) {
                if (!carta.getPalo().equals(palo)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean esPoker(ArrayList<Carta> mano) {
        HashMap<Integer, Integer> frecuencia = new HashMap<>();
        for (Carta carta : mano) {
            frecuencia.put(carta.getValorNumerico(), frecuencia.getOrDefault(carta.getValorNumerico(), 0) + 1);
        }
        return frecuencia.containsValue(4);
    }

    public boolean esFull(ArrayList<Carta> mano) {
        HashMap<Integer, Integer> frecuencia = new HashMap<>();
        for (Carta carta : mano) {
            frecuencia.put(carta.getValorNumerico(), frecuencia.getOrDefault(carta.getValorNumerico(), 0) + 1);
        }
        return frecuencia.containsValue(3) && frecuencia.containsValue(2);
    }

    public boolean esColor(ArrayList<Carta> mano) {
        String palo = mano.get(0).getPalo();
        for (Carta carta : mano) {
            if (!carta.getPalo().equals(palo)) {
                return false;
            }
        }
        return true;
    }

    public boolean esEscalera(ArrayList<Carta> mano) {
        List<Integer> valores = new ArrayList<>();
        for (Carta carta : mano) {
            valores.add(carta.getValorNumerico());
        }
        Collections.sort(valores);
        for (int i = 0; i < 4; i++) {
            if (valores.get(i) + 1 != valores.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean esTrio(ArrayList<Carta> mano) {
        HashMap<Integer, Integer> frecuencia = new HashMap<>();
        for (Carta carta : mano) {
            frecuencia.put(carta.getValorNumerico(), frecuencia.getOrDefault(carta.getValorNumerico(), 0) + 1);
        }
        return frecuencia.containsValue(3);
    }

    public boolean esDoblePar(ArrayList<Carta> mano) {
        HashMap<Integer, Integer> frecuencia = new HashMap<>();
        for (Carta carta : mano) {
            frecuencia.put(carta.getValorNumerico(), frecuencia.getOrDefault(carta.getValorNumerico(), 0) + 1);
        }
        return frecuencia.values().stream().filter(c -> c == 2).count() == 2;
    }

    public boolean esPar(ArrayList<Carta> mano) {
        HashMap<Integer, Integer> frecuencia = new HashMap<>();
        for (Carta carta : mano) {
            frecuencia.put(carta.getValorNumerico(), frecuencia.getOrDefault(carta.getValorNumerico(), 0) + 1);
        }
        return frecuencia.containsValue(2);
    }

    public int compararCartas(ArrayList<Carta> mano1, ArrayList<Carta> mano2) {
        // Comparar las cartas de dos manos
        ArrayList<Integer> valoresMano1 = new ArrayList<>();
        ArrayList<Integer> valoresMano2 = new ArrayList<>();

        for (Carta carta : mano1) {
            valoresMano1.add(carta.getValorNumerico());
        }
        for (Carta carta : mano2) {
            valoresMano2.add(carta.getValorNumerico());
        }

        Collections.sort(valoresMano1, Collections.reverseOrder());
        Collections.sort(valoresMano2, Collections.reverseOrder());

        for (int i = 0; i < valoresMano1.size(); i++) {
            if (valoresMano1.get(i) > valoresMano2.get(i)) {
                return 1; // Mano 1 es mejor
            } else if (valoresMano1.get(i) < valoresMano2.get(i)) {
                return -1; // Mano 2 es mejor
            }
        }

        return 0; // Son iguales
    }

    public String getFaseComoTexto() {
        switch (faseActual) {
            case APUESTA_1:
                return "Ronda de Apuestas (Primera)";
            case DESCARTE:
                return "Ronda de Descarte";
            case APUESTA_2:
                return "Ronda de Apuestas (Segunda)";
            case ENFRENTAMIENTO:
                return "Ronda de Enfrentamiento";
            default:
                return "";
        }
    }

    public String obtenerMejorJugada() {
        Jugador jugador = turno.obtenerJugadorActual();

        ArrayList<Carta> mano = jugador.getMano();

        if (esEscaleraReal(mano)) {
            return "Escalera Real";
        }
        if (esEscaleraDeColor(mano)) {
            return "Escalera de Color";
        }
        if (esPoker(mano)) {
            return "Poker";
        }
        if (esFull(mano)) {
            return "Full";
        }
        if (esColor(mano)) {
            return "Color";
        }
        if (esEscalera(mano)) {
            return "Escalera";
        }
        if (esTrio(mano)) {
            return "Trío";
        }
        if (esDoblePar(mano)) {
            return "Doble Par";
        }
        if (esPar(mano)) {
            return "Par";
        }
        return "Carta Alta";
    }

    public ArrayList<Carta> obtenerCartasDeMejorJugada() {

        Jugador jugador = turno.obtenerJugadorActual();

        ArrayList<Carta> mano = jugador.getMano();

        if (esEscaleraReal(mano)) {
            return new ArrayList<>(mano);
        }
        if (esEscaleraDeColor(mano)) {
            return new ArrayList<>(mano);
        }
        if (esPoker(mano)) {
            return obtenerCartasPorCantidad(mano, 4);
        }
        if (esFull(mano)) {
            return obtenerCartasFull(mano);
        }
        if (esColor(mano)) {
            return new ArrayList<>(mano);
        }
        if (esEscalera(mano)) {
            return new ArrayList<>(mano);
        }
        if (esTrio(mano)) {
            return obtenerCartasPorCantidad(mano, 3);
        }
        if (esDoblePar(mano)) {
            return obtenerCartasDoblePar(mano);
        }
        if (esPar(mano)) {
            return obtenerCartasPorCantidad(mano, 2);
        }

        // Carta alta
        Carta mayor = Collections.max(mano, Comparator.comparingInt(Carta::getValorNumerico));
        ArrayList<Carta> resultado = new ArrayList<>();
        resultado.add(mayor);
        return resultado;
    }

    private ArrayList<Carta> obtenerCartasPorCantidad(ArrayList<Carta> mano, int cantidad) {
        HashMap<String, List<Carta>> grupos = new HashMap<>(mano.stream()
                .collect(Collectors.groupingBy(Carta::getValor)));
        for (List<Carta> grupo : grupos.values()) {
            if (grupo.size() == cantidad) {
                return new ArrayList<>(grupo);
            }
        }
        return new ArrayList<>();
    }

    private ArrayList<Carta> obtenerCartasFull(ArrayList<Carta> mano) {
        ArrayList<Carta> resultado = new ArrayList<>();
        HashMap<String, List<Carta>> grupos = new HashMap<>(mano.stream()
                .collect(Collectors.groupingBy(Carta::getValor)));
        for (List<Carta> grupo : grupos.values()) {
            if (grupo.size() == 3) {
                resultado.addAll(grupo);
            }
        }
        for (List<Carta> grupo : grupos.values()) {
            if (grupo.size() == 2) {
                resultado.addAll(grupo);
            }
        }
        return resultado;
    }

    private ArrayList<Carta> obtenerCartasDoblePar(ArrayList<Carta> mano) {
        ArrayList<Carta> resultado = new ArrayList<>();
        HashMap<String, List<Carta>> grupos = new HashMap<>(mano.stream()
                .collect(Collectors.groupingBy(Carta::getValor)));
        int paresEncontrados = 0;
        for (List<Carta> grupo : grupos.values()) {
            if (grupo.size() == 2 && paresEncontrados < 2) {
                resultado.addAll(grupo);
                paresEncontrados++;
            }
        }
        return resultado;
    }

}
