package MODELO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un Partido de Fútbol-5 en el sistema.
 * Contiene información de fecha, estado, formacion, jugadores y equipos con resultados.
 */
public class Partido {

    // ----------------------------
    // 1. Atributos
    // ----------------------------
    private int partidoId;                     // Id del partido (clave primaria en BD)
    private LocalDate fecha;                   // Fecha programada del partido
    private boolean confirmado;                // Indica si el partido ya fue confirmado
    
    private Formaciones formacion;             // Plantilla utilizada para repartir
    private List<Jugador> jugadores;           // Lista de jugadores inscritos
    
    private EquipoRojo equipoRojo;             // Equipo Rojo asignado después del reparto
    private EquipoAzul equipoAzul;             // Equipo Azul asignado después del reparto
    
    private int resultadoRojo;                 // Goles anotados por el Equipo Rojo
    private int resultadoAzul;                 // Goles anotados por el Equipo Azul

    // ----------------------------
    // 2. Constructores
    // ----------------------------
    public Partido() {
        this.jugadores = new ArrayList<>();
        this.equipoRojo = new EquipoRojo();
        this.equipoAzul = new EquipoAzul();
        this.confirmado = false;
        this.resultadoRojo = 0;
        this.resultadoAzul = 0;
    }

    public Partido(LocalDate fecha, Formaciones formacion) {
        this();
        this.fecha = fecha;
        this.formacion = formacion;
    }

    // ----------------------------
    // 3. Getters y Setters
    // ----------------------------
    public int getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(int partidoId) {
        this.partidoId = partidoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Formaciones getFormacion() {
        return formacion;
    }

    public void setFormacion(Formaciones formacion) {
        this.formacion = formacion;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public EquipoRojo getEquipoRojo() {
        return equipoRojo;
    }

    public void setEquipoRojo(EquipoRojo equipoRojo) {
        this.equipoRojo = equipoRojo;
    }

    public EquipoAzul getEquipoAzul() {
        return equipoAzul;
    }

    public void setEquipoAzul(EquipoAzul equipoAzul) {
        this.equipoAzul = equipoAzul;
    }

    public int getResultadoRojo() {
        return resultadoRojo;
    }

    public void setResultadoRojo(int resultadoRojo) {
        this.resultadoRojo = resultadoRojo;
    }

    public int getResultadoAzul() {
        return resultadoAzul;
    }

    public void setResultadoAzul(int resultadoAzul) {
        this.resultadoAzul = resultadoAzul;
    }

    // ----------------------------
    // 4. Métodos de Negocio
    // ----------------------------

    /**
     * Agrega un jugador a la lista de inscritos, si aún no está y no está penalizado.
     *
     * @param j Jugador a inscribir
     * @return true si se agregó, false si ya estaba o está penalizado
     */
    public boolean agregarJugador(Jugador j) {
        if (j == null) return false;
        if (j.estaPenalizado()) {
            return false;
        }
        if (!jugadores.contains(j)) {
            jugadores.add(j);
            return true;
        }
        return false;
    }

    /**
     * Registra la inasistencia de un jugador en este partido.
     * Lo remueve de la lista si estaba inscrito y se le suma infracción.
     *
     * @param j Jugador que no asistió
     */
    public void registrarInasistencia(Jugador j) {
        if (j == null) return;
        if (jugadores.remove(j)) {
            j.addInfraccion();
        }
    }

    /**
     * Confirma el partido, indicando que se jugará y se validan los equipos y formaciones.
     */
    public void confirmar() {
        this.confirmado = true;
    }

    // ----------------------------
    // 5. Utilitarios Adicionales
    // ----------------------------

    /**
     * Retorna true si ya se asignaron ambos equipos (rojo y azul).
     */
    public boolean equiposAsignados() {
        return equipoRojo != null && equipoAzul != null
               && !equipoRojo.getJugadores().isEmpty()
               && !equipoAzul.getJugadores().isEmpty();
    }
}
