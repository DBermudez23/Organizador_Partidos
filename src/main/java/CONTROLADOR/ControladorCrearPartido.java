package CONTROLADOR;

import MODELO.Conexion;
import MODELO.Equipo;
import MODELO.Equipo.TipoEquipo;
import MODELO.Formaciones;
import MODELO.Jugador;
import MODELO.ListaJugadores;
import MODELO.Partidos;
import MODELO.ReparticionEquipos;
import MODELO.ReparticionImparPar;
import MODELO.ReparticionDefinida;
import MODELO.Singleton;
import VISTA.VistaOpcionesAdmin.CrearPartidoVista;
import VISTA.VistaOpcionesAdmin.PartidoActualVista;

import javax.swing.*;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controlador para la ventana CrearPartidoVista.
 *
 * Funcionalidades principales:
 *   1) Cargar en la JList todos los jugadores disponibles.
 *   2) Cargar en el JComboBox las formaciones disponibles.
 *   3) Permitir elegir fecha y formación, seleccionar jugadores y generar reparto.
 *   4) Construir un objeto Partidos + dos objetos Equipo (ROJO y AZUL).
 *   5) “Preconfirmar” abre PartidoActualVista para que se registren resultados.
 *   6) “Guardar Partido” persiste Partidos + ambos Equipos + sus relaciones con jugadores.
 *   7) “Atrás” cierra la ventana y regresa al menú anterior.
 */
public class ControladorCrearPartido implements ActionListener {

    private final CrearPartidoVista vista;
    private final ListaJugadores modeloLista;    // Modelo para obtener List<Jugador>
    private final Formaciones modeloFormaciones; // Modelo para manejar formaciones
    private ReparticionEquipos estrategiaReparto; // Estrategia de reparto (ImparPar, Definida, ...)
    private final Conexion conexion;             // Para persistir en BD

    // Tras “Generar Reparto”:
    private Partidos partidoActual;
    private Equipo equipoRojo;
    private Equipo equipoAzul;

    public ControladorCrearPartido(CrearPartidoVista vista) {
        this.vista = vista;

        // 1) Asociar botones al ActionListener
        this.vista.generarRepartoButton.addActionListener(this);
        this.vista.preconfirmarButton.addActionListener(this);
        this.vista.guardarPartidoButton.addActionListener(this);
        this.vista.atrasButton.addActionListener(this);

        // 2) Inicializar modelos/servicios
        this.modeloLista = new ListaJugadores();
        this.modeloFormaciones = new Formaciones();
        this.conexion = new Conexion();

        // 3) Cargar listado de jugadores en la JList (mostramos solo su nombre)
        cargarJugadoresEnVista();

        // 4) Cargar opciones de formación en el JComboBox
        cargarFormacionesEnVista();

        // 5) Seleccionar estrategia de reparto por defecto (Impar/Par).
        //    Si quieres usar “ReparticionDefinida”, reemplaza aquí:
        this.estrategiaReparto = new ReparticionImparPar();
    }

    /**
     * Llenar la JList con los nombres de todos los Jugador obtenidos de ListaJugadores.
     */
    private void cargarJugadoresEnVista() {
        List<Jugador> todos = modeloLista.getJugadores();
        DefaultListModel<String> listaModel = new DefaultListModel<>();
        for (Jugador j : todos) {
            listaModel.addElement(j.getNombre());
        }
        vista.setJugadoresLista(listaModel);
    }

