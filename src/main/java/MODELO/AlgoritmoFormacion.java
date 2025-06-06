package MODELO;

import java.util.List;

/**
 * Determina cómo formar una lista de Equipos, a partir de Jugadores.
 */
public interface AlgoritmoFormacion {
    List<Equipo> formarEquipos(List<Jugador> jugadores);
}
