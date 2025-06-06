package VISTA.Comun;

import MODELO.Jugador;
import MODELO.ModoParticipacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Vista única para mostrar todos los jugadores registrados en “tarjetas” horizontales.
 * Puede usarse tanto desde opciones de Administrador como de Jugador.
 * Cada tarjeta muestra: nombre, si es frecuente, modo de participación,
 * cantidad de infracciones y calificación promedio.
 */
public class ListaJugadoresVista extends JFrame {

    private JPanel contentPanel;    // Panel principal con tarjetas
    private JScrollPane scrollPane; // Para desplazamiento vertical

    public ListaJugadoresVista() {
        super("Lista de Jugadores");
        initComponents();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        // Raíz con BorderLayout
        getContentPane().setLayout(new BorderLayout());

        // Encabezado
        JLabel header = new JLabel("Jugadores Registrados", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.setBorder(new EmptyBorder(10, 0, 10, 0));
        getContentPane().add(header, BorderLayout.NORTH);

        // Panel que contendrá las tarjetas (BoxLayout Y_AXIS)
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // ScrollPane para contentPanel
        scrollPane = new JScrollPane(
            contentPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Toma una lista de Jugador y crea “tarjetas” horizontales para cada uno.
     * @param jugadores lista de Jugador obtenidos desde la base de datos
     */
    public void mostrarJugadores(List<Jugador> jugadores) {
        contentPanel.removeAll();

        for (Jugador j : jugadores) {
            JPanel card = crearTarjeta(j);
            contentPanel.add(card);
            contentPanel.add(Box.createVerticalStrut(10)); // espacio entre tarjetas
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Construye una tarjeta horizontal con:
     *   • Nombre
     *   • Frecuente (Sí/No)
     *   • Modo de Participación
     *   • Número de infracciones
     *   • Calificación Promedio
     *
     * @param j Jugador a representar
     * @return JPanel formateado como tarjeta
     */
    private JPanel crearTarjeta(Jugador j) {
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

        // Frecuente
        String frecuenteText = j.isFrecuente() ? "Sí" : "No";
        JLabel frecuenteLabel = new JLabel("Frecuente: " + frecuenteText);
        frecuenteLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        frecuenteLabel.setPreferredSize(new Dimension(120, 30));
        tarjeta.add(frecuenteLabel);
        tarjeta.add(Box.createHorizontalStrut(20));

        // Modo de Participación
        JLabel modoLabel = new JLabel("Modo: " + j.getModo().name());
        modoLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        modoLabel.setPreferredSize(new Dimension(150, 30));
        tarjeta.add(modoLabel);
        tarjeta.add(Box.createHorizontalStrut(20));

        // Infracciones
        JLabel infraLabel = new JLabel("Infracciones: " + j.getInfracciones());
        infraLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infraLabel.setPreferredSize(new Dimension(130, 30));
        tarjeta.add(infraLabel);
        tarjeta.add(Box.createHorizontalStrut(20));

        // Calificación Promedio
        JLabel promedioLabel = new JLabel(String.format("Promedio: %.2f", j.getCalificacionPromedio()));
        promedioLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        promedioLabel.setPreferredSize(new Dimension(130, 30));
        tarjeta.add(promedioLabel);

        tarjeta.add(Box.createHorizontalGlue()); // empujar contenido a la izquierda

        return tarjeta;
    }

    /**
     * Ejemplo de main para probar la vista con datos de ejemplo.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear algunos jugadores de ejemplo
            Jugador j1 = new Jugador();
            j1.setNombre("Carlos Pérez");
            j1.setFrecuente(true);
            j1.setModoParticipacion(ModoParticipacion.ESTANDAR);
            j1.setInfracciones(1);
            j1.setCalificacionPromedio(7.5f);

            Jugador j2 = new Jugador();
            j2.setNombre("María Gómez");
            j2.setFrecuente(false);
            j2.setModoParticipacion(ModoParticipacion.SOLIDARIO);
            j2.setInfracciones(0);
            j2.setCalificacionPromedio(8.2f);

            ListaJugadoresVista vista = new ListaJugadoresVista();
            vista.setVisible(true);
            vista.mostrarJugadores(List.of(j1, j2));
        });
    }
}
