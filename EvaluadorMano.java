import java.util.*; 

public class EvaluadorMano {

    // Enumeración que define los diferentes tipos de manos posibles en el póker.
    // Valores de estas mano proporcionados del archivo que se compartio en clase
    public enum TipoMano {
        CARTA_ALTA, PAR, DOBLE_PAR, TRIO, ESCALERA, COLOR,
        FULL_HOUSE, POQUER, ESCALERA_COLOR, ESCALERA_REAL
    }

    //Clase para comparar los resultados de las manos, usa Comparable
    public static class ResultadoMano implements Comparable<ResultadoMano> {
        private TipoMano tipo; 
        private List<Integer> valores; 
        private Jugador jugador;

        // Constructor para crear una instancia de ResultadoMano con su tipo, valores y jugador
        public ResultadoMano(TipoMano tipo, List<Integer> valores, Jugador jugador) {
            this.tipo = tipo;
            this.valores = valores;
            this.jugador = jugador;
        }

        public TipoMano getTipo() {
            return tipo;
        }

        public Jugador getJugador() {
            return jugador;
        }

        /*
         * compara los resultados de las manos, devuelve un negativo cuando es la mano perderora
         * positivo para ganar y 0  empate
         */
        @Override
        public int compareTo(ResultadoMano otra) {
            int comp = Integer.compare(otra.tipo.ordinal(), this.tipo.ordinal()); 
            if (comp != 0) 
            return comp; // Si el tipo es diferente, retorna resultado
            for (int i = 0; i < Math.min(valores.size(), otra.valores.size()); i++) {
                comp = Integer.compare(otra.valores.get(i), this.valores.get(i)); 
                if (comp != 0) 
                return comp;
            }
            return 0; 
        }

        @Override
        public String toString() {
            return tipo + " - " + valores + " - " + jugador.getNombre();
        }
    }
    //Evalua las manos 
    public static ResultadoMano evaluarMejorMano(List<Carta> cartas, Jugador jugador) {
        List<List<Carta>> combinaciones = obtenerCombinaciones(cartas, 5); // Obtiene todas las combinaciones posibles de 5 cartas
        ResultadoMano mejor = null; // Almacena la mejor mano encontrada
        for (List<Carta> mano : combinaciones) { // Itera sobre cada combinación de 5 cartas
            ResultadoMano actual = evaluarCincoCartas(mano, jugador); // Evalúa la combinación actual
            if (mejor == null || actual.compareTo(mejor) > 0) { // Si la mano actual es mejor, la guarda
                mejor = actual;
            }
        }
        return mejor; // Devuelve la mejor mano encontrada
    }

    // Genera todas las combinaciones posibles de k cartas a partir de una lista dada
    private static List<List<Carta>> obtenerCombinaciones(List<Carta> cartas, int k) {
        // Lista donde se guardan todas las combinaciones
        List<List<Carta>> resultado = new ArrayList<>(); 
        // Llama recursiva para generar combinaciones
        combinar(cartas, new ArrayList<>(), 0, k, resultado); 
        return resultado;
    }

    // Método recursivo para generar combinaciones
    //la variable "k" es el numero de cartas a combinar
    private static void combinar(List<Carta> cartas, List<Carta> actual, int inicio, int k, List<List<Carta>> resultado) {
        if (k == 0) { 
            resultado.add(new ArrayList<>(actual)); // Agrega la combinación actual al resultado
            return;
        }
        // Itera desde el índice de inicio hasta donde quede suficiente espacio
        for (int i = inicio; i <= cartas.size() - k; i++) { 
            actual.add(cartas.get(i)); // Agrega una carta a la combinación actual
            combinar(cartas, actual, i + 1, k - 1, resultado); 
            actual.remove(actual.size() - 1); 
        }
    }

