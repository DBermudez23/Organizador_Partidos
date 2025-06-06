package VISTA.VistaOpcionesAdmin;

import javax.swing.*;
import java.awt.*;

/**
 * Vista del Menú Principal para el Administrador.
 * Contiene cuatro botones principales:
 *   1) Ver todos los jugadores
 *   2) Crear partido
 *   3) Ver sanciones pendientes
 *   4) Ver info del partido actual
 *
 * Los botones se presentan con un color de fondo verde oscuro (#006633),
 * texto blanco, y un tamaño uniforme. 
 *
 * El layout general es vertical (BoxLayout) con márgenes, para que quede
 * ordenado y “limpio” en cualquier resolución.
 */
public class MenuAdmin extends JFrame {

    // Componentes públicos para que el controlador asocie ActionListeners
    public JButton listaJugadoresButton;
    public JButton crearPartidoButton;
    public JButton sancionesPendientesButton;
    public JButton partidoActualButton;

    // Panel principal que contiene todo
    private JPanel mainPanel;
    // Un pequeño título arriba
    private JLabel tituloLabel;

    public MenuAdmin() {
        super("Menú Administrador");
        initComponents();

        setLocationRelativeTo(null);          // Centrar en pantalla
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();                                // Ajustar tamaño automáticamente
    }

    private void initComponents() {
        // 1) Crear el panel principal con BoxLayout vertical
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // Agregar un borde vacío para márgenes de 20px arriba/abajo y 30px izquierda/derecha
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // 2) Título en la parte superior
        tituloLabel = new JLabel("Opciones de Administrador");
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        tituloLabel.setForeground(new Color(0, 102, 51));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(tituloLabel);
        mainPanel.add(Box.createVerticalStrut(20)); // Espacio debajo del título

        // 3) Crear cada botón con estilo uniforme
        listaJugadoresButton = crearBotonVentana("VER TODOS LOS JUGADORES");
        crearPartidoButton = crearBotonVentana("CREAR PARTIDO");
        sancionesPendientesButton = crearBotonVentana("SANCIONES PENDIENTES");
        partidoActualButton = crearBotonVentana("INFO PARTIDO ACTUAL");

        // 4) Agrupar los botones en un panel intermedio para centrar horizontalmente
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));

        botonesPanel.add(listaJugadoresButton);
        botonesPanel.add(Box.createVerticalStrut(15));
        botonesPanel.add(crearPartidoButton);
        botonesPanel.add(Box.createVerticalStrut(15));
        botonesPanel.add(sancionesPendientesButton);
        botonesPanel.add(Box.createVerticalStrut(15));
        botonesPanel.add(partidoActualButton);

        botonesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(botonesPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Espacio inferior

        // 5) Agregar todo al JFrame
        getContentPane().add(mainPanel);
    }

    /**
     * Crea un botón con estilo consistente:
     *  - Fondo verde oscuro (#006633)
     *  - Texto en blanco
     *  - Fuente SansSerif 14pt en negrita
     *  - Tamaño preferido 300x40
     */
    private JButton crearBotonVentana(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(0, 102, 51));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusable(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300, 40));
        btn.setPreferredSize(new Dimension(300, 40));
        return btn;
    }

    /**
     * “Main” de prueba para abrir solo esta ventana sin controlador.
     */
    public static void main(String[] args) {
        // Intentar Forzar Look & Feel “Nimbus” (opcional)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // Si no funciona, se usa el L&F por defecto
        }

        SwingUtilities.invokeLater(() -> {
            MenuAdmin menu = new MenuAdmin();
            menu.setVisible(true);
        });
    }
}
