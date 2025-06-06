package CONTROLADOR;

import VISTA.VistaOpcionesAdmin.MenuAdmin;
import VISTA.VistaOpcionesJugador.MenuJugador;                 // Vista de opciones de jugador
import VISTA.VistaOpcionesAdmin.CrearPartidoVista;
import VISTA.VistaOpcionesAdmin.PartidoActualVista;
import VISTA.VistaOpcionesAdmin.ListaJugadoresVista;
import VISTA.VistaOpcionesAdmin.SancionesPendientesVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Controlador para el menú de Opciones de Administrador.
 * Registra los listeners de cada botón y abre la vista/controlador correspondiente.
 */
public class ControladorOpcionesAdmin implements ActionListener {

    private final MenuAdmin vistaOpcionesAdmin;

    public ControladorOpcionesAdmin(MenuAdmin vistaOpcionesAdmin) {
        this.vistaOpcionesAdmin = vistaOpcionesAdmin;

        // Configuración inicial de la ventana
        this.vistaOpcionesAdmin.setVisible(true);
        this.vistaOpcionesAdmin.setLocationRelativeTo(null);
        this.vistaOpcionesAdmin.setResizable(false);
        this.vistaOpcionesAdmin.setTitle("Menú Administrador");

        
        this.vistaOpcionesAdmin.crearPartidoButton.addActionListener(this);
        this.vistaOpcionesAdmin.partidoActualButton.addActionListener(this);
        this.vistaOpcionesAdmin.listaJugadoresButton.addActionListener(this);
        this.vistaOpcionesAdmin.sancionesPendientesButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vistaOpcionesAdmin.crearPartidoButton) {
            abrirCrearPartido();
        }
        else if (source == vistaOpcionesAdmin.partidoActualButton) {
            abrirPartidoActual();
        }
        else if (source == vistaOpcionesAdmin.listaJugadoresButton) {
            abrirListaJugadores();
        }
        else if (source == vistaOpcionesAdmin.sancionesPendientesButton) {
            abrirSancionesPendientes();
        }
    }

    // ===========================
    // Métodos auxiliares para cada acción
    // ===========================

    /**
     * Abre la vista/controlador que permite crear un nuevo partido.
     */
    private void abrirCrearPartido() {
        CrearPartidoVista crearVista = new CrearPartidoVista();
        new ControladorCrearPartido(crearVista);
        // Opcional: cerrar el menú de Admin
        vistaOpcionesAdmin.dispose();
    }

    /**
     * Abre la vista/controlador que muestra el partido actual en curso.
     */
    private void abrirPartidoActual() {
        PartidoActualVista actualVista = new PartidoActualVista();
        new ControladorPartidoActual(actualVista);
        // Opcional: cerrar el menú de Admin
        vistaOpcionesAdmin.dispose();
    }

    /**
     * Abre la vista/controlador que muestra la lista de todos los jugadores.
     */
    private void abrirListaJugadores() {
        ListaJugadoresVista listaVista = new ListaJugadoresVista();
        new ControladorListaJugadores(listaVista);
        // Opcional: cerrar el menú de Admin
        vistaOpcionesAdmin.dispose();
    }

    /**
     * Abre la vista/controlador que muestra los jugadores con sanciones pendientes.
     */
    private void abrirSancionesPendientes() {
        SancionesPendientesVista sancionesVista = new SancionesPendientesVista();
        new ControladorSancionesPendientes(sancionesVista);
        // Opcional: cerrar el menú de Admin
        vistaOpcionesAdmin.dispose();
    }

    /**
     * Muestra información adicional sobre el Administrador.
     * Este método se invoca si quieres enlazarla a algún componente extra (por ejemplo, clic en imagen).
     */
    @SuppressWarnings("unused")
    private void mostrarInfoAdministrador() {
        JOptionPane.showMessageDialog(
            vistaOpcionesAdmin,
            "Información del Administrador:\n" +
            "– Nombre de usuario: " + /* obtener de Singleton si quieres */ "" + "\n" +
            "– Fecha de registro: ...",
            "Perfil Administrador",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Cambia de vista a las opciones de Jugador.
     * Si quieres enlazar esta acción a un clic sobre la imagen de jugador, puedes llamarlo ahí.
     */
    @SuppressWarnings("unused")
    private void abrirMenuJugador() {
        MenuJugador vistaJugador = new MenuJugador();
        new ControladorOpcionesJugador(vistaJugador);
        vistaOpcionesAdmin.dispose();
    }
}
