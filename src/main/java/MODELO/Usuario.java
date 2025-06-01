package MODELO;

/**
 * Interfaz que representan cualquier usuario del sistema (Jugador o Administrador).
 * Declara el método de autenticación que debe implementar cada tipo de usuario.
 */
public interface Usuario {
    /**
     * Verifica si el usuario está en estado válido para operar en el sistema.
     * En la práctica, se retorna true si el ID fue cargado correctamente tras el login.
     *
     * @return true si el usuario está autenticado/cargado, false en caso contrario
     */
    boolean login();
}
