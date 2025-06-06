package CONTROLADOR;

import MODELO.Conexion;
import MODELO.Jugador;
import MODELO.Administrador;
import MODELO.Singleton;
import VISTA.InicioSesion.LogInVista;
import VISTA.InicioSesion.RegistroVista;
import VISTA.VistaOpcionesAdmin.MenuAdmin;
import VISTA.VistaOpcionesJugador.MenuJugador;

/**
 * Controlador para la pantalla de inicio de sesión (LogIn).
 * - Lee los datos ingresados en LogInVista.
 * - Invoca a MODELO.Conexion para autenticar.
 * - Según el tipo de usuario (Jugador o Administrador) en Singleton,
 *   abre la vista correspondiente.
 * - Permite cambiar a la pantalla de registro de nuevo jugador.
 */
public class ControladorLogIn implements java.awt.event.ActionListener {

    private final LogInVista vistaLogin;
    private final RegistroVista vistaRegistro;

    public ControladorLogIn(LogInVista vistaLogin, RegistroVista vistaRegistro) {
        this.vistaLogin = vistaLogin;
        this.vistaRegistro = vistaRegistro;

        // Asociar los botones de la vista de login a este ActionListener
        this.vistaLogin.ingresarBoton.addActionListener(this);
        this.vistaLogin.nuevoUsuarioBoton.addActionListener(this);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == vistaLogin.ingresarBoton) {
            // -------------------------------------------------
            // 1) Leer usuario y clave desde la vista:
            // -------------------------------------------------
            String username = vistaLogin.getUsername();
            String password = new String(vistaLogin.getPassword());

            // -------------------------------------------------
            // 2) Intentar autenticar mediante MODELO.Conexion:
            // -------------------------------------------------
            Conexion conexion = new Conexion(username, password);
            boolean exito = conexion.ingresar();

            if (exito) {
                // -------------------------------------------------
                // 3) Si el login fue exitoso, recuperamos
                //    el objeto Usuario (Jugador o Administrador)
                //    que se almacenó dentro del Singleton.
                // -------------------------------------------------
                Object usuarioActual = Singleton.getInstance().getUsuario();

                if (usuarioActual instanceof Jugador) {
                    Jugador j = (Jugador) usuarioActual;
                    System.out.println("Login exitoso como Jugador: " + j.getNombre());

                    // -------------------------------------------------
                    // 4) Abrir Menú de Jugador:
                    // -------------------------------------------------
                    MenuJugador vistaJugador = new MenuJugador();
                    new ControladorMenuJugador(vistaJugador);

                    // Cerrar la ventana de login
                    vistaLogin.dispose();
                }
                else if (usuarioActual instanceof Administrador) {
                    Administrador a = (Administrador) usuarioActual;
                    System.out.println("Login exitoso como Administrador");

                    // -------------------------------------------------
                    // 5) Abrir Menú de Administrador:
                    // -------------------------------------------------
                    MenuAdmin vistaAdmin = new MenuAdmin();
                    new ControladorOpcionesAdmin(vistaAdmin);

                    // Cerrar (o esconder) la ventana de login
                    vistaLogin.dispose();
                }
                else {
                    // -------------------------------------------------
                    // Caso muy raro: Si singleton no contiene ni Jugador ni Administrador
                    // -------------------------------------------------
                    vistaLogin.mostrarError("Tipo de usuario desconocido. Comuníquese con soporte.");
                }
            }
            else {
                // -------------------------------------------------
                // 6) Credenciales inválidas: mostrar mensaje de error
                // -------------------------------------------------
                vistaLogin.mostrarError("Usuario o contraseña incorrectos. Intente de nuevo.");
            }

        } else if (e.getSource() == vistaLogin.nuevoUsuarioBoton) {
            // -------------------------------------------------
            // 7) Cuando presionan “Nuevo Usuario”: abrir registro
            // -------------------------------------------------
            ControladorRegistro controladorRegistro = new ControladorRegistro(vistaLogin, vistaRegistro);
            vistaRegistro.setVisible(true);
            vistaLogin.setVisible(false);
        }
    }
}
