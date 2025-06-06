package CONTROLADOR;

import MODELO.Jugador;
import MODELO.ListaJugadores;
import VISTA.Comun.ListaJugadoresVista;

import java.util.List;

/**
 * Controlador para la vista consolidada ListaJugadoresVista.
 * Obtiene todos los Jugador de la base de datos y se los pasa
 * a la vista para que renderice las “tarjetas” horizontales.
 */
public class ControladorListaJugadores {

    private final ListaJugadoresVista vista;
    private final ListaJugadores modelo;

    public ControladorListaJugadores(ListaJugadoresVista vista) {
        this.vista = vista;
        this.modelo = new ListaJugadores();

        cargarYMostrarJugadores();
    }

    /**
     * Obtiene la lista de jugadores desde el modelo y la envía a la vista.
     */
    private void cargarYMostrarJugadores() {
        List<Jugador> jugadores = modelo.getJugadores();
        vista.mostrarJugadores(jugadores);
        vista.setVisible(true);
    }
}
