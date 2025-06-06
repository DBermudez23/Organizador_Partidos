package VISTA.VistaOpcionesJugador;

import javax.swing.*;
import java.awt.*;

/**
 * Vista para mostrar la información del último partido jugado:
 *  - Fecha del partido
 *  - Resultado
 *  - Lista de calificaciones de los jugadores
 *  - Lista de jugadores penalizados
 *  - Botón “Volver” para regresar al menú de jugador
 */
public class UltimoPartidoVista extends JFrame {

    // Componentes públicos para que el controlador los use directamente
    public JButton volverButton;
    public JLabel fechaValueLabel;
    public JLabel resultadoValueLabel;
    public JList<String> calificacionesList;
    public JList<String> penalizadosList;

    public UltimoPartidoVista() {
        super("Último Partido");

        initComponents();
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        getContentPane().add(mainPanel);

        // ======== Panel superior: fecha y resultado ========
        JPanel headerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        headerPanel.setBackground(Color.WHITE);

        JLabel fechaLabel = new JLabel("Fecha:");
        fechaLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        headerPanel.add(fechaLabel);

        fechaValueLabel = new JLabel("");
        fechaValueLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        headerPanel.add(fechaValueLabel);

        JLabel resultadoLabel = new JLabel("Resultado:");
        resultadoLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        headerPanel.add(resultadoLabel);

        resultadoValueLabel = new JLabel("");
        resultadoValueLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        headerPanel.add(resultadoValueLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ======== Panel central: calificaciones y penalizados ========
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBackground(Color.WHITE);

        // Calificaciones
        JPanel califPanel = new JPanel();
        califPanel.setLayout(new BorderLayout(5, 5));
        califPanel.setBackground(Color.WHITE);
        califPanel.setBorder(BorderFactory.createTitledBorder("Calificaciones"));

        calificacionesList = new JList<>(new DefaultListModel<>());
        calificacionesList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane califScroll = new JScrollPane(calificacionesList);
        califPanel.add(califScroll, BorderLayout.CENTER);

        centerPanel.add(califPanel);

        // Penalizados
        JPanel penPanel = new JPanel();
        penPanel.setLayout(new BorderLayout(5, 5));
        penPanel.setBackground(Color.WHITE);
        penPanel.setBorder(BorderFactory.createTitledBorder("Jugadores Penalizados"));

        penalizadosList = new JList<>(new DefaultListModel<>());
        penalizadosList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane penScroll = new JScrollPane(penalizadosList);
        penPanel.add(penScroll, BorderLayout.CENTER);

        centerPanel.add(penPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // ======== Panel inferior: botón Volver ========
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(Color.WHITE);

        volverButton = new JButton("Volver");
        volverButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        volverButton.setBackground(new Color(0, 102, 51));
        volverButton.setForeground(Color.WHITE);
        volverButton.setPreferredSize(new Dimension(120, 36));
        footerPanel.add(volverButton);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Prueba de la ventana en modo standalone.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UltimoPartidoVista frame = new UltimoPartidoVista();
            frame.setVisible(true);
        });
    }
}
