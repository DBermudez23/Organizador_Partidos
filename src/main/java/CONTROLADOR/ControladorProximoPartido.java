package CONTROLADOR;

import MODELO.Partido;
import MODELO.Singleton;
import MODELO.Jugador;
import VISTA.VistaOpcionesJugador.ProximoPartidoVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para ProximoPartidoVista:
 *  - Obtiene el Partido “actual” (en configuración) desde Singleton.
 *  - Carga fecha, formación y listas de jugadores en cada equipo.
 *  - Permite al Jugador confirmar asistencia (lo agrega a la lista de inscritos).
 *  - Botón “Volver” cierra esta vista y regresa al menú de Jugador.
 */
public class ControladorProximoPartido implements ActionListener {

    private final ProximoPartidoVista vista;
    private final Partido partido;
    private final Jugador jugadorActual;

    public ControladorProximoPartido(ProximoPartidoVista vista) {
        this.vista = vista;

        // Obtener el partido en configuración desde Singleton
        this.partido = Singleton.getInstance().getPartidoActual();
        // Obtener el jugador logueado
        this.jugadorActual = (Jugador) Singleton.getInstance().getUsuario();

        // Configurar vista y listeners
        this.vista.confirmarAsistenciaButton.addActionListener(this);
        this.vista.volverButton.addActionListener(this);

        // Cargar datos iniciales en la vista
        cargarDatosPartido();
    }

    private void cargarDatosPartido() {
        if (partido == null) {
            // Si no hay partido cargado, mostrar mensaje y cerrar vista
            vista.mostrarError("No hay ningún partido en configuración.");
            vista.dispose();
            return;
        }

        // 1) Fecha
        String fechaTexto = partido.getFecha()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        vista.setFecha(fechaTexto);

        // 2) Formación
        String formacionTexto = (partido.getFormacion() != null)
                ? partido.getFormacion().getNombre()
                : "N/A";
        vista.setFormacion(formacionTexto);

        // 3) Listas de jugadores en cada equipo (nombres)
        List<String> nombresRojo = partido.getEquipoRojo()
                .getJugadores()
                .stream()
                .map(Jugador::getNombre)
                .collect(Collectors.toList());

        List<String> nombresAzul = partido.getEquipoAzul()
                .getJugadores()
                .stream()
                .map(Jugador::getNombre)
                .collect(Collectors.toList());

        vista.setEquipoRojo(nombresRojo.toArray(new String[0]));
        vista.setEquipoAzul(nombresAzul.toArray(new String[0]));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.confirmarAsistenciaButton) {
            confirmarAsistencia();
        } else if (src == vista.volverButton) {
            vista.dispose();
            // Regresar al menú de Jugador
            // (se asume que existe un ControladorOpcionesJugador para reabrirlo)
            new ControladorOpcionesJugador(Singleton.getInstance().getMenuJugadorVista());
        }
    }

    private void confirmarAsistencia() {
        boolean agregado = partido.agregarJugador(jugadorActual);
        if (agregado) {
            vista.mostrarInfo("Asistencia confirmada. ¡Nos vemos en la cancha!");
            // Actualizar listas en pantalla
            cargarDatosPartido();
        } else {
            vista.mostrarError("No puedes confirmar asistencia:\n" +
                               "- Quizás ya estás inscrito o estás penalizado.");
        }
    }
}
