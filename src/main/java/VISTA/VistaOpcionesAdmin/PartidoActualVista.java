package VISTA.VistaOpcionesAdmin;

import javax.swing.*;
import java.awt.*;

/**
 * Vista para mostrar el Partido Actual:
 *  - Lista de jugadores en Equipo Rojo.
 *  - Lista de jugadores en Equipo Azul.
 *  - Campos para ingresar el resultado de cada equipo.
 *  - Botón para confirmar el resultado.
 *  - Botón para regresar al Menú Administrador.
 */
public class PartidoActualVista extends JFrame {

    // Componentes públicos para que el controlador pueda asociar listeners y manipular datos
    public JList<String> listaRojo;       // Lista de nombres de jugadores Equipo Rojo
    public JList<String> listaAzul;       // Lista de nombres de jugadores Equipo Azul
    public JTextField rojoScoreField;     // Campo para goles Equipo Rojo
    public JTextField azulScoreField;     // Campo para goles Equipo Azul
    public JButton confirmarResultadoButton;
    public JButton volverButton;
    public JLabel fechaLabel;             // Mostrar la fecha del partido
    public JLabel formacionLabel;         // Mostrar la formación seleccionada

    public PartidoActualVista() {
        super("Partido Actual - Confirmar Resultado");
        initComponents();
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        // Panel principal con BorderLayout
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // ----- Norte: información básica (fecha y formación) -----
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);
        fechaLabel = new JLabel("Fecha: --/--/----", SwingConstants.CENTER);
        fechaLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        formacionLabel = new JLabel("Formación: --", SwingConstants.CENTER);
        formacionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(fechaLabel);
        infoPanel.add(formacionLabel);
        getContentPane().add(infoPanel, BorderLayout.NORTH);

        // ----- Centro: panel con ambas listas de jugadores y campos de resultados -----
        JPanel centroPanel = new JPanel(new GridBagLayout());
        centroPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Etiqueta Equipo Rojo
        JLabel rojoLabel = new JLabel("Equipo Rojo", SwingConstants.CENTER);
        rojoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        rojoLabel.setForeground(new Color(204, 0, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 0.0;
        centroPanel.add(rojoLabel, gbc);

        // Etiqueta Equipo Azul
        JLabel azulLabel = new JLabel("Equipo Azul", SwingConstants.CENTER);
        azulLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        azulLabel.setForeground(new Color(0, 0, 204));
        gbc.gridx = 1;
        gbc.gridy = 0;
        centroPanel.add(azulLabel, gbc);

        // Lista de jugadores Rojo dentro de JScrollPane
        listaRojo = new JList<>(new DefaultListModel<>());
        listaRojo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollRojo = new JScrollPane(listaRojo);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        centroPanel.add(scrollRojo, gbc);

        // Lista de jugadores Azul dentro de JScrollPane
        listaAzul = new JList<>(new DefaultListModel<>());
        listaAzul.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollAzul = new JScrollPane(listaAzul);
        gbc.gridx = 1;
        gbc.gridy = 1;
        centroPanel.add(scrollAzul, gbc);

        // Panel inferior de resultados (dos campos y botones)
        JPanel resultadosPanel = new JPanel(new GridBagLayout());
        resultadosPanel.setBackground(Color.WHITE);
        GridBagConstraints gbcRes = new GridBagConstraints();
        gbcRes.insets = new Insets(5, 5, 5, 5);
        gbcRes.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta "Goles Rojo"
        JLabel golesRojoLabel = new JLabel("Goles Rojo:");
        golesRojoLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbcRes.gridx = 0;
        gbcRes.gridy = 0;
        gbcRes.weightx = 0.0;
        resultadosPanel.add(golesRojoLabel, gbcRes);

        // Campo goles Rojo
        rojoScoreField = new JTextField("0");
        rojoScoreField.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbcRes.gridx = 1;
        gbcRes.gridy = 0;
        gbcRes.weightx = 0.5;
        resultadosPanel.add(rojoScoreField, gbcRes);

        // Etiqueta "Goles Azul"
        JLabel golesAzulLabel = new JLabel("Goles Azul:");
        golesAzulLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbcRes.gridx = 2;
        gbcRes.gridy = 0;
        gbcRes.weightx = 0.0;
        resultadosPanel.add(golesAzulLabel, gbcRes);

        // Campo goles Azul
        azulScoreField = new JTextField("0");
        azulScoreField.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbcRes.gridx = 3;
        gbcRes.gridy = 0;
        gbcRes.weightx = 0.5;
        resultadosPanel.add(azulScoreField, gbcRes);

        // Botón "Confirmar Resultado"
        confirmarResultadoButton = new JButton("Confirmar Resultado");
        confirmarResultadoButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        confirmarResultadoButton.setBackground(new Color(0, 102, 51));
        confirmarResultadoButton.setForeground(Color.WHITE);
        confirmarResultadoButton.setPreferredSize(new Dimension(200,  thirtySix()));
        gbcRes.gridx = 0;
        gbcRes.gridy = 1;
        gbcRes.gridwidth = 4;
        gbcRes.weightx = 1.0;
        resultadosPanel.add(confirmarResultadoButton, gbcRes);

        // Añadimos el panel de resultados debajo de las listas
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.0;
        centroPanel.add(resultadosPanel, gbc);

        getContentPane().add(centroPanel, BorderLayout.CENTER);

        // ----- Sur: botón de Volver -----
        JPanel surPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        surPanel.setBackground(Color.WHITE);
        volverButton = new JButton("Volver al Menú");
        volverButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        volverButton.setBackground(new Color(153, 153, 153));
        volverButton.setForeground(Color.WHITE);
        volverButton.setPreferredSize(new Dimension(160, thirtySix()));
        surPanel.add(volverButton);
        getContentPane().add(surPanel, BorderLayout.SOUTH);
    }

    private int thirtySix() {
        return 36;
    }

    /**
     * Método main de prueba para visualizar sólo esta ventana.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PartidoActualVista frame = new PartidoActualVista();
            frame.setVisible(true);
        });
    }
}
