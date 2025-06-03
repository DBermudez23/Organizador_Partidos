package CONTROLADOR;

import VISTA.VistaOpcionesAdmin.MenuAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Controlador para el menú de Opciones de Administrador.
 * Registra los listeners de cada botón y dirige a la vista/controlador correspondiente.
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

        // Registrar este controlador como listener de cada botón
        this.vistaOpcionesAdmin.crearPartidoButton.addActionListener(this);
        this.vistaOpcionesAdmin.PartidoActualButton.addActionListener(this);
        this.vistaOpcionesAdmin.listaJugadoresButton.addActionListener(this);
        this.vistaOpcionesAdmin.sancionesPendientesButton.addActionListener(this);
        // (Opcional) Si se desea reaccionar a clicks en las imágenes
        this.vistaOpcionesAdmin.imagenAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Ejemplo: mostrar información del administrador
                mostrarInfoAdministrador();
            }
        });
        this.vistaOpcionesAdmin.imagenJugador.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Ejemplo: cambiar a vista de opciones de Jugador
                abrirMenuJugador();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vistaOpcionesAdmin.crearPartidoButton) {
            abrirCrearPartido();
        }
        else if (source == vistaOpcionesAdmin.PartidoActualButton) {
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
        // TODO: Instanciar CrearPartidoVista y su controlador
        // Ejemplo:
        // CrearPartidoVista crearVista = new CrearPartidoVista();
        // new CrearPartidoController(crearVista);
        System.out.println("Usuario seleccionó: Crear Partido");
    }

    /**
     * Abre la vista/controlador que muestra el partido actual en curso.
     */
    private void abrirPartidoActual() {
        // TODO: Instanciar PartidoActualVista y su controlador
        // Ejemplo:
        // PartidoActualVista actualVista = new PartidoActualVista();
        // new PartidoActualController(actualVista);
        System.out.println("Usuario seleccionó: Ver Partido Actual");
    }

    /**
     * Abre la vista/controlador que muestra la lista de todos los jugadores.
     */
    private void abrirListaJugadores() {
        // TODO: Instanciar ListaJugadoresVista y su controlador
        // Ejemplo:
        // ListaJugadoresVista listaVista = new ListaJugadoresVista();
        // new ListaJugadoresController(listaVista);
        System.out.println("Usuario seleccionó: Lista de Jugadores");
    }

    /**
     * Abre la vista/controlador que muestra los jugadores con sanciones pendientes.
     */
    private void abrirSancionesPendientes() {
        // TODO: Instanciar SancionesPendientesVista y su controlador
        // Ejemplo:
        // SancionesPendientesVista sancionesVista = new SancionesPendientesVista();
        // new SancionesPendientesController(sancionesVista);
        System.out.println("Usuario seleccionó: Sanciones Pendientes");
    }

    /**
     * Muestra información adicional sobre el Administrador.
     * Este método se invoca si se hace clic en la imagen del administrador.
     */
    private void mostrarInfoAdministrador() {
        // TODO: Implementar lógica para mostrar perfil o datos del admin
        System.out.println("Clic en imagen Admin: mostrar información del Administrador");
    }

    /**
     * Cambia de vista a las opciones de Jugador.
     * Este método se invoca si se hace clic en la imagen del jugador.
     */
    private void abrirMenuJugador() {
        // TODO: Cerrar menú admin y abrir menú de jugador
        System.out.println("Clic en imagen Jugador: abrir menú de Jugador");
    }
}
