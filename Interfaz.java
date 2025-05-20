package comdiegocano.proyectofinalpoo;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Interfaz extends javax.swing.JFrame {

    private boolean fiveCardDraw;
    private boolean texas;
    private FiveCardDraw juegoFiveCardDraw;
    private TexasHoldem juegoTexas;
    private final String rutaImagenes = "C:\\Users\\diego\\Documents\\NetBeansProjects\\"
        + "ProyectoFinalPOO\\src\\main\\java\\comdiegocano\\proyectofinalpoo\\imagenes\\";
    //aqui cambia a tu ruta donde estan las imagenes
    
    
    
    public Interfaz() {
        initComponents();
        //getContentPane().setLayout(null);
        estadoJuego.setFont(new Font("Arial", Font.BOLD, 15));
        setTitle("POKER");
        manoJugador1.setEditable(false);
        manoJugador2.setEditable(false);
        manoJugador3.setEditable(false);
        manoJugador4.setEditable(false);
        mesaDeJuego.setEditable(false);
        apuestas.setEditable(false);
        mejorMano.setEditable(false);
        estadoJuego.setVisible(false);
        jugador1.setVisible(false);
        jugador2.setVisible(false);
        jugador3.setVisible(false);
        jugador4.setVisible(false);
        mejorManoLabel.setVisible(false);

        //rondaActual.setEditable(false);
        //turnoActual.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iniciarJuego = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        manoJugador2 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        manoJugador1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        manoJugador3 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        manoJugador4 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        mesaDeJuego = new javax.swing.JTextPane();
        tomarDecision = new javax.swing.JButton();
        estadoJuego = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        mejorMano = new javax.swing.JTextPane();
        mejorManoLabel = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        apuestas = new javax.swing.JTextPane();
        jugador1 = new javax.swing.JLabel();
        jugador2 = new javax.swing.JLabel();
        jugador3 = new javax.swing.JLabel();
        jugador4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        iniciarJuego.setText("Iniciar Juego");
        iniciarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarJuegoActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(manoJugador2);

        jScrollPane2.setViewportView(manoJugador1);

        jScrollPane3.setViewportView(manoJugador3);

        jScrollPane4.setViewportView(manoJugador4);

        jScrollPane5.setViewportView(mesaDeJuego);

        tomarDecision.setText("Decidir");
        tomarDecision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomarDecisionActionPerformed(evt);
            }
        });

        estadoJuego.setText("jLabel1");

        jScrollPane6.setViewportView(mejorMano);

        mejorManoLabel.setText("jLabel1");

        jScrollPane7.setViewportView(apuestas);

        jugador1.setText("jLabel1");

        jugador2.setText("jLabel1");

        jugador3.setText("jLabel1");

        jugador4.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jugador3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tomarDecision, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iniciarJuego))
                        .addGap(160, 160, 160))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jugador1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jugador4)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jugador2))
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(estadoJuego)
                        .addGap(198, 198, 198)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mejorManoLabel)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jugador2)
                    .addComponent(jugador1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mejorManoLabel)
                    .addComponent(estadoJuego))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tomarDecision)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jugador4)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(iniciarJuego)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jugador3)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarJuegoActionPerformed
        ArrayList<Jugador> jugadores = new ArrayList<>();
        String[] opciones = {"5 Card Draw", "Texas HoldEm"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona el tipo de juego de póker:",
                "Seleccionar juego",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == null) {
            return;
        }

        if (seleccion.equals("5 Card Draw")) {
            int cantidadJugadores = 0;
            boolean valido = false;
            fiveCardDraw = true;
            texas = false;
            while (!valido) {
                String input = JOptionPane.showInputDialog(this, "¿Cuántos jugadores? (2 a 4)");
                if (input == null) {
                    return; // Cancelado
                }
                try {
                    cantidadJugadores = Integer.parseInt(input);
                    if (cantidadJugadores >= 2 && cantidadJugadores <= 4) {
                        valido = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Debes ingresar un número entre 2 y 4.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Debes ingresar un número.");
                }
            }

            manoJugador1.setVisible(cantidadJugadores >= 1);
            manoJugador2.setVisible(cantidadJugadores >= 2);
            manoJugador3.setVisible(cantidadJugadores >= 3);
            manoJugador4.setVisible(cantidadJugadores == 4);

            Iterator<Integer> iterador = java.util.stream.IntStream.rangeClosed(1, cantidadJugadores).iterator();

            while (iterador.hasNext()) {
                int i = iterador.next();
                jugadores.add(new Jugador("Jugador " + i, 200));
            }

            juegoFiveCardDraw = new FiveCardDraw(jugadores);
            juegoFiveCardDraw.iniciarJuego();
            juegoFiveCardDraw.setInterfaz(this);
            estadoJuego.setVisible(true);
            jugador1.setVisible(true);
            jugador2.setVisible(true);
            jugador3.setVisible(true);
            jugador4.setVisible(true);
            mejorManoLabel.setVisible(true);

            actualizarManos(jugadores);
            actualizarInterfaz();
        } else {
            int cantidadJugadores = 0;
            boolean valido = false;
            fiveCardDraw = false;
            texas = true;
            while (!valido) {
                String input = JOptionPane.showInputDialog(this, "¿Cuántos jugadores? (2 a 4)");
                if (input == null) {
                    return; // Cancelado
                }
                try {
                    cantidadJugadores = Integer.parseInt(input);
                    if (cantidadJugadores >= 2 && cantidadJugadores <= 4) {
                        valido = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Debes ingresar un número entre 2 y 4.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Debes ingresar un número.");
                }
            }

            manoJugador1.setVisible(cantidadJugadores >= 1);
            manoJugador2.setVisible(cantidadJugadores >= 2);
            manoJugador3.setVisible(cantidadJugadores >= 3);
            manoJugador4.setVisible(cantidadJugadores == 4);

            Iterator<Integer> iterador = java.util.stream.IntStream.rangeClosed(1, cantidadJugadores).iterator();

            while (iterador.hasNext()) {
                int i = iterador.next();
                jugadores.add(new Jugador("Jugador " + i, 1000));
            }
            juegoTexas = new TexasHoldem(jugadores);
            juegoTexas.setInterfaz(this);
            juegoTexas.iniciarJuego();

            estadoJuego.setVisible(true);
            jugador1.setVisible(true);
            jugador2.setVisible(true);
            jugador3.setVisible(true);
            jugador4.setVisible(true);
            mejorManoLabel.setVisible(true);

            actualizarManos(jugadores);
            actualizarInterfaz();
        }


    }//GEN-LAST:event_iniciarJuegoActionPerformed
    public void actualizarInterfaz() {
        if (fiveCardDraw) {
            estadoJuego.setText(
                    juegoFiveCardDraw.getFaseComoTexto() + " - "
                    + juegoFiveCardDraw.getTurnoActualComoTexto());
            actualizarManos(juegoFiveCardDraw.getJugadores());
            mostrarMejorMano();
            mostrarCartasMesa();
            apuestas.setText(juegoFiveCardDraw.toStringApuestas());
            ArrayList<Jugador> jugadores = juegoFiveCardDraw.getJugadores();

            if (jugadores.size() > 0) {
                jugador1.setText(jugadores.get(0).getNombre() 
                        + " - Fichas: " + jugadores.get(0).getFichas());
            }
            if (jugadores.size() > 1) {
                jugador2.setText(jugadores.get(1).getNombre() 
                        + " - Fichas: " + jugadores.get(1).getFichas());
            }
            if (jugadores.size() > 2) {
                jugador3.setText(jugadores.get(2).getNombre() 
                        + " - Fichas: " + jugadores.get(2).getFichas());
            }
            if (jugadores.size() > 3) {
                jugador4.setText(jugadores.get(3).getNombre() 
                        + " - Fichas: " + jugadores.get(3).getFichas());
            }

        } else {
            estadoJuego.setText(
                    juegoTexas.getFaseComoTexto() + " - "
                    + juegoTexas.getTurnoActualComoTexto());
            actualizarManos(juegoTexas.getJugadores());
            mostrarMejorMano();
            mostrarCartasMesa();
            apuestas.setText(juegoTexas.toStringApuestas());
            ArrayList<Jugador> jugadores = juegoTexas.getJugadores();

            estadoJuego.setBounds(
                    estadoJuego.getX() - 50,
                    estadoJuego.getY(),
                    estadoJuego.getWidth(),
                    estadoJuego.getHeight()
            );

            if (jugadores.size() > 0) {
                jugador1.setText(jugadores.get(0).getNombre() 
                        + " - Fichas: " + jugadores.get(0).getFichas());
            }
            if (jugadores.size() > 1) {
                jugador2.setText(jugadores.get(1).getNombre() 
                        + " - Fichas: " + jugadores.get(1).getFichas());
            }
            if (jugadores.size() > 2) {
                jugador3.setText(jugadores.get(2).getNombre() 
                        + " - Fichas: " + jugadores.get(2).getFichas());
            }
            if (jugadores.size() > 3) {
                jugador4.setText(jugadores.get(3).getNombre() 
                        + " - Fichas: " + jugadores.get(3).getFichas());
            }
        }

    }
    
    
    //boton para ejecutar la accion de la fase actual en cada juego
    private void tomarDecisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomarDecisionActionPerformed
        if (fiveCardDraw) {
            juegoFiveCardDraw.ejecutarFaseActual(this);
        } else {
            juegoTexas.ejecutarFaseActual(this);
        }
    }//GEN-LAST:event_tomarDecisionActionPerformed
    
    
    //muestra una ventana con un mensaje
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    //para obtener el nombre de la carta para su respectiva imagen
    public String getNombreImagen(Carta carta) {
        String valor = carta.getValor();
        String palo = carta.getPalo().toLowerCase();
        return valor + palo + ".png";
    }
    
    //pone las imagenes de las manos de los jugadores en TextPanes
    public void mostrarMano(Jugador jugador, JTextPane textPane) {
       
            textPane.setText(""); // Limpiar el JTextPane

            for (Carta carta : jugador.getMano()) {
                String ruta = rutaImagenes + getNombreImagen(carta);
                ImageIcon iconOriginal = new ImageIcon(ruta);

                // Escalar imagen
                Image imagenEscalada = iconOriginal.getImage().getScaledInstance(
                        -1, // ancho automatico 
                        120, // alttura de la imgaen
                        Image.SCALE_SMOOTH
                );

                ImageIcon iconEscalado = new ImageIcon(imagenEscalada);
                textPane.insertIcon(iconEscalado);

            
            }
    }
    
    //muestra las cartas que se ponen en el tablero
    public void mostrarCartasMesa() {
        if (fiveCardDraw) {
            mesaDeJuego.setText(""); // Limpiar el panel

            for (Carta carta : juegoFiveCardDraw.getTablero().getCartasTablero()) {
                String ruta = rutaImagenes + getNombreImagen(carta);

                ImageIcon iconOriginal = new ImageIcon(ruta);
                Image imagenEscalada = iconOriginal.getImage().getScaledInstance(
                        -1, 120, Image.SCALE_SMOOTH);

                ImageIcon iconEscalado = new ImageIcon(imagenEscalada);
                mesaDeJuego.insertIcon(iconEscalado);
            }
        } else {
            mesaDeJuego.setText(""); // Limpiar el panel

            for (Carta carta : juegoTexas.getTablero().getCartasTablero()) {
                String ruta = rutaImagenes + getNombreImagen(carta);

                ImageIcon iconOriginal = new ImageIcon(ruta);
                Image imagenEscalada = iconOriginal.getImage().getScaledInstance(
                        -1, 120, Image.SCALE_SMOOTH);

                ImageIcon iconEscalado = new ImageIcon(imagenEscalada);
                mesaDeJuego.insertIcon(iconEscalado);
            }
        }
    }
    
    
    //muestra la mejor mano que tiene el jugador
    public void mostrarMejorMano() {
        if (fiveCardDraw) {
            mejorMano.setText("");
            // Limpiar el textpane
            String mejorJugada = juegoFiveCardDraw.obtenerMejorJugada();
            mejorManoLabel.setText("Mejor mano: " + mejorJugada);
            mejorManoLabel.setFont(new Font("Arial", Font.BOLD, 14));

            ArrayList<Carta> mejoresCartas = juegoFiveCardDraw.obtenerCartasDeMejorJugada();
            for (Carta carta : mejoresCartas) {
                String ruta = rutaImagenes + getNombreImagen(carta);

                ImageIcon iconOriginal = new ImageIcon(ruta);
                Image imagenEscalada = iconOriginal.getImage().getScaledInstance(-1, 80, Image.SCALE_SMOOTH);
                ImageIcon iconEscalado = new ImageIcon(imagenEscalada);
                mejorMano.insertIcon(iconEscalado);
            }

        } else {

            mejorMano.setText("");
            mejorManoLabel.setFont(new Font("Arial", Font.BOLD, 14));

// Obtener resultado completo de la mejor mano
            EvaluadorMano.ResultadoMano resultado = juegoTexas.obtenerResultadoMejorManoJugadorActual();

            if (resultado != null) {
                mejorManoLabel.setText("Mejor mano: " + resultado.toString());

//                for (Carta carta : resultado.getCartas()) {
//                    String ruta = "C:\\Users\\diego\\Documents\\NetBeansProjects\\"
//                            + "ProyectoFinalPOO\\src\\main\\java\\comdiegocano\\proyectofinalpoo\\imagenes\\"
//                            + getNombreImagen(carta);
//
//                    ImageIcon iconOriginal = new ImageIcon(ruta);
//                    Image imagenEscalada = iconOriginal.getImage().getScaledInstance(-1, 80, Image.SCALE_SMOOTH);
//                    ImageIcon iconEscalado = new ImageIcon(imagenEscalada);
//                    mejorMano.insertIcon(iconEscalado);
                mejorMano.setVisible(false);
//                }
            } else {
                mejorManoLabel.setText("Mejor mano: -");
            }

        }
    }
    
    
    //actualiza los paneles de las manos 
    public void actualizarManos(ArrayList<Jugador> jugadores) {
        JTextPane[] paneles = {manoJugador1, manoJugador2, manoJugador3, manoJugador4};

        for (int i = 0; i < jugadores.size(); i++) {
            paneles[i].removeAll();
            mostrarMano(jugadores.get(i), paneles[i]);
            paneles[i].revalidate();
            paneles[i].repaint();
        }
    }
    
    //muestra un dialogo para que el  jugador pueda seleccionar que cartas
    //desea descartar
    public ArrayList<Integer> mostrarDialogoDescarte(ArrayList<Carta> mano) {
        JCheckBox[] checkboxes = new JCheckBox[mano.size()];
        JPanel panel = new JPanel(new GridLayout(0, 1));

        for (int i = 0; i < mano.size(); i++) {
            checkboxes[i] = new JCheckBox(mano.get(i).toString());
            panel.add(checkboxes[i]);
        }

        int result = JOptionPane.showConfirmDialog(null, panel, "Selecciona cartas a descartar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        ArrayList<Integer> indices = new ArrayList<>();
        if (result == JOptionPane.OK_OPTION) {
            for (int i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].isSelected()) {
                    indices.add(i);
                }
            }
        }

        return indices;
    }
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane apuestas;
    private javax.swing.JLabel estadoJuego;
    private javax.swing.JButton iniciarJuego;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel jugador1;
    private javax.swing.JLabel jugador2;
    private javax.swing.JLabel jugador3;
    private javax.swing.JLabel jugador4;
    private javax.swing.JTextPane manoJugador1;
    private javax.swing.JTextPane manoJugador2;
    private javax.swing.JTextPane manoJugador3;
    private javax.swing.JTextPane manoJugador4;
    private javax.swing.JTextPane mejorMano;
    private javax.swing.JLabel mejorManoLabel;
    private javax.swing.JTextPane mesaDeJuego;
    private javax.swing.JButton tomarDecision;
    // End of variables declaration//GEN-END:variables
}
