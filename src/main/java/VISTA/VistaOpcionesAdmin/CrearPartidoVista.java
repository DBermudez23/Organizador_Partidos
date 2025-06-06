package VISTA.VistaOpcionesAdmin;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Date;

/**
 * Vista para crear un nuevo partido:
 * - Selección de fecha (JSpinner)
 * - Selección de formación (JComboBox)
 * - Lista de jugadores disponibles (JList en JScrollPane)
 * - Botones: Generar Reparto, Preconfirmar, Guardar Partido, Atrás
 *
 * NOTA: Los componentes públicos permiten que el controlador los asocie listeners y recupere datos.
 */
public class CrearPartidoVista extends JFrame {

    // 1) Componentes públicos para que el controlador asocie listeners y acceda a sus valores:
    public JSpinner fechaSpinner;
    public JComboBox<String> formacionCombo;
    public JList<String> jugadoresList;
    public JButton generarRepartoButton;
    public JButton preconfirmarButton;
    public JButton guardarPartidoButton;
    public JButton atrasButton;

    // Modelo para la lista de jugadores (puede reemplazarse por un DefaultListModel<Jugador>)
    private DefaultListModel<String> listaModeloJugadores;

    // Panel principal
    private JPanel mainPanel;

    public CrearPartidoVista() {
        super("Crear Partido - Fútbol 5");
        initComponents();
        setLocationRelativeTo(null); // Centrar ventana en pantalla
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
    }

    private void initComponents() {
        // =====================
        // 1) Panel principal
        // =====================
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // =============================
        // 2) Fecha de Partido (JSpinner)
        // =============================
        JLabel fechaLabel = new JLabel("Fecha del Partido:");
        fechaLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        fechaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(fechaLabel);
        mainPanel.add(Box.createVerticalStrut(5));

        // Spinner para seleccionar fecha (solo fecha, sin hora)
        fechaSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy");
        fechaSpinner.setEditor(dateEditor);
        fechaSpinner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        fechaSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(fechaSpinner);
        mainPanel.add(Box.createVerticalStrut(15));

        // ====================================================
        // 3) Selección de Formación (JComboBox<String>)
        // ====================================================
        JLabel formacionLabel = new JLabel("Formación:");
        formacionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        formacionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(formacionLabel);
        mainPanel.add(Box.createVerticalStrut(5));

        // ComboBox con formaciones de ejemplo
        formacionCombo = new JComboBox<>(new String[] {
            "Selección...", 
            "Formación 1: 2-2", 
            "Formación 2: 3-1", 
            "Formación 3: Libre"
        });
        formacionCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        formacionCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        mainPanel.add(formacionCombo);
        mainPanel.add(Box.createVerticalStrut(15));

        // ===============================
        // 4) Lista de Jugadores (JList)
        // ===============================
        JLabel jugadoresLabel = new JLabel("Jugadores Disponibles:");
        jugadoresLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jugadoresLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(jugadoresLabel);
        mainPanel.add(Box.createVerticalStrut(5));

        // Modelo vacío inicialmente; el controlador llenará con nombres reales
        listaModeloJugadores = new DefaultListModel<>();
        // Ejemplo de datos (para probar); el controlador deberá reemplazar esto:
        listaModeloJugadores.addElement("Andrés Martínez");
        listaModeloJugadores.addElement("Miguel Rodríguez");
        listaModeloJugadores.addElement("Luis González");
        listaModeloJugadores.addElement("Carlos Ramírez");
        listaModeloJugadores.addElement("Juan Fernández");
        listaModeloJugadores.addElement("Pedro García");

        jugadoresList = new JList<>(listaModeloJugadores);
        jugadoresList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jugadoresList.setVisibleRowCount(8);
        jugadoresList.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JScrollPane scrollJugadores = new JScrollPane(jugadoresList);
        scrollJugadores.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollJugadores.setPreferredSize(new Dimension(350, 150));
        scrollJugadores.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        mainPanel.add(scrollJugadores);
        mainPanel.add(Box.createVerticalStrut(20));

        // ========================================
        // 5) Panel de Botones de Acción
        // ========================================
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        generarRepartoButton = new JButton("Generar Reparto");
        generarRepartoButton.setBackground(new Color(0, 102, 51));
        generarRepartoButton.setForeground(Color.WHITE);
        generarRepartoButton.setFocusable(false);
        generarRepartoButton.setPreferredSize(new Dimension(140,  thirtyTwo()));
        botonesPanel.add(generarRepartoButton);

        preconfirmarButton = new JButton("Preconfirmar");
        preconfirmarButton.setBackground(new Color(0, 102, 51));
        preconfirmarButton.setForeground(Color.WHITE);
        preconfirmarButton.setFocusable(false);
        preconfirmarButton.setPreferredSize(new Dimension(120, thirtyTwo()));
        botonesPanel.add(preconfirmarButton);

        guardarPartidoButton = new JButton("Guardar Partido");
        guardarPartidoButton.setBackground(new Color(0, 102, 51));
        guardarPartidoButton.setForeground(Color.WHITE);
        guardarPartidoButton.setFocusable(false);
        guardarPartidoButton.setPreferredSize(new Dimension(140, thirtyTwo()));
        botonesPanel.add(guardarPartidoButton);

        atrasButton = new JButton("ATRÁS");
        atrasButton.setBackground(new Color(200, 0, 0));
        atrasButton.setForeground(Color.WHITE);
        atrasButton.setFocusable(false);
        atrasButton.setPreferredSize(new Dimension(100, thirtyTwo()));
        botonesPanel.add(atrasButton);

        mainPanel.add(botonesPanel);
        mainPanel.add(Box.createVerticalStrut(10));

        // ========================================
        // 6) Añadir panel principal al JFrame
        // ========================================
        getContentPane().add(mainPanel);
    }

    private int thirtyTwo() {
        return 32;
    }

    /**
     * Permite que el controlador reemplace el contenido de la lista de jugadores.
     * Por ejemplo: cargar un DefaultListModel<String> con nombres reales.
     */
    public void setJugadoresLista(DefaultListModel<String> modelo) {
        jugadoresList.setModel(modelo);
    }

    /**
     * Permite al controlador asociar un ListSelectionListener a la JList de jugadores.
     */
    public void addJugadoresListSelectionListener(ListSelectionListener listener) {
        jugadoresList.addListSelectionListener(listener);
    }

    /**
     * Método principal de prueba para abrir únicamente esta ventana.
     */
    public static void main(String[] args) {
        // Intentar usar Nimbus, si está disponible
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // Si falla, no importa: se usará el L&F por defecto.
        }

        SwingUtilities.invokeLater(() -> {
            CrearPartidoVista ventana = new CrearPartidoVista();
            ventana.setVisible(true);
        });
    }
}
