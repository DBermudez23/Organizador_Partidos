package VISTA.InicioSesion;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario de registro para nuevo jugador, con estilo amplio y atractivo.
 * - Etiqueta de encabezado.
 * - Campos de entrada grandes.
 * - Botones destacados con color verde.
 */
public class RegistroVista extends JFrame {

    public JTextField nombreUsuarioCampo;
    public JTextField mailCampo;
    public JPasswordField contraseñaCampo;
    public JPasswordField repetirContraseñaCampo;
    public JButton atrasBoton;
    public JButton registrarseBoton;

    private JLabel encabezadoLabel;
    private JLabel nombreUsuarioLabel;
    private JLabel mailLabel;
    private JLabel arrobaLabel;
    private JLabel contraseñaLabel;
    private JLabel repetirContraseñaLabel;
    private JPanel formPanel;

    public RegistroVista() {
        super("Registro de Nuevo Jugador");
        initComponents();
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        // Fondo blanco
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // -----------------------------------
        // 1) Encabezado en la parte superior
        // -----------------------------------
        encabezadoLabel = new JLabel("Crear Cuenta - Fútbol 5", SwingConstants.CENTER);
        encabezadoLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        encabezadoLabel.setForeground(new Color(0, 102, 51));
        encabezadoLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(encabezadoLabel, BorderLayout.NORTH);

        // ---------------------------------------
        // 2) Panel central con los campos del formulario
        // ---------------------------------------
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12, 12, 12, 12);

        // 2.1) Nombre de Usuario
        nombreUsuarioLabel = new JLabel("Nombre de Usuario:");
        nombreUsuarioLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(nombreUsuarioLabel, gbc);

        nombreUsuarioCampo = new JTextField();
        nombreUsuarioCampo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        formPanel.add(nombreUsuarioCampo, gbc);

        // 2.2) Mail (parte local + etiqueta fija)
        mailLabel = new JLabel("Mail:");
        mailLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(mailLabel, gbc);

        JPanel mailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        mailPanel.setBackground(Color.WHITE);
        mailCampo = new JTextField();
        mailCampo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        mailCampo.setPreferredSize(new Dimension(200,  thirtySix()));
       (mailPanel).add(mailCampo);

        arrobaLabel = new JLabel("@futbol5.com");
        arrobaLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        mailPanel.add(arrobaLabel);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        formPanel.add(mailPanel, gbc);

        // 2.3) Contraseña
        contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(contraseñaLabel, gbc);

        contraseñaCampo = new JPasswordField();
        contraseñaCampo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        formPanel.add(contraseñaCampo, gbc);

        // 2.4) Repetir Contraseña
        repetirContraseñaLabel = new JLabel("Repetir Contraseña:");
        repetirContraseñaLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        formPanel.add(repetirContraseñaLabel, gbc);

        repetirContraseñaCampo = new JPasswordField();
        repetirContraseñaCampo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.7;
        formPanel.add(repetirContraseñaCampo, gbc);

        add(formPanel, BorderLayout.CENTER);

        // ----------------------------
        // 3) Panel inferior (botones)
        // ----------------------------
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 15));
        botonesPanel.setBackground(Color.WHITE);

        atrasBoton = new JButton("ATRÁS");
        atrasBoton.setFont(new Font("SansSerif", Font.BOLD, 16));
        atrasBoton.setBackground(new Color(0, 102, 51));
        atrasBoton.setForeground(Color.WHITE);
        atrasBoton.setPreferredSize(new Dimension(120,  forty()));
        atrasBoton.setFocusPainted(false);
        botonesPanel.add(atrasBoton);

        registrarseBoton = new JButton("REGISTRARSE");
        registrarseBoton.setFont(new Font("SansSerif", Font.BOLD, 16));
        registrarseBoton.setBackground(new Color(0, 102, 51));
        registrarseBoton.setForeground(Color.WHITE);
        registrarseBoton.setPreferredSize(new Dimension(160,  forty()));
        registrarseBoton.setFocusPainted(false);
        botonesPanel.add(registrarseBoton);

        add(botonesPanel, BorderLayout.SOUTH);
    }

    private int thirtySix() {
        return 36;
    }

    private int forty() {
        return 40;
    }

    /**
     * Devuelve el nombre de usuario ingresado.
     */
    public String getUsername() {
        return nombreUsuarioCampo.getText().trim();
    }

    /**
     * Devuelve la parte “local” del mail (antes de @futbol5.com).
     */
    public String getMailLocalPart() {
        return mailCampo.getText().trim();
    }

    /**
     * @return la contraseña ingresada como arreglo de char.
     */
    public char[] getPassword() {
        return contraseñaCampo.getPassword();
    }

    /**
     * @return la repetición de contraseña ingresada como arreglo de char.
     */
    public char[] getPasswordRepeat() {
        return repetirContraseñaCampo.getPassword();
    }

    /**
     * Muestra un mensaje de error (por ejemplo, si las contraseñas no coinciden).
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Error de Registro",
            JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Muestra un mensaje de información (por ejemplo, “Registro exitoso”).
     */
    public void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Registro",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Método main de prueba para abrir solo esta ventana.
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
            // Si falla, se usa look & feel por defecto
        }

        SwingUtilities.invokeLater(() -> {
            RegistroVista registro = new RegistroVista();
            registro.setVisible(true);
        });
    }
}
