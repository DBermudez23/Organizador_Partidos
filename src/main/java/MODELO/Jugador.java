package MODELO;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un jugador de Fútbol-5 en el sistema.
 * Implementa la interfaz Usuario para permitir autenticación genérica.
 */
public class Jugador implements Usuario {

    // ----------------------------
    // 1. Atributos
    // ----------------------------
    private int id;  // Identificador único (coincide con usuario_id)
    private String nombre;
    private boolean frecuente;
    private ModoParticipacion modo;
    private int infracciones;
    private List<Calificacion> calificaciones;
    private float calificacionPromedio;
    private float calificacionUltimoPartido;

    // ----------------------------
    // 2. Constructores
    // ----------------------------
    public Jugador() {
        this.calificaciones = new ArrayList<>();
    }

    public Jugador(int id, String nombre, boolean frecuente, ModoParticipacion modo) {
        this.id = id;
        this.nombre = nombre;
        this.frecuente = frecuente;
        this.modo = modo;
        this.infracciones = 0;
        this.calificaciones = new ArrayList<>();
        this.calificacionPromedio = 0.0f;
        this.calificacionUltimoPartido = 0.0f;
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

    public boolean isFrecuente() {
        return frecuente;
    }

    public void setFrecuente(boolean frecuente) {
        this.frecuente = frecuente;
    }

    public ModoParticipacion getModo() {
        return modo;
    }

    public void setModoParticipacion(ModoParticipacion modo) {
        this.modo = modo;
    }

    public int getInfracciones() {
        return infracciones;
    }

    public void setInfracciones(int infracciones) {
        this.infracciones = infracciones;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
        actualizarCalificacionPromedio();
    }

    public float getCalificacionPromedio() {
        return calificacionPromedio;
    }

    /** 
     * Ahora public para que Conexion pueda invocarlo 
     */
    public void setCalificacionPromedio(float calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public float getCalificacionUltimoPartido() {
        return calificacionUltimoPartido;
    }

    public void setCalificacionUltimoPartido(float calificacionUltimoPartido) {
        this.calificacionUltimoPartido = calificacionUltimoPartido;
    }

    // ----------------------------
    // 4. Métodos de Negocio
    // ----------------------------

    /**
     * Calcula y retorna el promedio de todas las calificaciones registradas.
     * Si no hay calificaciones, retorna 0.
     */
    public float getPromedio() {
        if (calificaciones == null || calificaciones.isEmpty()) {
            return 0.0f;
        }
        float suma = 0.0f;
        for (Calificacion c : calificaciones) {
            suma += c.getPuntuacion();
        }
        return suma / calificaciones.size();
    }

    /**
     * Recalcula el atributo calificacionPromedio internamente,
     * invocado automáticamente cada vez que se setea la lista completa.
     */
    public void actualizarCalificacionPromedio() {
        float prom = getPromedio();
        setCalificacionPromedio(prom);
    }

    /**
     * Indica si el jugador está penalizado.
     * Según el negocio, se asume que 2 o más infracciones significan penalización.
     *
     * @return true si infracciones >= 2, false en caso contrario
     */
    public boolean estaPenalizado() {
        return infracciones >= 2;
    }

    /**
     * Agrega una nueva calificación a la lista y actualiza el promedio.
     *
     * @param c objeto Calificacion a registrar
     */
    public void addCalificacion(Calificacion c) {
        if (c == null) return;
        this.calificaciones.add(c);
        actualizarCalificacionPromedio();
        this.calificacionUltimoPartido = c.getPuntuacion();
    }

    /**
     * Incrementa en 1 el contador de infracciones.
     */
    public void addInfraccion() {
        this.infracciones++;
    }

    // ----------------------------
    // 5. Implementación de Usuario
    // ----------------------------
    /**
     * Método requerido por la interfaz Usuario.
     * En nuestro caso, la autenticación real la hace Conexion.ingresar(), así que aquí
     * podemos devolver true si hay un ID asignado (o false si no).
     */
    @Override
    public boolean login() {
        return this.id > 0;
    }
}
