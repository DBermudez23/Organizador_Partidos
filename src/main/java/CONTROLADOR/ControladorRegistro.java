package CONTROLADOR;

import MODELO.Conexion;
import MODELO.Jugador;
import MODELO.ModoParticipacion;
import VISTA.InicioSesion.LogInVista;    // Para poder regresar al login
import VISTA.InicioSesion.RegistroVista; // Vista de registro que creamos

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ControladorRegistro:
 *  - Lee los campos ingresados en RegistroVista.
 *  - Valida que no haya campos vacíos.
 *  - Valida que contraseña y repetir contraseña coincidan.
 *  - Llama a Conexion.registrarJugador(...) para insertar en la BD.
 *  - Si el registro es exitoso, muestra mensaje, cierra registro y vuelve al login.
 *  - Si se hace clic en “ATRÁS”, simplemente regresa al login.
 */
public class ControladorRegistro implements ActionListener {

    private final LogInVista vistaLogin;
    private final RegistroVista vistaRegistro;

    public ControladorRegistro(LogInVista vistaLogin, RegistroVista vistaRegistro) {
        this.vistaLogin = vistaLogin;
        this.vistaRegistro = vistaRegistro;

        // Asociamos los botones de la Vista de registro a este ActionListener:
        this.vistaRegistro.registrarseBoton.addActionListener(this);
        this.vistaRegistro.atrasBoton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaRegistro.registrarseBoton) {
            // --- 1) Obtener datos de la vista ---
            String username = vistaRegistro.getUsername().trim();
            String mailLocal = vistaRegistro.getMailLocalPart().trim();
            String emailCompleto = mailLocal + "@futbol5.com";

            String pass1 = new String(vistaRegistro.getPassword());
            String pass2 = new String(vistaRegistro.getPasswordRepeat());

            // --- 2) Validaciones básicas ---
            if (username.isEmpty() || mailLocal.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                vistaRegistro.mostrarError("Por favor, complete todos los campos.");
                return;
            }
            if (!pass1.equals(pass2)) {
                vistaRegistro.mostrarError("Las contraseñas no coinciden.");
                return;
            }

            // --- 3) Crear el objeto Jugador con valores predeterminados ---
            Jugador nuevoJugador = new Jugador();
            nuevoJugador.setNombre(username);
            nuevoJugador.setFrecuente(false);
            nuevoJugador.setModoParticipacion(ModoParticipacion.ESTANDAR);
            nuevoJugador.setInfracciones(0);
            nuevoJugador.setCalificacionPromedio(0.0f);
            nuevoJugador.setCalificacionUltimoPartido(0.0f);
            // Si tu modelo Jugador contiene email, podrías almacenarlo:
            // nuevoJugador.setEmail(emailCompleto);

            // --- 4) Llamar a la capa de Modelo para insertar en la base de datos ---
            Conexion conexion = new Conexion();
            boolean exito = conexion.registrarJugador(username, pass1, nuevoJugador);

            // --- 5) Manejar el resultado del registro ---
            if (exito) {
                vistaRegistro.mostrarInfo("¡Registro exitoso! Ahora puede iniciar sesión.");
                vistaLogin.setVisible(true);
                vistaRegistro.dispose();
            } else {
                vistaRegistro.mostrarError(
                    "Error al registrar jugador.\n" +
                    "Verifique sus datos y vuelva a intentarlo."
                );
            }
        }
        else if (e.getSource() == vistaRegistro.atrasBoton) {
            vistaLogin.setVisible(true);
            vistaRegistro.dispose();
        }
    }
}
