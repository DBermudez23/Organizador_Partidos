package VISTA.VistaOpcionesJugador;

import javax.swing.*;
import java.awt.*;

/**
 * Menú de opciones para el Jugador.
 * Contiene botones para:
 *  - Próximo Partido
 *  - Calificación Último Partido
 *  - Mi Perfil
 *  - Enviar Mail
 *  - Reglas
 *
 * Esta versión usa un diseño más limpio y amplio,
 * con un cabezal gráfico y botones de color verde (0,102,51).
 */
public class MenuJugador extends JFrame {

    // Componentes públicos para que el controlador les asocie listeners
    public JButton proximoPartidoButton;
    public JButton calificacionUltimoButton;
    public JButton miPerfilButton;
    public JButton mailButton;
    public JButton reglasButton;

    // Etiqueta de imagen (logo o ilustración del jugador)
    private JLabel encabezadoLabel;

    // Panel principal de contenido
    private JPanel mainPanel;

    public MenuJugador() {
        super("Menú Jugador");
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
    }

    private void initComponents() {
        // Panel principal con BoxLayout vertical
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // 1) Encabezado gráfico (puedes reemplazar "menuJugador.png" por tu propio recurso)
        encabezadoLabel = new JLabel();
        encabezadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon(getClass().getResource("/VISTA/VistaOpcionesJugador/menuJugador.png"));
        encabezadoLabel.setIcon(icon);
        mainPanel.add(encabezadoLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // 2) Botones: misma anchura, color de fondo verde (0,102,51), texto blanco, altura fija
        Dimension buttonSize = new Dimension(300,  forty());
        Color verde = new Color(0, 102, 51);

        proximoPartidoButton = new JButton("PRÓXIMO PARTIDO");
        proximoPartidoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        proximoPartidoButton.setBackground(verde);
        proximoPartidoButton.setForeground(Color.WHITE);
        proximoPartidoButton.setFocusable(false);
        proximoPartidoButton.setPreferredSize(buttonSize);
        proximoPartidoButton.setMaximumSize(buttonSize);
        mainPanel.add(proximoPartidoButton);
        mainPanel.add(Box.createVerticalStrut(15));

        calificacionUltimoButton = new JButton("CALIFICACIÓN ÚLTIMO PARTIDO");
        calificacionUltimoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calificacionUltimoButton.setBackground(verde);
        calificacionUltimoButton.setForeground(Color.WHITE);
        calificacionUltimoButton.setFocusable(false);
        calificacionUltimoButton.setPreferredSize(buttonSize);
        calificacionUltimoButton.setMaximumSize(buttonSize);
        mainPanel.add(calificacionUltimoButton);
        mainPanel.add(Box.createVerticalStrut(15));

        miPerfilButton = new JButton("MI PERFIL");
        miPerfilButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        miPerfilButton.setBackground(verde);
        miPerfilButton.setForeground(Color.WHITE);
        miPerfilButton.setFocusable(false);
        miPerfilButton.setPreferredSize(buttonSize);
        miPerfilButton.setMaximumSize(buttonSize);
        mainPanel.add(miPerfilButton);
        mainPanel.add(Box.createVerticalStrut(15));

        mailButton = new JButton("MAIL");
        mailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mailButton.setBackground(verde);
        mailButton.setForeground(Color.WHITE);
        mailButton.setFocusable(false);
        mailButton.setPreferredSize(buttonSize);
        mailButton.setMaximumSize(buttonSize);
        mainPanel.add(mailButton);
        mainPanel.add(Box.createVerticalStrut(15));

        reglasButton = new JButton("REGLAS");
        reglasButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reglasButton.setBackground(verde);
        reglasButton.setForeground(Color.WHITE);
        reglasButton.setFocusable(false);
        reglasButton.setPreferredSize(buttonSize);
        reglasButton.setMaximumSize(buttonSize);
        mainPanel.add(reglasButton);
        mainPanel.add(Box.createVerticalStrut(10));

        // Agregar todo el panel al JFrame
        getContentPane().add(mainPanel);
    }

    private int forty() {
        return 40;
    }

    /**
     * Ejemplo de main para probar solo esta ventana.
     */
    public static void main(String[] args) {
        // Intentar aplicar Look and Feel "Nimbus" si está disponible
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // Si falla, se usa el L&F por defecto
        }

        SwingUtilities.invokeLater(() -> {
            MenuJugador ventana = new MenuJugador();
            ventana.setVisible(true);
        });
    }
}
