package CONTROLADOR;

import MODELO.Jugador;
import MODELO.ListaJugadores;
import VISTA.VistaOpcionesAdmin.SancionesPendientesVista;

import java.util.List;

/**
 * Controlador para la vista SancionesPendientesVista.
 * Obtiene todos los jugadores desde el modelo, filtra los que tienen
 * infracciones pendientes (>= 2) y los muestra en la vista.
 */
public class ControladorSancionesPendientes {

    private final SancionesPendientesVista vista;
    private final ListaJugadores modelo;

    public ControladorSancionesPendientes(SancionesPendientesVista vista) {
        this.vista = vista;
        this.modelo = new ListaJugadores();

        // Cargar datos y mostrarlos
        cargarYMostrarSancionados();
    }

    /**
     * Obtiene la lista completa de jugadores, delega a la vista la
     * presentación de aquellos con infracciones >= 2, y hace visible la ventana.
     */
    private void cargarYMostrarSancionados() {
        List<Jugador> todos = modelo.getJugadores();
        vista.mostrarSancionados(todos);
        vista.setVisible(true);
    }
}