    // Evalúa una mano específica de 5 cartas y determina su tipo
    private static ResultadoMano evaluarCincoCartas(List<Carta> mano, Jugador jugador) {
        List<Integer> valores = new ArrayList<>(); // Lista para valores de las cartas
        Map<Integer, Integer> conteo = new HashMap<>(); // Mapa para contar cuántas veces aparece cada valor
        Map<String, List<Carta>> palos = new HashMap<>(); // Mapa que agrupa cartas por palo
        // Orden descendente de valores para jerarquía
        List<Integer> orden = Arrays.asList(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2); 

        for (Carta c : mano) { // Recorre cada carta en la mano
            int valor = c.getValorNumerico(); 
            conteo.put(valor, conteo.getOrDefault(valor, 0) + 1);
            palos.computeIfAbsent(c.getPalo(), k -> new ArrayList<>()).add(c); 
        }
        // Lista de valores únicos presentes
        List<Integer> valoresOrdenados = new ArrayList<>(conteo.keySet()); 
        valoresOrdenados.sort((a, b) -> b - a); 

        boolean esColor = false; // Indica si hay color
        boolean esEscalera = false; // Indica si hay escalera
        List<Integer> escaleraValores = new ArrayList<>(); // Valores de la escalera
        // Para iterar por cada grupo de cartas del mismo palo
        for (List<Carta> grupo : palos.values()) { 
            if (grupo.size() >= 5) { // Si hay al menos 5 cartas del mismo palo, esColor es true
                esColor = true;
                grupo.sort((a, b) -> b.getValorNumerico() - a.getValorNumerico()); // Ordena las cartas del grupo
                List<Integer> colorValores = new ArrayList<>();
                for (Carta c : grupo) colorValores.add(c.getValorNumerico()); // Obtiene valores de las cartas del color
                escaleraValores = detectarEscalera(colorValores); 
                
                if (!escaleraValores.isEmpty()) { // Si hay escalera en el color
                    if (escaleraValores.get(0) == 14 && escaleraValores.get(4) == 10) {
                        // Si empieza en 14 (As) y termina en 10 -> escalera real
                        return new ResultadoMano(TipoMano.ESCALERA_REAL, escaleraValores, jugador); 
                    }
                    return new ResultadoMano(TipoMano.ESCALERA_COLOR, escaleraValores, jugador); 
                }
                return new ResultadoMano(TipoMano.COLOR, colorValores.subList(0, 5), jugador); 
            }
        }

        List<Integer> todosValores = new ArrayList<>();
        // Recorre el orden de valores posibles
        for (Integer v : orden) { 
            if (conteo.containsKey(v)) {
                for (int i = 0; i < conteo.get(v); i++) todosValores.add(v); 
            }
        }

        escaleraValores = detectarEscalera(todosValores); 
        if (!escaleraValores.isEmpty()) {
            return new ResultadoMano(TipoMano.ESCALERA, escaleraValores, jugador);
        }

        // Separa los valores por cantidad de repeticiones
        List<Integer> cuartas = new ArrayList<>();
        List<Integer> trios = new ArrayList<>();
        List<Integer> pares = new ArrayList<>();

        for (int v : valoresOrdenados) {
            int c = conteo.get(v); // Número de veces que aparece el valor
            if (c == 4) cuartas.add(v); // Poker
            else if (c == 3) trios.add(v); // Trío
            else if (c == 2) pares.add(v); // Par
        }

        if (!cuartas.isEmpty()) {
            int kicker = obtenerKicker(valoresOrdenados, cuartas); // Obtiene carta adicional
            return new ResultadoMano(TipoMano.POQUER, Arrays.asList(cuartas.get(0), kicker), jugador);
        }

        if (!trios.isEmpty() && !pares.isEmpty()) {
            return new ResultadoMano(TipoMano.FULL_HOUSE, Arrays.asList(trios.get(0), pares.get(0)), jugador);
        }

        if (trios.size() >= 2) {
            return new ResultadoMano(TipoMano.FULL_HOUSE, Arrays.asList(trios.get(0), trios.get(1)), jugador);
        }

        if (!trios.isEmpty()) {
            List<Integer> kicker = obtenerKickers(valoresOrdenados, trios, 2);
            List<Integer> resultado = new ArrayList<>();
            resultado.add(trios.get(0));
            resultado.addAll(kicker);
            return new ResultadoMano(TipoMano.TRIO, resultado, jugador);
        }

        if (pares.size() >= 2) {
            int par1 = pares.get(0);
            int par2 = pares.get(1);
            int kicker = obtenerKicker(valoresOrdenados, Arrays.asList(par1, par2));
            return new ResultadoMano(TipoMano.DOBLE_PAR, Arrays.asList(par1, par2, kicker), jugador);
        }

        if (!pares.isEmpty()) {
            int par = pares.get(0);
            List<Integer> kickers = obtenerKickers(valoresOrdenados, Arrays.asList(par), 3);
            List<Integer> resultado = new ArrayList<>();
            resultado.add(par);
            resultado.addAll(kickers);
            return new ResultadoMano(TipoMano.PAR, resultado, jugador);
        }

        // Si no hay nada más, se devuelve la carta más alta
        return new ResultadoMano(TipoMano.CARTA_ALTA, todosValores.subList(0, 5), jugador);
    }

    // Detecta si una lista de valores contiene una escalera de 5 consecutivos
    private static List<Integer> detectarEscalera(List<Integer> valores) {
        Set<Integer> set = new HashSet<>(valores); // Elimina duplicados
        List<Integer> lista = new ArrayList<>(set); // Crea lista ordenada
        if (lista.contains(14)) lista.add(1); 
        // Paraordenar descendente
        Collections.sort(lista, Collections.reverseOrder()); 

        for (int i = 0; i <= lista.size() - 5; i++) { // Recorre inicios de escalera
            boolean esEscalera = true;
            for (int j = 1; j < 5; j++) { // Comprueba 5 valores consecutivos descendentes
                if (lista.get(i + j) != lista.get(i) - j) {
                    esEscalera = false;
                    break;
                }
            }
            if (esEscalera) {
                return lista.subList(i, i + 5); // Devuelve la escalera encontrada
            }
        }
        return new ArrayList<>(); // Cuando no se encontró escalera
    }
    // Kicker una carta que no forma parte principal de la mano, pero que se usa para desempatar 
    // Devuelve el kicker más alto que no esté en la lista de usados
    private static int obtenerKicker(List<Integer> valores, List<Integer> usados) {
        for (int v : valores) {
            if (!usados.contains(v)) return v;
        }
        return 0;
    }

    // Devuelve múltiples kickers, ignorando los valores ya usados
    private static List<Integer> obtenerKickers(List<Integer> valores, List<Integer> usados, int cantidad) {
        List<Integer> resultado = new ArrayList<>();
        for (int v : valores) {
            if (!usados.contains(v)) {
                resultado.add(v);
                if (resultado.size() == cantidad) break;
            }
        }
        return resultado;
    }
}
