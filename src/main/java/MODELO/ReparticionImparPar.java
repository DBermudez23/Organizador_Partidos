package MODELO;

import java.util.Comparator;
import java.util.List;

/**
 * Implementación que reparte alternando impares/pares según, por ejemplo, calificación.
 */
public class ReparticionImparPar implements ReparticionEquipos {

    @Override
    public Equipo[] repartir(Formaciones formacion, List<Jugador> listaJugadores) {
        // 1) (Opcional) Ordenar listaJugadores por algún criterio:
        listaJugadores.sort(Comparator.comparingDouble(Jugador::getCalificacionPromedio).reversed());

        // 2) Crear los dos equipos
        Equipo equipoRojo = new Equipo();
        equipoRojo.setTipo(Equipo.TipoEquipo.ROJO);

        Equipo equipoAzul = new Equipo();
        equipoAzul.setTipo(Equipo.TipoEquipo.AZUL);

        // 3) Repartir alternando
        boolean asignarARojo = true;
        for (Jugador j : listaJugadores) {
            if (asignarARojo) {
                equipoRojo.agregarJugador(j);
            } else {
                equipoAzul.agregarJugador(j);
            }
            asignarARojo = !asignarARojo;
        }

        // 4) Devolver en un arreglo de longitud 2
        return new Equipo[]{ equipoRojo, equipoAzul };
    }
}
