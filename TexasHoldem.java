// Importación de clases necesarias
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class TexasHoldem extends Poker {
    private int ciegaPequena;
    private int ciegaGrande;
    private int apuestaActual;

    // Lista de cartas comunitarias
    private ArrayList<Carta> cartasComunitarias = new ArrayList<>();

    // Flags para controlar en qué fase del juego estamos 
    //usado para permitir pasar 
    private boolean esFlop = false;
    private boolean esTurn = false;
    private boolean esRiver = false;

    public TexasHoldem() {
        super(new ArrayList<>());
        inicializarJugadores();
    }

    private void inicializarJugadores() {
        Scanner scanner = new Scanner(System.in);
        int numJugadores;

        // Validación para permitir entre 2 y 10 jugadores
        do {
            System.out.print("Ingrese el número de jugadores (2-10): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Debe ingresar un número válido.");
                scanner.next();
            }
            numJugadores = scanner.nextInt();

            if (numJugadores < 2 || numJugadores > 10) {
                System.out.println("Número fuera de rango. Debe ser entre 2 y 10.");
            }
        } while (numJugadores < 2 || numJugadores > 10);

        // Solicita el nombre de cada jugador y 
        // cada jugador se inicializa con 100 fichas
        for (int i = 1; i <= numJugadores; i++) {
            System.out.print("Ingrese el nombre del jugador " + i + ": ");
            String nombre = scanner.next();
            jugadores.add(new Jugador(nombre, 100));
        }

        System.out.println("Primera ronda de poker " + numJugadores + " jugadores.");
    }

    // Método que ejecuta todas las fases de una partida
    @Override
    public void iniciarJuego() {
        System.out.println("¡Comienza la partida de Texas Hold'em!");
        barajarMazo();               // Baraja el mazo
        repartirCartas();            // Da 2 cartas a cada jugador
        mostrarCartasJugadores();    // Muestra cartas en consola
        definirCiegas();             // Asigna ciegas pequeña y grande
        iniciarRondaApuestas();      // Preflop

        flop();                      // Muestra 3 cartas comunitarias + apuestas
        turn();                      // Muestra 1 carta más + apuestas
        river();                     // Muestra 1 carta final + apuestas

        showdown();                  // Determina ganador
    }

    // Da 2 cartas a cada jugador
    @Override
    protected void repartirCartas() {
        System.out.println("Repartiendo cartas...");
        for (Jugador jugador : jugadores) {
            jugador.recibirCarta(repartirCarta());
            jugador.recibirCarta(repartirCarta());
        }
    }

    // Muestra las cartas privadas de cada jugador
    private void mostrarCartasJugadores() {
        System.out.println("\nCartas de cada jugador:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + " tiene: " + jugador.getMano().get(0) + " y " + jugador.getMano().get(1));
        }
    }

    // Define las apuestas iniciales obligatorias
    private void definirCiegas() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n" + jugadores.get(0).getNombre() + ", ingrese el valor de la ciega pequeña: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Recuerda que la ciega grande es el doble de la pequeña.");
            scanner.next();
        }
        ciegaPequena = scanner.nextInt();
        ciegaGrande = ciegaPequena * 2;

        // El primer jugador pone la ciega pequeña
        jugadores.get(0).apostar(ciegaPequena);
        // El segundo jugador pone la ciega grande
        jugadores.get(1).apostar(ciegaGrande);

        apuestaActual = ciegaGrande;

        System.out.println(jugadores.get(0).getNombre() + " es la ciega pequeña y su apuesta es " + ciegaPequena + " fichas.");
        System.out.println(jugadores.get(1).getNombre() + " es la ciega grande y su apuesta es " + ciegaGrande + " fichas.");
        mostrarEstadoJugadores();
    }

    // Ejecuta una ronda de apuestas
    private void iniciarRondaApuestas() {
        Scanner scanner = new Scanner(System.in);
        boolean rondaCompleta = false;

        // Se repite hasta que todos los jugadores activos hayan igualado las apuestas
        while (!rondaCompleta) {
            rondaCompleta = true;

            // Comienza desde el tercer jugador
            // excluyendo las ciegas
            for (int i = 2; i < jugadores.size(); i++) {
                realizarAccionJugador(jugadores.get(i), scanner);
            }

            realizarAccionJugador(jugadores.get(0), scanner);
            realizarAccionJugador(jugadores.get(1), scanner);

            rondaCompleta = verificarApuestasIguales();
        }

        if (rondaCompleta) {
            int totalBote = calcularBote();
            System.out.println("\nEl bote total acumulado es de " + totalBote + " fichas.");
        }
    }

    // Permite a cada jugador realizar una acción en su turno
    private void realizarAccionJugador(Jugador jugador, Scanner scanner) {
        if (!jugador.estaActivo()) return;

        System.out.println("\n" + jugador.getNombre() + ", elige una opción:");
        if ((esFlop || esTurn || esRiver) && apuestaActual == 0) {
            System.out.println("[0] Pasar (Check)");
        }
        System.out.println("[1] Apostar (Bet)");
        System.out.println("[2] Igualar (Call)");
        System.out.println("[3] Subir (Raise)");
        System.out.println("[4] Retirarse (Fold)");

        int opcion;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Debe ingresar un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();

            if ((opcion < 0 || opcion > 4) || (opcion == 0 && (!(esFlop || esTurn || esRiver) || apuestaActual != 0))) {
                System.out.println("Vuelve a intentarlo.");
                opcion = -1;
            }
        } while (opcion == -1);
        
        switch (opcion) {
            case 0:
                System.out.println(jugador.getNombre() + " decidió pasar.");
                break;
            case 1:
                System.out.print("Cuánto vas a apostar?: ");
                int cantidad = scanner.nextInt();
                jugador.apostar(cantidad);
                apuestaActual = Math.max(apuestaActual, cantidad);
                System.out.println(jugador.getNombre() + " apostó " + cantidad + " fichas.");
                break;
            case 2:
                int yaApostado = 100 - jugador.getFichas();
                int diferencia = apuestaActual - yaApostado;
                if (diferencia > 0) {
                    jugador.apostar(diferencia);
                    System.out.println(jugador.getNombre() + " igualó con " + diferencia + " fichas.");
                } else {
                    System.out.println(jugador.getNombre() + " ya ha igualado.");
                }
                break;
            case 3:
                int nuevaApuesta;
                do {
                    System.out.print("Aumenta la apuesta: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Debe ingresar un número válido.");
                        scanner.next();
                    }
                    nuevaApuesta = scanner.nextInt();
                    if (nuevaApuesta <= apuestaActual) {
                        System.out.println("Debe ser mayor que la apuesta actual (" + apuestaActual + ").");
                    }
                } while (nuevaApuesta <= apuestaActual);
                int cantidadARestar = nuevaApuesta - (100 - jugador.getFichas());
                apuestaActual = nuevaApuesta;
                jugador.apostar(cantidadARestar);
                System.out.println(jugador.getNombre() + " subió a " + apuestaActual + " fichas.");
                break;
            case 4:
                jugador.retirarse();
                System.out.println(jugador.getNombre() + " se ha retirado.");
                break;
        }

        mostrarEstadoJugadores();

        // Verifica si solo queda un jugador activo
        if (contarJugadoresActivos() == 1) {
            Jugador ganador = obtenerUnicoJugadorActivo();
            System.out.println("\n¡" + ganador.getNombre() + " gana porque todos se han retirado!");
            System.exit(0);
        }
    }

    // Verifica si todos los jugadores activos han apostado lo mismo
    private boolean verificarApuestasIguales() {
        for (Jugador jugador : jugadores) {
            if (jugador.estaActivo()) {
                int cantidadApostada = 100 - jugador.getFichas();
                if (cantidadApostada != apuestaActual) {
                    return false;
                }
            }
        }
        return true;
    }

    // Suma todas las apuestas en el bote
    private int calcularBote() {
        int total = 0;
        for (Jugador jugador : jugadores) {
            total += (100 - jugador.getFichas());
        }
        return total;
    }

    // Muestra el estado de todos los jugadores
    private void mostrarEstadoJugadores() {
        System.out.println("\nEstado de los jugadores:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + " - Fichas: " + jugador.getFichas() + " | Apuesta: " + (100 - jugador.getFichas()));
        }
    }

    // Muestra las 3 primeras cartas comunitarias
    private void flop() {
        System.out.println("\n== FLOP ==");
        //Quema una carta o la retira
        //Las reglas de Texas es eliminar la primera carta antes de las comunitarias
        repartirCarta(); 
        cartasComunitarias.clear();
        for (int i = 0; i < 3; i++) {
            cartasComunitarias.add(repartirCarta());
        }
        mostrarCartasComunitarias();
        reiniciarApuestas();
        esFlop = true;
        iniciarRondaApuestas();
        esFlop = false;
    }

    // Muestra la cuarta carta comunitaria
    private void turn() {
        System.out.println("\n== TURN ==");
        repartirCarta(); // Quemar una carta
        cartasComunitarias.add(repartirCarta());
        mostrarCartasComunitarias();
        reiniciarApuestas();
        esTurn = true;
        iniciarRondaApuestas();
        esTurn = false;
    }

    // Muestra la quinta y última carta comunitaria
    private void river() {
        System.out.println("\n== RIVER ==");
        repartirCarta(); // Quemar una carta
        cartasComunitarias.add(repartirCarta());
        mostrarCartasComunitarias();
        reiniciarApuestas();
        esRiver = true;
        iniciarRondaApuestas();
        esRiver = false;
    }

    // Imprime las cartas comunitarias
    private void mostrarCartasComunitarias() {
        System.out.print("Cartas comunitarias: ");
        for (Carta carta : cartasComunitarias) {
            System.out.print(carta + " ");
        }
        System.out.println();
    }

