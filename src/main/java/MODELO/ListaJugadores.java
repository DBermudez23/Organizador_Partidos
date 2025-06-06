package MODELO;

import java.util.ArrayList;
import java.util.List;

/**
 * Contiene lógica para obtener todos los jugadores (por ejemplo, de BD o mock).
 */
public class ListaJugadores {

    public ListaJugadores() {
        // podrías inyectar un servicio/DAO; por ahora, empty.
    }

    /**
     * @return lista de todos los Jugador existentes (quizá con un SELECT a la BD).
     * En un ejemplo muy básico, podrías devolver una lista vacía o datos mock.
     */
    public List<Jugador> getJugadores() {
        // TODO: implementar la consulta a BD (SELECT * FROM JUGADOR)
        return new ArrayList<>();
    }

    /**
     * @return los N jugadores más frecuentes, ordenados por alguna regla de negocio.
     */
    public List<Jugador> getFrecuentes(int topN) {
        // TODO: implementación de filtrado / ordenamiento por calificación / frecuencia.
        return new ArrayList<>();
    }
}
