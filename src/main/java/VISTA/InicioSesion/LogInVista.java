package VISTA.InicioSesion;

import javax.swing.*;
import java.awt.*;

/**
 * Un formulario de login con estilo más amplio y atractivo (sin imágenes).
 * - Encabezado centrado
 * - Campos de entrada más grandes
 * - Botones destacados
 */
public class LogInVista extends JFrame {

    public JTextField nombreUsuarioCampo;
    public JPasswordField contraseñaCampo;
    public JButton ingresarBoton;
    public JButton nuevoUsuarioBoton;

    private JLabel encabezadoLabel;
    private JLabel nombreUsuarioLabel;
    private JLabel contraseñaLabel;
    private JPanel formPanel;

    public LogInVista() {
        super("Inicio de Sesión - Fútbol 5");
        initComponents();
        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar ventana
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        // Color de fondo general
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // -----------------------
        // 1) Encabezado superior
        // -----------------------
        encabezadoLabel = new JLabel("Bienvenido a Fútbol 5", SwingConstants.CENTER);
        encabezadoLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        encabezadoLabel.setForeground(new Color(0, 102, 51));
        encabezadoLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(encabezadoLabel, BorderLayout.NORTH);

        // -------------------------
        // 2) Panel central (formulario)
        // -------------------------
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // 2.1) Etiqueta “Nombre de Usuario”
        nombreUsuarioLabel = new JLabel("Nombre de Usuario:");
        nombreUsuarioLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(nombreUsuarioLabel, gbc);

        // 2.2) Campo de texto usuario
        nombreUsuarioCampo = new JTextField();
        nombreUsuarioCampo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        formPanel.add(nombreUsuarioCampo, gbc);

        // 2.3) Etiqueta “Contraseña”
        contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(contraseñaLabel, gbc);

        // 2.4) Campo de contraseña
        contraseñaCampo = new JPasswordField();
        contraseñaCampo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        formPanel.add(contraseñaCampo, gbc);

        add(formPanel, BorderLayout.CENTER);

        // -------------------------
        // 3) Panel inferior (botones)
        // -------------------------
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        botonesPanel.setBackground(Color.WHITE);

        // Botón “INGRESAR”
        ingresarBoton = new JButton("INGRESAR");
        ingresarBoton.setFont(new Font("SansSerif", Font.BOLD, 16));
        ingresarBoton.setBackground(new Color(0, 102, 51));
        ingresarBoton.setForeground(Color.WHITE);
        ingresarBoton.setPreferredSize(new Dimension(160, 40));
        ingresarBoton.setFocusPainted(false);
        botonesPanel.add(ingresarBoton);

        // Botón “NUEVO USUARIO”
        nuevoUsuarioBoton = new JButton("NUEVO USUARIO");
        nuevoUsuarioBoton.setFont(new Font("SansSerif", Font.BOLD, 16));
        nuevoUsuarioBoton.setBackground(new Color(0, 102, 51));
        nuevoUsuarioBoton.setForeground(Color.WHITE);
        nuevoUsuarioBoton.setPreferredSize(new Dimension(180, 40));
        nuevoUsuarioBoton.setFocusPainted(false);
        botonesPanel.add(nuevoUsuarioBoton);

        add(botonesPanel, BorderLayout.SOUTH);
    }

    /**
     * Devuelve el texto escrito en el campo “Nombre de Usuario”.
     */
    public String getUsername() {
        return nombreUsuarioCampo.getText().trim();
    }

    /**
     * Devuelve el arreglo de caracteres que contiene la contraseña.
     */
    public char[] getPassword() {
        return contraseñaCampo.getPassword();
    }

    /**
     * Muestra un diálogo emergente con un mensaje de error.
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Error de Autenticación",
            JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Muestra un diálogo emergente con un mensaje de información.
     */
    public void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Información",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Main de prueba para visualizar esta ventana.
     */
    public static void main(String[] args) {
        // Forzar look and feel “Nimbus” si está disponible
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // Si falla, se usa el por defecto
        }

        SwingUtilities.invokeLater(() -> {
            LogInVista vista = new LogInVista();
            vista.setVisible(true);
        });
    }
}
