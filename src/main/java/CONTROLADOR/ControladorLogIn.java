package CONTROLADOR;

import MODELO.Conexion;
import MODELO.Jugador;
import MODELO.Administrador;
import MODELO.Singleton;
import VISTA.InicioSesion.LogInVista;
import VISTA.InicioSesion.RegistroVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la pantalla de inicio de sesión.
 * - Valida credenciales usando MODELO.Conexion.
 * - Según el tipo de usuario, redirige a la vista correspondiente.
 * - Permite abrir la vista de registro de nuevo jugador.
 */
public class ControladorLogIn implements ActionListener {

    private LogInVista vistaLogin;
    private RegistroVista vistaRegistro;

    public ControladorLogIn(LogInVista vistaLogin, RegistroVista vistaRegistro) {
        this.vistaLogin = vistaLogin;
        this.vistaRegistro = vistaRegistro;

        // Asociar botones a este ActionListener
        this.vistaLogin.ingresarBoton.addActionListener(this);
        this.vistaLogin.nuevoUsuarioBoton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.ingresarBoton) {
            // 1) Leer usuario y clave de la vista
            String username = vistaLogin.getUsername().trim();
            String password = new String(vistaLogin.getPassword()); // supongamos que getPassword() devuelve char[]

            // 2) Intentar autenticar
            Conexion conexion = new Conexion(username, password);
            boolean exito = conexion.ingresar();

            if (exito) {
                // 3) Recuperar usuario desde Singleton
                Object usuarioActual = Singleton.getInstance().getUsuario();

                if (usuarioActual instanceof Jugador) {
                    Jugador j = (Jugador) usuarioActual;
                    System.out.println("Login exitoso como Jugador: " + j.getNombre());
                    // TODO: Abrir vista/controlador de Jugador
                    // Ejemplo:
                    // JugadorOpcionesVista vistaJugador = new JugadorOpcionesVista();
                    // new JugadorOpcionesController(vistaJugador);
                    // vistaJugador.setVisible(true);
                    vistaLogin.dispose(); // cerrar la ventana de login
                }
                else if (usuarioActual instanceof Administrador) {
                    Administrador a = (Administrador) usuarioActual;
                    System.out.println("Login exitoso como Administrador");
                    // TODO: Abrir vista/controlador de Administrador
                    // Ejemplo:
                    // AdministradorOpcionesVista vistaAdmin = new AdministradorOpcionesVista();
                    // new AdministradorOpcionesController(vistaAdmin);
                    // vistaAdmin.setVisible(true);
                    vistaLogin.dispose(); // cerrar la ventana de login
                }
                else {
                    vistaLogin.mostrarError("Tipo de usuario desconocido.");
                }
            } else {
                vistaLogin.mostrarError("Credenciales inválidas. Intente de nuevo.");
            }

        } else if (e.getSource() == vistaLogin.nuevoUsuarioBoton) {
            // Abrir la vista de registro y ocultar la de login
            System.out.println("Botón Nuevo Usuario presionado");
            // Se asume que RegistroController solo necesita la vista de registro:
            ControladorRegistro controladorRegistro = new ControladorRegistro(vistaRegistro);
            vistaRegistro.setVisible(true);
            vistaLogin.setVisible(false);
        }
    }
}