    /**
     * Llenar el JComboBox con todas las formaciones disponibles (como cadenas de texto).
     */
    private void cargarFormacionesEnVista() {
        List<String> formacionesDisponibles = modeloFormaciones.getFormaciones();
        vista.formacionCombo.removeAllItems();
        vista.formacionCombo.addItem("Seleccione formación...");
        for (String f : formacionesDisponibles) {
            vista.formacionCombo.addItem(f);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();

        if (origen == vista.generarRepartoButton) {
            manejarGenerarReparto();
        }
        else if (origen == vista.preconfirmarButton) {
            manejarPreconfirmar();
        }
        else if (origen == vista.guardarPartidoButton) {
            manejarGuardarPartido();
        }
        else if (origen == vista.atrasButton) {
            vista.dispose();
        }
    }

    /**
     * 1) Verifica que se haya seleccionado una formación válida.
     * 2) Lee fecha desde el JSpinner (vista.fechaSpinner) y la convierte a LocalDate.
     * 3) Obtiene lista de nombres seleccionados en la JList.
     * 4) Traduce nombres a objetos Jugador y llama a estrategiaReparto.repartir(...)
     *    para obtener dos objetos Equipo (uno tipo ROJO y otro tipo AZUL).
     * 5) Construye un nuevo Partidos con fecha y la formación seleccionada.
     * 6) Muestra un resumen en un JOptionPane.
     */
    private void manejarGenerarReparto() {
        // --- Verificar formación ---
        int idxForm = vista.formacionCombo.getSelectedIndex();
        if (idxForm <= 0) {
            JOptionPane.showMessageDialog(
                vista,
                "Debe seleccionar una formación para generar el reparto.",
                "Error: formación no seleccionada",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // --- Leer fecha ---
        Date fechaDate = (Date) vista.fechaSpinner.getValue();
        LocalDate fechaLocal = fechaDate
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // --- Obtener nombres seleccionados en la JList ---
        List<String> seleccion = vista.jugadoresList.getSelectedValuesList();
        if (seleccion == null || seleccion.isEmpty()) {
            JOptionPane.showMessageDialog(
                vista,
                "Debe seleccionar al menos un jugador para repartir.",
                "Error: jugadores no seleccionados",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // --- Traducir nombres a objetos Jugador ---
        List<Jugador> jugadoresSeleccionados = new ArrayList<>();
        for (String nombre : seleccion) {
            for (Jugador j : modeloLista.getJugadores()) {
                if (j.getNombre().equals(nombre)) {
                    jugadoresSeleccionados.add(j);
                    break;
                }
            }
        }

        // --- Ejecutar estrategia de reparto ---
        // Estrategia debe devolver Object[] { Equipo rojo, Equipo azul }
        Object[] resultado = estrategiaReparto.repartir(jugadoresSeleccionados);
        equipoRojo = (Equipo) resultado[0];
        equipoAzul = (Equipo) resultado[1];

        // --- Construir Partidos (sin ID aún) ---
        partidoActual = new Partidos();
        partidoActual.setFecha(fechaLocal);

        // Obtener el nombre de la formación elegida (índice idxForm)
        String nombreFormacion = (String) vista.formacionCombo.getItemAt(idxForm);
        // Asumimos que Formaciones tiene método para retornar un objeto Formacion/ID:
        partidoActual.setFormacion(modeloFormaciones.getFormacionPorNombre(nombreFormacion));

        // --- Mostrar resumen ---
        StringBuilder sb = new StringBuilder();
        sb.append("Reparto generado:\n\n");
        sb.append("Fecha: ").append(fechaLocal).append("\n");
        sb.append("Formación: ").append(nombreFormacion).append("\n\n");

        sb.append("Equipo Rojo (").append(equipoRojo.getJugadores().size()).append("):\n");
        for (Jugador j : equipoRojo.getJugadores()) {
            sb.append("  • ").append(j.getNombre()).append("\n");
        }
        sb.append("\nEquipo Azul (").append(equipoAzul.getJugadores().size()).append("):\n");
        for (Jugador j : equipoAzul.getJugadores()) {
            sb.append("  • ").append(j.getNombre()).append("\n");
        }

        JOptionPane.showMessageDialog(
            vista,
            sb.toString(),
            "Reparto Generado",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Verifica que partidoActual y ambos equipos existan, luego abre PartyActualVista
     * (para que el admin ingrese resultado y confirme), pasando los objetos recién creados.
     */
    private void manejarPreconfirmar() {
        if (partidoActual == null || equipoRojo == null || equipoAzul == null) {
            JOptionPane.showMessageDialog(
                vista,
                "Primero debe generar el reparto antes de preconfirmar.",
                "Error: reparto no generado",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        PartidoActualVista vistaPartido = new PartidoActualVista();
        new ControladorPartidoActual(vistaPartido, partidoActual, equipoRojo, equipoAzul);

        vistaPartido.setVisible(true);
        vista.dispose();
    }

    /**
     * Verifica que exista un reparto previo y un objeto Partidos, e invoca a
     * conexion.guardarPartido(partidoActual, equipoRojo, equipoAzul). Muestra un mensaje
     * informando éxito o error en la base de datos.
     */
    private void manejarGuardarPartido() {
        if (partidoActual == null || equipoRojo == null || equipoAzul == null) {
            JOptionPane.showMessageDialog(
                vista,
                "Necesita generar y preconfirmar el reparto antes de guardar.",
                "Error: datos incompletos",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean exito = conexion.guardarPartido(partidoActual, equipoRojo, equipoAzul);
        if (exito) {
            JOptionPane.showMessageDialog(
                vista,
                "Partido guardado exitosamente en la base de datos.",
                "Guardado Exitoso",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                vista,
                "Error al guardar el partido en la base de datos.",
                "Error de Persistencia",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
