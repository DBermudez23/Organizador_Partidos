package CONTROLADOR;

import MODELO.Conexion;
import MODELO.Jugador;
import MODELO.Partido;
import VISTA.VistaOpcionesAdmin.MenuAdmin;
import VISTA.VistaOpcionesAdmin.PartidoActualVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la vista de Partido Actual.
 * - Recibe un Partido ya generado (con equipos asignados y fecha/formación).
 * - Muestra la lista de jugadores en cada equipo.
 * - Permite ingresar el resultado (goles) de Rojo y Azul.
 * - Al confirmar, guarda el partido en BD y regresa al menú Admin.
 */
public class ControladorPartidoActual {

    private final PartidoActualVista vista;
    private final Partido partido;

    public ControladorPartidoActual(PartidoActualVista vista, Partido partido) {
        this.vista = vista;
        this.partido = partido;

        // Configurar campos estáticos
        vista.fechaLabel.setText("Fecha: " + partido.getFecha().toString());
        vista.formacionLabel.setText("Formación: " + partido.getFormacion().getNombre());

        // Poblar listas de jugadores
        DefaultListModel<String> rojoModel = new DefaultListModel<>();
        for (Jugador j : partido.getEquipoRojo().getJugadores()) {
            rojoModel.addElement(j.getNombre());
        }
        vista.listaRojo.setModel(rojoModel);

        DefaultListModel<String> azulModel = new DefaultListModel<>();
        for (Jugador j : partido.getEquipoAzul().getJugadores()) {
            azulModel.addElement(j.getNombre());
        }
        vista.listaAzul.setModel(azulModel);

        // Registrar listeners
        vista.confirmarResultadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarResultado();
            }
        });
        vista.volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAlMenu();
            }
        });
    }

    /**
     * Lógica al presionar "Confirmar Resultado":
     * - Lee los goles ingresados.
     * - Actualiza el objeto Partido.
     * - Llama a Conexion.guardarPartido(...) para persistir en BD.
     * - Muestra mensaje de éxito/error y regresa al menú Admin.
     */
    private void confirmarResultado() {
        String golesRojoText = vista.rojoScoreField.getText().trim();
        String golesAzulText = vista.azulScoreField.getText().trim();

        int golesRojo, golesAzul;
        try {
            golesRojo = Integer.parseInt(golesRojoText);
            golesAzul = Integer.parseInt(golesAzulText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                vista,
                "Ingrese números válidos para goles.",
                "Error de formato",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        partido.setResultadoRojo(golesRojo);
        partido.setResultadoAzul(golesAzul);
        partido.setConfirmado(true);

        // Guardar en base de datos
        Conexion conexion = new Conexion();
        boolean ok = conexion.guardarPartido(partido,
                                             partido.getEquipoRojo(),
                                             partido.getEquipoAzul());

        if (ok) {
            JOptionPane.showMessageDialog(
                vista,
                "Partido guardado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
            volverAlMenu();
        } else {
            JOptionPane.showMessageDialog(
                vista,
                "Error al guardar el partido en la base de datos.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Cierra la vista de Partido Actual y abre nuevamente el Menú Admin.
     */
    private void volverAlMenu() {
        vista.dispose();
        MenuAdmin menu = new MenuAdmin();
        new ControladorOpcionesAdmin(menu);
        menu.setVisible(true);
    }
}
