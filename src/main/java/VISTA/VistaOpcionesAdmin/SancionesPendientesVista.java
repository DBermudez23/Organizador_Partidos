package VISTA.VistaOpcionesAdmin;

import MODELO.Jugador;
import MODELO.ListaJugadores;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Vista que muestra los jugadores con sanciones pendientes (infracciones >= 2).
 * Cada jugador sancionado se muestra como “tarjeta” horizontal con su nombre,
 * número de infracciones y calificación promedio.
 */
public class SancionesPendientesVista extends JFrame {

    private JPanel contentPanel;    // Panel principal con las tarjetas
    private JScrollPane scrollPane; // Para desplazamiento vertical

    public SancionesPendientesVista() {
        super("Jugadores con Sanciones Pendientes");
        initComponents();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        getContentPane().setLayout(new BorderLayout());

        // Encabezado
        JLabel header = new JLabel("Sanciones Pendientes", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.setBorder(new EmptyBorder(10, 0, 10, 0));
        getContentPane().add(header, BorderLayout.NORTH);

        // Panel para las tarjetas
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // ScrollPane que envuelve contentPanel
        scrollPane = new JScrollPane(
            contentPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Toma la lista de todos los jugadores, filtra aquellos con infracciones >= 2,
     * y crea una “tarjeta” horizontal para cada uno.
     * @param todosJugadores lista completa de Jugador
     */
    public void mostrarSancionados(List<Jugador> todosJugadores) {
        contentPanel.removeAll();

        for (Jugador j : todosJugadores) {
            if (j.getInfracciones() >= 2) {
                JPanel card = crearTarjetaSancionado(j);
                contentPanel.add(card);
                contentPanel.add(Box.createVerticalStrut(10));
            }
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Construye la “tarjeta” para un Jugador sancionado:
     *   • Nombre
     *   • Número de infracciones
     *   • Calificación promedio
     *
     * @param j Jugador sancionado
     * @return JPanel formateado como tarjeta
     */
    private JPanel crearTarjetaSancionado(Jugador j) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.X_AXIS));
        tarjeta.setBackground(new Color(245, 245, 245));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(8, 12, 8, 12)
        ));
        tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Nombre
        JLabel nombreLabel = new JLabel(j.getNombre());
        nombreLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nombreLabel.setPreferredSize(new Dimension(200, 30));
        tarjeta.add(nombreLabel);
        tarjeta.add(Box.createHorizontalStrut(20));

        // Infracciones
        JLabel infraLabel = new JLabel("Infracciones: " + j.getInfracciones());
        infraLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infraLabel.setPreferredSize(new Dimension(150, 30));
        tarjeta.add(infraLabel);
        tarjeta.add(Box.createHorizontalStrut(20));

        // Calificación Promedio
        JLabel promedioLabel = new JLabel(String.format("Promedio: %.2f", j.getCalificacionPromedio()));
        promedioLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        promedioLabel.setPreferredSize(new Dimension(130, 30));
        tarjeta.add(promedioLabel);

        tarjeta.add(Box.createHorizontalGlue());
        return tarjeta;
    }

    /**
     * Ejemplo de main para probar la vista con datos de la BD.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Supongamos que ListaJugadores tiene getJugadores() que retorna todos los jugadores
            ListaJugadores modelo = new ListaJugadores();
            List<Jugador> todos = modelo.getJugadores();

            SancionesPendientesVista vista = new SancionesPendientesVista();
            vista.mostrarSancionados(todos);
            vista.setVisible(true);
        });
    }
}
