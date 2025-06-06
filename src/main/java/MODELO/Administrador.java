package MODELO;

import java.util.List;

/**
 * Representa un Administrador en el sistema de Fútbol-5.
 * Implementa la interfaz Usuario para permitir autenticación genérica.
 */
public class Administrador implements Usuario {

    // ----------------------------
    // 1. Atributos
    // ----------------------------
    private int id;           // Identificador único (coincide con usuario_id en la BD)
    private String nombre;    // Opcional: si deseas almacenar el nombre del admin

    // ----------------------------
    // 2. Constructores
    // ----------------------------
    public Administrador() { }

    public Administrador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // ----------------------------
    // 3. Getters y Setters
    // ----------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // ----------------------------
    // 4. Métodos de Negocio (según UML)
    // ----------------------------

    /**
     * Crea una nueva instancia de Partido.
     * En un caso real, podría inicializar campos y guardarla en la base.
     *
     * @return objeto Partido recién creado (con fecha actual y no confirmado).
     */
    public Partido crearPartido() {
        Partido p = new Partido();
        // Inicializar con fecha actual y estado no confirmado
        p.setFecha(java.time.LocalDate.now());
        p.setConfirmado(false);
        return p;
    }

    /**
     * Genera los equipos para un partido usando el algoritmo de formación dado.
     * Llama al método formarEquipos() del AlgoritmoFormacion, que debe devolver
     * una lista de 2 objetos Equipo: el primero será el equipo ROJO, el segundo el AZUL.
     *
     * @param p          el partido al que se le asignarán los equipos
     * @param algoritmo  implementación concreta de AlgoritmoFormacion
     */
    public void generarEquipos(Partido p, AlgoritmoFormacion algoritmo) {
        // Se asume que algoritmo.formarEquipos(...) devuelve una List<Equipo> de tamaño al menos 2:
        //   - índices [0] → EquipRojo
        //   - índices [1] → EquipAzul
        List<Equipo> equipos = algoritmo.formarEquipos(p.getJugadores());

        if (equipos != null && equipos.size() >= 2) {
            // Primer equipo → ROJO
            Equipo rojo = equipos.get(0);
            rojo.setTipo(Equipo.TipoEquipo.ROJO);
            p.setEquipoRojo(rojo);

            // Segundo equipo → AZUL
            Equipo azul = equipos.get(1);
            azul.setTipo(Equipo.TipoEquipo.AZUL);
            p.setEquipoAzul(azul);
        }
    }

    /**
     * Aplica una sanción a un jugador (incrementa su contador de infracciones).
     *
     * @param j instancia de Jugador a sancionar
     */
    public void sancionar(Jugador j) {
        if (j != null) {
            j.addInfraccion();
        }
    }

    /**
     * Quita una infracción a un jugador (decrementa, si es posible).
     *
     * @param j instancia de Jugador a “des-sancionar”
     */
    public void quitarInfraccion(Jugador j) {
        if (j != null) {
            int actuales = j.getInfracciones();
            if (actuales > 0) {
                j.setInfracciones(actuales - 1);
            }
        }
    }

    /**
     * Marca un partido como confirmado (true) y, en un caso real,
     * lo guardaría en la base de datos usando una clase de persistencia.
     *
     * @param p instancia de Partido que se confirmará
     */
    public void confirmarPartido(Partido p) {
        if (p != null) {
            p.setConfirmado(true);
            // En un caso real, aquí se podría invocar a Conexion.guardarPartido(...)
            // para persistir los resultados y los equipos.
        }
    }

    // ----------------------------
    // 5. Implementación de Usuario
    // ----------------------------
    /**
     * Método requerido por la interfaz Usuario.
     * En este caso, si el Administrador ya tiene un ID asignado (> 0),
     * consideramos que “está listo” para operar.
     */
    @Override
    public boolean login() {
        return this.id > 0;
    }
}
