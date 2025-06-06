package MODELO;

import java.util.List;

/**
 * Implementación que reparte la “mitad superior” de la lista en ROJO  
 * y la “mitad inferior” en AZUL (simple ejemplo).
 */
public class ReparticionDefinida implements ReparticionEquipos {

    @Override
    public Equipo[] repartir(Formaciones formacion, List<Jugador> listaJugadores) {
        int total = listaJugadores.size();
        int mitad = total / 2; // si son impares, el azar o criterio define

        Equipo equipoRojo = new Equipo();
        equipoRojo.setTipo(Equipo.TipoEquipo.ROJO);

        Equipo equipoAzul = new Equipo();
        equipoAzul.setTipo(Equipo.TipoEquipo.AZUL);

        for (int i = 0; i < total; i++) {
            if (i < mitad) {
                equipoRojo.agregarJugador(listaJugadores.get(i));
            } else {
                equipoAzul.agregarJugador(listaJugadores.get(i));
            }
        }

        return new Equipo[]{ equipoRojo, equipoAzul };
    }
}
