package VISTA.VistaOpcionesJugador;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Vista que muestra la información del próximo partido en configuración.
 * Incluye:
 *  - Fecha del partido
 *  - Formación seleccionada
 *  - Listas de jugadores en Equipo Rojo y Equipo Azul
 */
public class ProximoPartidoVista extends JFrame {

    // Componentes para mostrar información del partido
    private JLabel fechaLabel;
    private JLabel formacionLabel;

    // Listas para mostrar miembros de cada equipo
    private JList<String> equipoRojoList;
    private JList<String> equipoAzulList;

    // Botones de acción (por ejemplo, confirmar asistencia o volver)
    public JButton confirmarAsistenciaButton;
    public JButton volverButton;

    public ProximoPartidoVista() {
        super("Próximo Partido");
        initComponents();
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        // Establecer layout principal
        getContentPane().setLayout(new BorderLayout(10, 10));

        // ----- 1) Panel superior: fecha y formación -----
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        fechaLabel = new JLabel("Fecha: --/--/----");
        fechaLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        infoPanel.add(fechaLabel);

        formacionLabel = new JLabel("Formación: N/A");
        formacionLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        infoPanel.add(formacionLabel);

        getContentPane().add(infoPanel, BorderLayout.NORTH);

        // ----- 2) Panel central: dos listas en paralelo para mostrar equipos -----
        JPanel equiposPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        equiposPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // Equipo Rojo
        equipoRojoList = new JList<>(new DefaultListModel<>());
        equipoRojoList.setEnabled(false);
        JScrollPane rojoScroll = new JScrollPane(equipoRojoList);
        rojoScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.RED),
                "Equipo Rojo",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                Color.RED));
        equiposPanel.add(rojoScroll);

        // Equipo Azul
        equipoAzulList = new JList<>(new DefaultListModel<>());
        equipoAzulList.setEnabled(false);
        JScrollPane azulScroll = new JScrollPane(equipoAzulList);
        azulScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE),
                "Equipo Azul",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                Color.BLUE));
        equiposPanel.add(azulScroll);

        getContentPane().add(equiposPanel, BorderLayout.CENTER);

        // ----- 3) Panel inferior: botones de acción -----
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        confirmarAsistenciaButton = new JButton("Confirmar Asistencia");
        confirmarAsistenciaButton.setBackground(new Color(0, 102, 51));
        confirmarAsistenciaButton.setForeground(Color.WHITE);
        confirmarAsistenciaButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        confirmarAsistenciaButton.setPreferredSize(new Dimension(180, 40));
        botonesPanel.add(confirmarAsistenciaButton);

        volverButton = new JButton("Volver");
        volverButton.setBackground(new Color(128, 128, 128));
        volverButton.setForeground(Color.WHITE);
        volverButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        volverButton.setPreferredSize(new Dimension(100, 40));
        botonesPanel.add(volverButton);

        getContentPane().add(botonesPanel, BorderLayout.SOUTH);
    }

    /**
     * Permite actualizar la fecha que se muestra.
     *
     * @param fechaTexto texto con la fecha del partido (ej. "12/06/2025")
     */
    public void setFecha(String fechaTexto) {
        fechaLabel.setText("Fecha: " + fechaTexto);
    }

    /**
     * Permite actualizar la formación que se muestra.
     *
     * @param formacionTexto nombre de la formación seleccionada
     */
    public void setFormacion(String formacionTexto) {
        formacionLabel.setText("Formación: " + formacionTexto);
    }

    /**
     * Carga la lista de nombres en el panel de Equipo Rojo.
     *
     * @param jugadoresRojo array (o lista) de cadenas con nombres de jugadores rojos
     */
    public void setEquipoRojo(String[] jugadoresRojo) {
        DefaultListModel<String> modelRojo = new DefaultListModel<>();
        for (String nombre : jugadoresRojo) {
            modelRojo.addElement(nombre);
        }
        equipoRojoList.setModel(modelRojo);
    }

    /**
     * Carga la lista de nombres en el panel de Equipo Azul.
     *
     * @param jugadoresAzul array (o lista) de cadenas con nombres de jugadores azules
     */
    public void setEquipoAzul(String[] jugadoresAzul) {
        DefaultListModel<String> modelAzul = new DefaultListModel<>();
        for (String nombre : jugadoresAzul) {
            modelAzul.addElement(nombre);
        }
        equipoAzulList.setModel(modelAzul);
    }

    /**
     * Por si el controlador quiere obtener la referencia a la lista de rojos
     * y realizar alguna acción adicional.
     */
    public JList<String> getEquipoRojoList() {
        return equipoRojoList;
    }

    /**
     * Por si el controlador quiere obtener la referencia a la lista de azules
     * y realizar alguna acción adicional.
     */
    public JList<String> getEquipoAzulList() {
        return equipoAzulList;
    }
}