private void showdown() {
    System.out.println("\n== ENFRENTAMIENTO ==");
    // Lista para almacenar el resultado de la mejor mano .
    List<EvaluadorMano.ResultadoMano> resultados = new ArrayList<>();

    // Se calcula el bote total 
    int boteTotal = calcularBote();
    System.out.println("El bote total es de " + boteTotal + " fichas.");

    // Se evalúa la mano de cada jugador 
    for (Jugador jugador : jugadores) {
        if (jugador.estaActivo()) {
            // Se combinan las cartas comunitarias con las cartas del jugador.
            List<Carta> todasCartas = new ArrayList<>(cartasComunitarias);
            todasCartas.addAll(jugador.getMano());
            // Se evalúa la mejor mano posible de 5 cartas entre las 7 
            EvaluadorMano.ResultadoMano resultado = EvaluadorMano.evaluarMejorMano(todasCartas, jugador);
            // Se añade el resultado a la lista para comparar más adelante.
            resultados.add(resultado);
            
            System.out.println(jugador.getNombre() + " muestra: " +
                jugador.getMano().get(0) + " y " + jugador.getMano().get(1) +
                " - Mano: " + resultado);
        }
    }

    // Se ordenan los resultados de las manos de mejor a peor usando compareTo().
    Collections.sort(resultados);
    // La mejor mano es la primera 
    EvaluadorMano.ResultadoMano mejorResultado = resultados.get(0);
    // Se obtiene el tipo de jugada del mejor resultado.
    EvaluadorMano.TipoMano tipoMejorMano = mejorResultado.getTipo();
    // Lista para almacenar a todos los jugadores que tienen la misma mejor mano 
    List<Jugador> ganadores = new ArrayList<>();
    // Se comparan todas las manos con la mejor
    for (EvaluadorMano.ResultadoMano resultado : resultados) {
        if (resultado.compareTo(mejorResultado) == 0) {
            ganadores.add(resultado.getJugador());
        }
    }
    // Calcul cuántos ganadores hay 
    // y cuánto le toca a cada uno
    int numGanadores = ganadores.size();
    int gananciaPorJugador = boteTotal / numGanadores;
    // Por si el bote no se divide de forma exacta
    int residuo = boteTotal % numGanadores; 

    // Cuando hay un ganador
    if (numGanadores == 1) {
        Jugador ganador = ganadores.get(0);
        // Se transfiere el bote completo al jugador.
        ganador.ganarBote(boteTotal);
        System.out.println("\n¡" + ganador.getNombre() + " gana el pozo de " +
            boteTotal + " fichas con " + tipoMejorMano + "!");
    } else {
        // En caso de empate entre varios jugadores:
        System.out.println("\nEmpate, el pozo se divide entre:");
        // Se recorre la lista de ganadores.
        for (int i = 0; i < ganadores.size(); i++) {
            Jugador ganador = ganadores.get(i);

            // Al rpimero, se le asigna el residuo si es que hay.
            int ganancia = gananciaPorJugador;
            if (i == 0) ganancia += residuo;
            // Se asignan las fichas  al ganador.
            ganador.ganarBote(ganancia);
            System.out.println("🏅 " + ganador.getNombre() +
                " (recibe " + ganancia + " fichas)");
        }
    }
}
    // Reinicia la apuesta actual
    private void reiniciarApuestas() {
        apuestaActual = 0;
    }

    // Cuenta los jugadores aún activos en la mano
    private int contarJugadoresActivos() {
        int activos = 0;
        for (Jugador jugador : jugadores) {
            if (jugador.estaActivo()) {
                activos++;
            }
        }
        return activos;
    }

    // Devuelve al único jugador activo si solo queda uno
    private Jugador obtenerUnicoJugadorActivo() {
        for (Jugador jugador : jugadores) {
            if (jugador.estaActivo()) {
                return jugador;
            }
        }
        return null;
    }
    @Override
    public int evaluarMano(ArrayList<Carta> mano) {
        return 0;
    }
}
