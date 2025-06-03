package CONTROLADOR;

import MODELO.Conexion;
import MODELO.Jugador;
import MODELO.Administrador;
import MODELO.Singleton;
import VISTA.InicioSesion.LogInVista;
import VISTA.InicioSesion.RegistroVista;
import VISTA.VistaOpcionesAdmin.MenuAdmin;
import CONTROLADOR.ControladorOpcionesAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la pantalla de inicio de sesión.
 * - Valida credenciales usando MODELO.Conexion.
 * - Según el tipo de usuario, redirige a la vista correspondiente.
 * - Permite abrir la vista de registro de nuevo jugador.
 */
public class ControladorLogIn implements ActionListener {

    private final LogInVista vistaLogin;
    private final RegistroVista vistaRegistro;

    public ControladorLogIn(LogInVista vistaLogin, RegistroVista vistaRegistro) {
        this.vistaLogin = vistaLogin;
        this.vistaRegistro = vistaRegistro;

        // Asociar botones de la vista de login a este ActionListener
        this.vistaLogin.ingresarBoton.addActionListener(this);
        this.vistaLogin.nuevoUsuarioBoton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.ingresarBoton) {
            // 1) Leer usuario y clave desde los campos de la vista
            String username = vistaLogin.getUsername().trim();
            // getPassword() suele devolver un char[], así que lo convertimos a String:
            String password = new String(vistaLogin.getPassword());

            // 2) Intentar autenticar mediante el modelo
            Conexion conexion = new Conexion(username, password);
            boolean exito = conexion.ingresar();

            if (exito) {
                // 3) Si el login fue exitoso, recuperamos el objeto cargado en el Singleton
                Object usuarioActual = Singleton.getInstance().getUsuario();

                if (usuarioActual instanceof Jugador) {
                    Jugador j = (Jugador) usuarioActual;
                    System.out.println("Login exitoso como Jugador: " + j.getNombre());

                    // TODO: Abrir la vista/controlador de Jugador
                    // Ejemplo:
                    // JugadorOpcionesVista vistaJugador = new JugadorOpcionesVista();
                    // new JugadorOpcionesController(vistaJugador);
                    // vistaJugador.setVisible(true);

                    // Cerrar la ventana de login
                    vistaLogin.dispose();
                }
                else if (usuarioActual instanceof Administrador) {
                    Administrador a = (Administrador) usuarioActual;
                    System.out.println("Login exitoso como Administrador");

                    // TODO: Abrir la vista/controlador de Administrador
                    // Ejemplo:
                    MenuAdmin vistaAdmin = new MenuAdmin();
                    new ControladorOpcionesAdmin(vistaAdmin);
                    vistaAdmin.setVisible(true);
                    vistaLogin.setVisible(false);
                }
                else {
                    // Caso muy raro: el objeto en Singleton no es ni Jugador ni Administrador
                    vistaLogin.mostrarError("Tipo de usuario desconocido.");
                }
            } else {
                // Si la conexión/consulta falló o credenciales inválidas
                vistaLogin.mostrarError("Credenciales inválidas. Intente de nuevo.");
            }

        } else if (e.getSource() == vistaLogin.nuevoUsuarioBoton) {
            // 4) Si presionó “Nuevo Usuario”, abrimos la vista de registro
            System.out.println("Botón Nuevo Usuario presionado");

            // Creamos el controlador de registro (RegistroController) con su vista
            ControladorRegistro controladorRegistro = new ControladorRegistro(vistaRegistro);

            // Mostramos la ventana de registro y ocultamos la de login
            vistaRegistro.setVisible(true);
            vistaLogin.setVisible(false);
        }
    }
}
