package CONTROLADOR;

import MODELO.Conexion;
import MODELO.Jugador;
import MODELO.Partido;
import MODELO.Calificacion;
import MODELO.Singleton;
import VISTA.VistaOpcionesJugador.UltimoPartidoVista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador para UltimoPartidoVista.
 * - Obtiene el último partido jugado por el Jugador actual.
 * - Muestra fecha, resultado, lista de calificaciones y penalizados.
 * - Maneja el botón “Volver” para regresar al menú de jugador.
 */
public class ControladorUltimoPartido implements ActionListener {

    private final UltimoPartidoVista vista;
    private final Partido ultimoPartido;

    public ControladorUltimoPartido(UltimoPartidoVista vista) {
        this.vista = vista;

        // 1) Obtener el Jugador actual del Singleton
        Jugador jugadorActual = (Jugador) Singleton.getInstance().getUsuario();

        // 2) Consultar a la base de datos por el último partido de este jugador.
        //    Supongamos que Conexion.obtenerUltimoPartido(jugadorId) devuelve el objeto Partido.
        Conexion conexion = new Conexion();
        this.ultimoPartido = conexion.obtenerUltimoPartido(jugadorActual.getId());

        // 3) Llenar la vista con los datos del partido
        cargarDatos();

        // 4) Asociar el botón “Volver”
        vista.volverButton.addActionListener(this);
        vista.setVisible(true);
    }

    /**
     * Carga en la vista la información del último partido:
     * fecha, resultado, calificaciones y penalizados.
     */
    private void cargarDatos() {
        if (ultimoPartido == null) {
            JOptionPane.showMessageDialog(
                vista,
                "No hay un partido registrado para este jugador.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        // Fecha
        vista.fechaValueLabel.setText(ultimoPartido.getFecha().toString());

        // Resultado (formato “Rojo x - y Azul”)
        String resultado = ultimoPartido.getResultadoRojo() + " - " +
                           ultimoPartido.getResultadoAzul();
        vista.resultadoValueLabel.setText(resultado);

        // --------------- Calificaciones ---------------
        DefaultListModel<String> califModel = new DefaultListModel<>();
        // Por cada Jugador en el partido, obtenemos su última calificación
        for (Jugador j : ultimoPartido.getJugadores()) {
            List<Calificacion> listaCal = j.getCalificaciones();
            if (!listaCal.isEmpty()) {
                Calificacion ult = listaCal.get(listaCal.size() - 1);
                String texto = j.getNombre() + ": " + ult.getPuntuacion() + " pts";
                califModel.addElement(texto);
            }
        }
        vista.calificacionesList.setModel(califModel);

        // --------------- Penalizados ---------------
        DefaultListModel<String> penModel = new DefaultListModel<>();
        for (Jugador j : ultimoPartido.getJugadores()) {
            if (j.estaPenalizado()) {
                penModel.addElement(j.getNombre());
            }
        }
        vista.penalizadosList.setModel(penModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Si presionó “Volver”, regresar al menú de jugador
        if (e.getSource() == vista.volverButton) {
            vista.dispose();
            // Abrir menú de jugador
            VISTA.VistaOpcionesJugador.MenuJugador menu = new VISTA.VistaOpcionesJugador.MenuJugador();
            new ControladorOpcionesJugador(menu);
        }
    }
}
