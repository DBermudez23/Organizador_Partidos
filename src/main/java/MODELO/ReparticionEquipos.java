package MODELO;

import java.util.List;

/**
 * Interfaz que define cómo repartir una lista de Jugadores en dos Equipos.
 */
public interface ReparticionEquipos {
    /**
     * @param formacion      Objeto Formaciones con la táctica elegida.
     * @param listaJugadores Lista completa de Jugadores a repartir.
     * @return un arreglo de longitud 2: [0] = Equipo ROJO, [1] = Equipo AZUL.
     */
    Equipo[] repartir(Formaciones formacion, List<Jugador> listaJugadores);
}
