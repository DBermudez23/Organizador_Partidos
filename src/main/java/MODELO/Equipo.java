package MODELO;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un equipo (ROJO o AZUL).  
 * Antes tenías EquipoRojo y EquipoAzul por separado; ahora con esto abarcamos ambos casos.
 */
public class Equipo {

    public enum TipoEquipo {
        ROJO, AZUL
    }

    private int id;                    // (opcional, mapea equipo_id en BD)
    private TipoEquipo tipo;           // ROJO o AZUL
    private List<Jugador> jugadores;   // lista de jugadores del equipo

    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    public Equipo(int id, TipoEquipo tipo) {
        this.id = id;
        this.tipo = tipo;
        this.jugadores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public TipoEquipo getTipo() {
        return tipo;
    }
    public void setTipo(TipoEquipo tipo) {
        this.tipo = tipo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void agregarJugador(Jugador j) {
        if (j != null && !jugadores.contains(j)) {
            jugadores.add(j);
        }
    }

    public void removerJugador(Jugador j) {
        jugadores.remove(j);
    }

    @Override
    public String toString() {
        return "[" + tipo + "] (#" + jugadores.size() + " jugadores)";
    }
}
