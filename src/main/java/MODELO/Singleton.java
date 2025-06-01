package MODELO;

/**
 * Singleton para mantener la sesión del usuario autenticado.
 * Almacena el usuario actual (que puede ser de tipo Jugador o Administrador).
 */
public class Singleton {

    // Única instancia de Singleton
    private static Singleton instance;

    // Referencia al usuario autenticado (implementa la interfaz Usuario)
    private Usuario currentUser;

    // ----------------------------
    // 1. Constructor privado
    // ----------------------------
    private Singleton() {
        // Vacío para prevenir instanciación externa
    }

    // ----------------------------
    // 2. Obtener la instancia
    //    (thread-safe, inicialización perezosa)
    // ----------------------------
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // ----------------------------
    // 3. Métodos para acceder y modificar el usuario actual
    // ----------------------------

    /**
     * Asigna el usuario que acaba de autenticarse.
     * Puede ser un Jugador o un Administrador (ambos implementan Usuario).
     *
     * @param u instancia de Usuario (Jugador o Administrador)
     */
    public void setUsuario(Usuario u) {
        this.currentUser = u;
    }

    /**
     * Retorna el usuario actual almacenado en la sesión.
     * Si no hay nadie autenticado, devuelve null.
     *
     * @return Usuario autenticado o null si no hay ninguno
     */
    public Usuario getUsuario() {
        return this.currentUser;
    }

    /**
     * Retorna el usuario actual como Jugador, si corresponde.
     *
     * @return Jugador autenticado, o null si el currentUser no es Jugador
     */
    public Jugador getJugador() {
        if (currentUser instanceof Jugador) {
            return (Jugador) currentUser;
        }
        return null;
    }

    /**
     * Retorna el usuario actual como Administrador, si corresponde.
     *
     * @return Administrador autenticado, o null si el currentUser no es Administrador
     */
    public Administrador getAdministrador() {
        if (currentUser instanceof Administrador) {
            return (Administrador) currentUser;
        }
        return null;
    }

    // ----------------------------
    // 4. Método para cerrar sesión
    // ----------------------------
    /**
     * Cierra la sesión actual, borrando la referencia al usuario.
     */
    public void logout() {
        this.currentUser = null;
    }
}
